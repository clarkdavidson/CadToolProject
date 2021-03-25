package csci240.prinCad.model;

import csci240.prinCad.fx3d.Map3d;
import csci240.prinCad.fx3d.Xform;
import javafx.scene.Node;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;

public class SquareItem extends CadItem {
	public final double x, y, w, h, Depth;

	public SquareItem(double x, double y, double w, double h) {
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		this.Depth = (w + h) / 2;
	}

	public SquareItem(String info) {
		String[] tokens = info.split(" ");
		x = Double.parseDouble(tokens[1]);
		y = Double.parseDouble(tokens[2]);
		w = Double.parseDouble(tokens[3]);
		h = Double.parseDouble(tokens[4]);
		setzValue(Double.parseDouble(tokens[5]));
		Depth = (w + h) / 2;
	}

	@Override
	public void draw(GraphicsContext gc) {
		gc.strokeRect(x, y, w, h);

	}

	@Override
	public String save() {
		double z = getzValue();
		return String.format("%1$f %2$f %3$f %4$f %5$f", x, y, w, h, z);
	}

	@Override
	public CadBox getRect() {
		return new CadBox(x, y, w, h);
	}

	@Override
	public boolean intersects(CadLine line) {

		return line.intersects(getRect());
	}

	@Override
	public CadItem Copy() {
		return new SquareItem(x, y, w, h);
	}

	@Override
	public Node get3dObject(Map3d map) {

		final PhongMaterial material = new PhongMaterial();
		material.setDiffuseColor(Color.DEEPSKYBLUE);
		material.setSpecularColor(Color.LIGHTPINK);

		double w2 = this.w / 2.0;
		double h2 = this.h / 2.0;
		Box box = new Box(map.Scale(this.w), map.Scale(this.h), map.Scale(this.Depth));
		box.setMaterial(material);

		Xform xform = new Xform();
		xform.getChildren().add(box);
		xform.setTx(map.ToWorldX(x + w2));
		xform.setTy(map.ToWorldY(y + h2));
		xform.setTz(map.ToWorldZ(getzValue()));

		return xform;
	}

}
