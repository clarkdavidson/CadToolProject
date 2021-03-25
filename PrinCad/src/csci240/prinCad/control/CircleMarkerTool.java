package csci240.prinCad.control;

import csci240.prinCad.model.CircleItem;
import javafx.scene.Cursor;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class CircleMarkerTool implements CadTool {
	boolean _activeMouse;
	double _xCenter, _yCenter, _r, _yEnd;
	
	@Override
	public void onMousePressed(CanvasToolInterface canvas, MouseEvent e) {
		if (e.getButton() == MouseButton.PRIMARY) {
			double x = e.getX();
			double y = e.getY();
			_xCenter = x;
			_yCenter = y;
			_r = 0;
			_activeMouse = true;
			canvas.getGraphicsContext2D().setStroke(Color.ORANGERED);
			canvas.getGraphicsContext2D().setLineWidth(0);
			canvas.setCursor(Cursor.CROSSHAIR);

		}
	}

	@Override
	public void onMouseDrag(CanvasToolInterface canvas, MouseEvent e) {
		if (_activeMouse) {
			canvas.draw();
			double dx, dy;
			dx = _xCenter - e.getX();
			dy = _yCenter - e.getY();
			_r = Math.sqrt(dx * dx + dy * dy);

			canvas.getGraphicsContext2D().strokeOval(_xCenter - _r, _yCenter - _r, 2 * _r, 2 * _r);
			;
		}
	}

	@Override
	public void onMouseRelease(CanvasToolInterface canvas, MouseEvent e) {
		if (_activeMouse) {
			_activeMouse = false;
			canvas.setCursor(Cursor.DEFAULT);
			canvas.reset(new CircleItem(_xCenter,_yCenter,_r));
		}

	}

}
