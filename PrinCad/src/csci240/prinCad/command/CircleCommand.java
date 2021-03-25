package csci240.prinCad.command;

import csci240.prinCad.control.CircleMarkerTool;
import csci240.prinCad.util.Log;
import javafx.event.ActionEvent;

public class CircleCommand extends CommandHandler {

	public CircleCommand(CanvasCommandInterface canvas) {
		super(canvas);
		
	}

	@Override
	public void action(ActionEvent e) {

		Log.info("Circle Tool Selected");
		
		getCanvas().setActiveTool(new CircleMarkerTool());
	}
	

}
