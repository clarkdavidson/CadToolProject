package csci240.prinCad.command;

import csci240.prinCad.control.CrisscrossMarkerTool;
import csci240.prinCad.util.Log;
import javafx.event.ActionEvent;

public class CrisscrossMarkerCommand extends CommandHandler {

	public CrisscrossMarkerCommand(CanvasCommandInterface canvas) {
		super(canvas);
	}

	@Override
	public void action(ActionEvent e) {
		Log.info("Crisscross Marker Pressed");
		
		getCanvas().setActiveTool(new CrisscrossMarkerTool());
	}
	
	

}
