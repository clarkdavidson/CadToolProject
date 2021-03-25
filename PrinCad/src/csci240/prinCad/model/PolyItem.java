package csci240.prinCad.model;

import java.util.ArrayList;

import csci240.prinCad.fx3d.Map3d;
import javafx.scene.Node;
import javafx.scene.canvas.GraphicsContext;

public class PolyItem extends CadItem {

	ArrayList<CadPoint> points = new ArrayList<CadPoint>();

	public PolyItem(ArrayList<CadPoint> points) {
		this.points = points;

	}

	public PolyItem(String info) {
		String[] tokens = info.split(";");
		for (int i = 0; i < tokens.length; i++) {
			String item = tokens[i];
			item = item.trim();
			String[] cadPoints = item.split(" ");
			double x = Double.parseDouble(cadPoints[0]);
			double y = Double.parseDouble(cadPoints[1]);
			CadPoint a = new CadPoint(x, y);
			points.add(a);
		}

	}

	@Override
	public void draw(GraphicsContext gc) {
		for (int index = 1; index < points.size(); index++) {
			CadPoint p = points.get(index);
			CadPoint d = points.get(index - 1);
			double x = p.X();
			double o = d.X();
			double q = d.Y();
			double y = p.Y();
			gc.strokeLine(x, y, o, q);
		}

	}

	@Override
	public String save() {
		String s = "";
		for (int i = 0; i < points.size(); i++) {

			CadPoint cadPoint = points.get(i);
			double x = cadPoint.X();
			double y = cadPoint.Y();
			String format = String.format("%1$f %2$f;", x, y);
			s += format;
		}
		return s;
	}

	@Override
	public CadBox getRect() {
		CadPoint cadPoint = points.get(0);
		CadPoint cadPoint1 = points.get(1);
		double x = cadPoint.X();
		double y = cadPoint.Y();
		double o = cadPoint1.X();
		double q = cadPoint1.Y();

		return new CadBox(x, y, o, q);
	}

	@Override
	public boolean intersects(CadLine line) {
		// Check each line
		for (int i = 1; i < points.size(); i++) {
			CadPoint p0 = points.get(i);
			CadPoint p1 = points.get(i - 1);
			if (line.intersects(p0, p1)) {
				return true;
			}
		}
		return false;
	}
	
	@Override
	public CadItem Copy() {
		return(new PolyItem(points));
	}

	@Override
	public Node get3dObject(Map3d map) {
		return null;
	}

}
