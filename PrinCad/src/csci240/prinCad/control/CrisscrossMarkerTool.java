package csci240.prinCad.control;

import csci240.prinCad.model.CadItem;
import csci240.prinCad.model.CadPoint;
import csci240.prinCad.model.ExMarkerItem;
import javafx.scene.input.MouseEvent;

public class CrisscrossMarkerTool extends MarkerTool {

	@Override
	protected void Draw(CanvasToolInterface canvas, MouseEvent e) {
		double x = e.getX();
		double y = e.getY();

		canvas.getGraphicsContext2D().strokeLine(x, y, x + MarkerSize, y - MarkerSize);
		canvas.getGraphicsContext2D().strokeLine(x, y - MarkerSize, x + MarkerSize, y);

	}

	@Override
	protected CadItem createMarkerItem(CadPoint point) {
		return new ExMarkerItem(point);
	}

}
