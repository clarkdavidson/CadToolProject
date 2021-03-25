package csci240.prinCad.control;

import csci240.prinCad.model.CadItem;
import csci240.prinCad.model.CadPoint;
import csci240.prinCad.ui.PrinCanvas;
import javafx.scene.Cursor;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public abstract class MarkerTool implements CadTool {

	
	protected final double MarkerSize = 4;
	
	//Mouse Movement properties
	boolean _activeMouse;
	
	//Only place a marker when the primary mouse button is clicked
	@Override
	public void onMousePressed(CanvasToolInterface canvas, MouseEvent e) {
		if(e.getButton()==MouseButton.PRIMARY) {
			canvas.setCursor(Cursor.CROSSHAIR);
			_activeMouse = true;
		}
	}
	@Override
	public void onMouseDrag(CanvasToolInterface canvas, MouseEvent e) {
		
	}
	//Actually place the marker when the user has released the mouse button
	@Override
	public void onMouseRelease(CanvasToolInterface canvas, MouseEvent e){
		if(_activeMouse) {
			_activeMouse=false;
			canvas.setCursor(Cursor.DEFAULT);
			canvas.getGraphicsContext2D().setStroke(Color.ORANGERED);
			canvas.getGraphicsContext2D().setLineWidth(0);
			
			Draw(canvas, e);
			
			canvas.reset(createMarkerItem(new CadPoint(e.getX(), e.getY())));
		}
	}
	
	
	//Create the marker item
	protected abstract void Draw(CanvasToolInterface canvas, MouseEvent e);
	
	protected abstract CadItem createMarkerItem(CadPoint point);
	
		
}
