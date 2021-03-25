package csci240.prinCad.model;

import csci240.prinCad.fx3d.Map3d;
import csci240.prinCad.fx3d.Xform;
import javafx.scene.Node;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Cylinder;
import javafx.scene.transform.Rotate;

public class PlusMarkerItem extends CadItem {
	CadPoint point;

	public PlusMarkerItem(CadPoint point) {
		this.point = point;
	}
	
	
	@Override
	public void draw(GraphicsContext gc) {
		double x = point.x;
		double y = point.y;
		gc.strokeLine(x - MarkerSize, y, x + MarkerSize, y);
		gc.strokeLine(x, y - MarkerSize, x, y + MarkerSize);
	}
	
	public static PlusMarkerItem load(String data) {
		CadPoint point = CadItem.toPoint(data);
		if(point==null)
			return null;
		
		return new PlusMarkerItem(point);
	}
	
	//Copy cadItem
	@Override
	public CadItem Copy() {
		return new PlusMarkerItem(point);
	}
	
	//get the 3d Object
	@Override
	public Node get3dObject(Map3d map) {
		final PhongMaterial material = new PhongMaterial();
		material.setDiffuseColor(Color.GOLDENROD);
		material.setSpecularColor(Color.YELLOW);

		double s = map.Scale(MarkerSize + MarkerSize) * 2.0;
		
		Cylinder cylinderX = new Cylinder(0.25,s);
		cylinderX.setMaterial(material);
		
		cylinderX.setRotationAxis(Rotate.X_AXIS);
		cylinderX.setRotate(90);
		
		Cylinder cylinderY=new Cylinder(0.25,s);
		cylinderY.setMaterial(material);
		
		cylinderY.setRotationAxis(Rotate.Y_AXIS);
		cylinderY.setRotate(90);
		
		Cylinder cylinderZ = new Cylinder(0.25,s);
		cylinderZ.setMaterial(material);
		
		cylinderZ.setRotationAxis(Rotate.Z_AXIS);
		cylinderZ.setRotate(90);
		
		
		Xform xform = new Xform();
		xform.getChildren().add(cylinderX);
		xform.getChildren().add(cylinderY);
		xform.getChildren().add(cylinderZ);
		xform.setTx(map.ToWorldX(point.x));
		xform.setTy(map.ToWorldY(point.y));
		xform.setTz(map.ToWorldZ(getzValue()));
		
		return xform;
	}
	
	@Override
	public String save() {
		return String.format("%1$f %2$f", point.x, point.y);
	}
	
	@Override
	public CadBox getRect() {
		return new CadBox(point.x - MarkerSize, point.y - MarkerSize, 2 * MarkerSize, 2 * MarkerSize);
	}

	@Override
	public boolean intersects(CadLine line) {
		return line.intersects(getRect());
	}
}
