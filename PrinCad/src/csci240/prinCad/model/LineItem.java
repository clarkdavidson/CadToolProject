package csci240.prinCad.model;

import csci240.prinCad.fx3d.Map3d;
import csci240.prinCad.fx3d.Xform;
import javafx.scene.Node;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Cylinder;
import javafx.scene.transform.Rotate;

public class LineItem extends CadItem {
	public final double x, y, x2, y2;
	public final CadLine _cadLine;

	public LineItem(double x, double y, double x2, double y2) {
		this.x = x;
		this.y = y;
		this.x2 = x2;
		this.y2 = y2;
		_cadLine = new CadLine(x, y, x2, y2);
	}

	public LineItem(String info) {
		String[] tokens = info.split(" ");
		x = Double.parseDouble(tokens[1]);
		y = Double.parseDouble(tokens[2]);
		x2 = Double.parseDouble(tokens[3]);
		y2 = Double.parseDouble(tokens[4]);
		setzValue(Double.parseDouble(tokens[5]));
		_cadLine = new CadLine(x, y, x2, y2);
	}

	@Override
	public void draw(GraphicsContext gc) {
		gc.strokeLine(x, y, x2, y2);

	}

	@Override
	public String save() {
		double z = getzValue();
		return String.format("%1$f %2$f %3$f %4$f %5$f", x, y, x2, y2,z);
	}

	@Override
	public CadBox getRect() {
		return new CadBox(x, y, x2, y2);
	}

	@Override
	public boolean intersects(CadLine line) {
		return line.intersects(x, y, x2, y2);
	}

	@Override
	public CadItem Copy() {
		return new LineItem(x, y, x2, y2);
	}

	@Override
	public Node get3dObject(Map3d map) {
		final PhongMaterial material = new PhongMaterial();
		material.setDiffuseColor(Color.DARKGREEN);
		material.setSpecularColor(Color.AZURE);

		double xc = (this.x + this.x2) / 2.0;
		double yc = (this.y + this.y2) / 2.0;

		double rotationAngle = 0.0 - Math.acos(_cadLine.cos) * 180.0 / Math.PI;

		Cylinder cylinder = new Cylinder(5, map.Scale(_cadLine.len));
		cylinder.setMaterial(material);

		cylinder.setRotationAxis(Rotate.Z_AXIS);
		cylinder.setRotate(rotationAngle);

		Xform xform = new Xform();
		xform.getChildren().add(cylinder);
		xform.setTx(map.ToWorldX(xc));
		xform.setTy(map.ToWorldY(yc));
		xform.setTz(map.ToWorldZ(getzValue()));

		return xform;
	}

}
