package csci240.prinCad.control;

import javafx.scene.input.MouseEvent;

public interface CadTool {
	public void onMousePressed(CanvasToolInterface canvas, MouseEvent e);
	public void onMouseDrag(CanvasToolInterface canvas, MouseEvent e);
	public void onMouseRelease(CanvasToolInterface canvas, MouseEvent e);
	
}
