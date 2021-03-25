package csci240.prinCad.control;

import csci240.prinCad.model.BoxItem;
import csci240.prinCad.model.CadBox;
import csci240.prinCad.model.CadItem;
import csci240.prinCad.model.CadPoint;
import csci240.prinCad.model.SquareItem;
import javafx.scene.input.MouseEvent;

public class BoxMarkerTool extends MarkerTool {

	@Override
	protected void Draw(CanvasToolInterface canvas, MouseEvent e) {
		double x = e.getX();
		double y = e.getY();

		canvas.getGraphicsContext2D().strokeRect(x, y, MarkerSize, MarkerSize);

	}

	@Override
	protected CadItem createMarkerItem(CadPoint point) {
	return new BoxItem(point);
	}
}
