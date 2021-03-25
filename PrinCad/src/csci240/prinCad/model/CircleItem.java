package csci240.prinCad.model;

import csci240.prinCad.fx3d.Map3d;
import csci240.prinCad.fx3d.Xform;
import csci240.prinCad.util.Log;
import javafx.scene.Node;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Sphere;

public class CircleItem extends CadItem {
	public final double x, y, radius;

	public CircleItem(double _xCenter, double _yCenter, double _r) {
		this.x = _xCenter;
		this.y = _yCenter;
		this.radius = _r;
	}

	public static CircleItem load(String info) {

		CircleItem item = null;
		try {
			String[] tokens = info.split(" ");
			double x = Double.parseDouble(tokens[1]);
			double y = Double.parseDouble(tokens[2]);
			double radius = Double.parseDouble(tokens[3]);
			double zvalue = (Double.parseDouble(tokens[4]));
			item = new CircleItem(x, y, radius);
			item.setzValue(zvalue);

		} catch (Exception ex) {
			Log.error("Invalid CircleItem data string: " + info, ex);
		}

		return item;
	}

	@Override
	public void draw(GraphicsContext gc) {
		gc.strokeOval(x - radius, y - radius, 2 * radius, 2 * radius);

	}

	public CadBox getRect() {
		return new CadBox(x - radius, y - radius, x + radius, y + radius);
	}

	@Override
	public String save() {
		double z = getzValue();
		return String.format("%1$f %2$f %3$f %4$f", x, y, radius, z);

	}

	@Override
	public boolean intersects(CadLine line) {
		double dx0 = line.x0 - x;
		double dy0 = line.y0 - y;
		double dx1 = line.x1 - x;
		double dy1 = line.y1 - y;
		double lenSquare0 = dx0 * dx0 + dy0 * dy0;
		double lenSquare1 = dx1 * dx1 + dy1 * dy1;
		double radiusSquare = radius * radius;

		return ((lenSquare0 <= radiusSquare && lenSquare1 >= radiusSquare)
				|| (lenSquare0 >= radiusSquare && lenSquare1 <= radiusSquare));
	}

	@Override
	public CadItem Copy() {
		return new CircleItem(x, y, radius);
	}

	@Override
	public Node get3dObject(Map3d map) {

		final PhongMaterial material = new PhongMaterial();
		material.setDiffuseColor(Color.DARKRED);
		material.setSpecularColor(Color.RED);

		Sphere sphere = new Sphere(map.Scale(radius));
		sphere.setMaterial(material);

		Xform xform = new Xform();
		xform.getChildren().add(sphere);
		xform.setTx(map.ToWorldX(x));
		xform.setTy(map.ToWorldY(y));
		xform.setTz(map.ToWorldZ(getzValue()));

		return xform;
	}

}
