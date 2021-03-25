package csci240.prinCad.control;

import csci240.prinCad.model.LineItem;
import javafx.scene.Cursor;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class LineToolMarker implements CadTool {
	boolean _activeMouse;
	double _xPivot, _yPivot, _xEnd, _yEnd;

	@Override
	public void onMousePressed(CanvasToolInterface canvas, MouseEvent e) {
		if (e.getButton() == MouseButton.PRIMARY) {
			double x = e.getX();
			double y = e.getY();
			_xPivot = x;
			_yPivot = y;
			_xEnd = x;
			_yEnd = y;
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
			_xEnd = e.getX();
			_yEnd = e.getY();
			canvas.getGraphicsContext2D().strokeLine(_xPivot, _yPivot, _xEnd, _yEnd);
		}
	}

	@Override
	public void onMouseRelease(CanvasToolInterface canvas, MouseEvent e) {
		if (_activeMouse) {
			_activeMouse = false;
			canvas.setCursor(Cursor.DEFAULT);

			canvas.reset(new LineItem(_xPivot, _yPivot, _xEnd, _yEnd));
		}
	}

}
