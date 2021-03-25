package csci240.prinCad.command;

import csci240.prinCad.control.PlusMarkerTool;
import csci240.prinCad.util.Log;
import javafx.event.ActionEvent;

public class PlusMarkerCommand extends CommandHandler {

	public PlusMarkerCommand(CanvasCommandInterface canvas) {
		super(canvas);
		
	}

	@Override
	public void action(ActionEvent e) {
		Log.info("Plus Marker Pressed");
		
		getCanvas().setActiveTool(new PlusMarkerTool());
		
	}

}
