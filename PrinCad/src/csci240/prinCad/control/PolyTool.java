package csci240.prinCad.control;

import java.util.ArrayList;

import csci240.prinCad.model.CadPoint;
import csci240.prinCad.model.PolyItem;
import javafx.scene.Cursor;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class PolyTool implements CadTool {

	boolean _activeMouse;
	double _xPivot, _yPivot, _xEnd, _yEnd;

	ArrayList<CadPoint> points = new ArrayList<CadPoint>();

	@Override
	public void onMousePressed(CanvasToolInterface canvas, MouseEvent e) {

		if (_activeMouse) {
		}

	}

	@Override
	public void onMouseDrag(CanvasToolInterface canvas, MouseEvent e) {
		if (_activeMouse) {
			double x = Math.min(_xPivot, _xEnd) - 1;
			double y = Math.min(_yPivot, _yEnd) - 1;
			double w = Math.abs(_xEnd - _xPivot) + 2;
			double h = Math.abs(_yEnd - _yPivot) + 2;
			canvas.draw();

			_xEnd = e.getX();
			_yEnd = e.getY();

			canvas.getGraphicsContext2D().strokeLine(_xPivot, _yPivot, _xEnd, _yEnd);
			for (int index = 1; index < points.size(); index++) {
				CadPoint p = points.get(index);
				CadPoint d = points.get(index - 1);
				x = p.X();
				double o = d.X();
				double q = d.Y();
				y = p.Y();
				canvas.getGraphicsContext2D().strokeLine(x, y, o, q);
			}
		}
	}

	@Override
	public void onMouseRelease(CanvasToolInterface canvas, MouseEvent e) {
		if (e.getButton() == MouseButton.PRIMARY) {
			double x = e.getX();
			double y = e.getY();
			_xPivot = x;
			_yPivot = y;
			_xEnd = x;
			_yEnd = y;
			canvas.getGraphicsContext2D().setStroke(Color.ORANGERED);
			canvas.getGraphicsContext2D().setLineWidth(0);
			canvas.setCursor(Cursor.CROSSHAIR);
			_activeMouse = true;
			points.add(new CadPoint(x, y));
		}else if (e.getButton() == MouseButton.SECONDARY) {
			_activeMouse = false;
			canvas.setCursor(Cursor.DEFAULT);
			canvas.reset(new PolyItem(points));
		}

	}

}
