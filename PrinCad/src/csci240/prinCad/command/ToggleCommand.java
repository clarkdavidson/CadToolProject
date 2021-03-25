package csci240.prinCad.command;

import javafx.event.ActionEvent;
import csci240.prinCad.util.Log;

public class ToggleCommand extends CommandHandler {
	
	//String a = MainForm.appSettings.getSelectionTool();

	public ToggleCommand(CanvasCommandInterface canvas) {
		super(canvas);
	}

	@Override
	public void action(ActionEvent e) {
		Log.info("Toggle Selection Button Pressed");
		getCanvas().toggleSelectionType();
	}
	
}
