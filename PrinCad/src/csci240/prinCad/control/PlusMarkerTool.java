package csci240.prinCad.control;

import csci240.prinCad.model.CadItem;
import csci240.prinCad.model.CadPoint;
import csci240.prinCad.model.PlusMarkerItem;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;

public class PlusMarkerTool extends MarkerTool {

	@Override
	protected void Draw(CanvasToolInterface canvas, MouseEvent e) {
		double x = e.getX();
		double y = e.getY();
		
		canvas.getGraphicsContext2D().strokeLine(x - MarkerSize, y, x + MarkerSize, y);
		canvas.getGraphicsContext2D().strokeLine(x, y - MarkerSize, x, y + MarkerSize);
		}

	@Override
	protected CadItem createMarkerItem(CadPoint point) {
		return new PlusMarkerItem(point);
	}
	

}
