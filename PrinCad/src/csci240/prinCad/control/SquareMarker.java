package csci240.prinCad.control;

import csci240.prinCad.model.SquareItem;
import javafx.scene.Cursor;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class SquareMarker implements CadTool {

	boolean _activeMouse;
	double _xPivot, _yPivot, _xEnd, _yEnd;
	double _x,_y,_w,_h;
	@Override
	public void onMousePressed(CanvasToolInterface canvas, MouseEvent e) {

		//Left mouse button click handler 
		if (e.getButton() == MouseButton.PRIMARY) {
			double x = e.getX();
			double y = e.getY();
			_xPivot = x;
			_yPivot = y;
			_xEnd = x;
			_yEnd = y;
			_x = x;
			_y = y;
			_w = 0.0;
			_h = 0.0;
			_activeMouse = true;
			canvas.getGraphicsContext2D().setStroke(Color.ORANGERED);
			canvas.getGraphicsContext2D().setLineWidth(0);
			canvas.setCursor(Cursor.CROSSHAIR);
		}
	}
	
		//Covers the movement of the mouse.
	@Override
	public void onMouseDrag(CanvasToolInterface canvas, MouseEvent e) {
		if (_activeMouse) {
			canvas.draw();
			_x = Math.min(_xPivot, _xEnd) - 1;
			_y = Math.min(_yPivot, _yEnd) - 1;
			_w = Math.abs(_xEnd - _xPivot) + 2;
			_h = Math.abs(_yEnd - _yPivot) + 2;
			_xEnd = e.getX();
			_yEnd = e.getY();

			canvas.getGraphicsContext2D().strokeRect(_x, _y, _w, _h);
		}
	}
	
	//Covers the release of the mouse button.
	@Override
	public void onMouseRelease(CanvasToolInterface canvas, MouseEvent e) {
		if (_activeMouse) {
			_activeMouse = false;
			canvas.setCursor(Cursor.DEFAULT);
			//double x = Math.min(_xPivot, _xEnd);
			//double y = Math.min(_yPivot, _yEnd);
			//double w = Math.abs(_xEnd - _xPivot);
			//double h = Math.abs(_yEnd - _yPivot);
			
			canvas.reset(new SquareItem(_x,_y,_w,_h));
			
		}
	}

}
