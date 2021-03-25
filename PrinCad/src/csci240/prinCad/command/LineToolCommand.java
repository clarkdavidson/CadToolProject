package csci240.prinCad.command;

import csci240.prinCad.control.LineToolMarker;
import csci240.prinCad.util.Log;
import javafx.event.ActionEvent;

public class LineToolCommand extends CommandHandler{

	public LineToolCommand(CanvasCommandInterface canvas) {
		super(canvas);

	}

	@Override
	public void action(ActionEvent e) {
		Log.info("Line Tool Used");
		getCanvas().setActiveTool(new LineToolMarker());
	}
}
