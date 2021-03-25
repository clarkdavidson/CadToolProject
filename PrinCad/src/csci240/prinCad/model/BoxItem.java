package csci240.prinCad.model;

import csci240.prinCad.fx3d.Map3d;
import csci240.prinCad.fx3d.Xform;
import javafx.scene.Node;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;

public class BoxItem extends CadItem {
	CadPoint p;

	public BoxItem(CadPoint p) {
		this.p = p;
	}

	@Override
	public Node get3dObject(Map3d map) {
		final PhongMaterial material = new PhongMaterial();
		material.setDiffuseColor(Color.DEEPSKYBLUE);
		material.setSpecularColor(Color.LIGHTPINK);

		double w2 = 5.0;
		double h2 = 5.0;
		Box box = new Box(map.Scale(2*MarkerSize), map.Scale(2*MarkerSize), map.Scale(10));
		box.setMaterial(material);

		Xform xform = new Xform();
		xform.getChildren().add(box);
		xform.setTx(map.ToWorldX(p.x + w2));
		xform.setTy(map.ToWorldY(p.y + h2));
		xform.setTz(map.ToWorldX(getzValue())); 

		return xform;
	}

	@Override
	public void draw(GraphicsContext gc) {
		gc.strokeRect(p.x - MarkerSize, p.y - MarkerSize, 2 * MarkerSize, 2 * MarkerSize);

	}

	@Override
	public String save() {
		return String.format("%1$f %2$f %3$f %4$f", p.x, p.y, 2 * MarkerSize, 2 * MarkerSize);
	}

	@Override
	public CadBox getRect() {
		return new CadBox(p.x - MarkerSize, p.y - MarkerSize, 2 * MarkerSize, 2 * MarkerSize);
	}

	@Override
	public boolean intersects(CadLine line) {
		return line.intersects(getRect());
	}

	@Override
	public CadItem Copy() {
		return new BoxItem(this.p);
	}

}
