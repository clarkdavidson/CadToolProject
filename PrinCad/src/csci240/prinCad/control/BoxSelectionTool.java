package csci240.prinCad.control;

import javafx.scene.Cursor;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import csci240.prinCad.model.ModelManager;
import csci240.prinCad.model.CadBox;

public class BoxSelectionTool implements CadTool {
	boolean _activeMouse;
	double _xPivot, _yPivot, _xEnd, _yEnd;

	public void onMousePressed(CanvasToolInterface canvas, MouseEvent e) {

		// Left mouse button click handler
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

	// Covers the movement of the mouse.
	public void onMouseDrag(CanvasToolInterface canvas, MouseEvent e) {
		if (_activeMouse) {
			double x = Math.min(_xPivot, _xEnd) - 1;
			double y = Math.min(_yPivot, _yEnd) - 1;
			double w = Math.abs(_xEnd - _xPivot) + 2;
			double h = Math.abs(_yEnd - _yPivot) + 2;
			canvas.draw();
			_xEnd = e.getX();
			_yEnd = e.getY();
			x = Math.min(_xPivot, _xEnd);
			y = Math.min(_yPivot, _yEnd);
			w = Math.abs(_xEnd - _xPivot);
			h = Math.abs(_yEnd - _yPivot);

			canvas.getGraphicsContext2D().strokeRect(x, y, w, h);
		}
	}

	// Covers the release of the mouse button.
	public void onMouseRelease(CanvasToolInterface canvas, MouseEvent e) {
		if (_activeMouse) {
			ModelManager _model = canvas.getModel();
			double xMin = Math.min(_xPivot, _xEnd);
			double yMin = Math.min(_yPivot, _yEnd);
			double xMax = Math.max(_xPivot, _xEnd);
			double yMax = Math.max(_yPivot, _yEnd);
			_model.select(new CadBox(xMin, yMin, xMax, yMax));
			canvas.draw();
			_activeMouse = false;
			canvas.setCursor(Cursor.DEFAULT);
		}
	}

}
