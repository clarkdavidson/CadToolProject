package csci240.prinCad.command;

import csci240.prinCad.util.Log;
import javafx.event.ActionEvent;

public class NewFileCommand extends CommandHandler {

	// Constructor
	public NewFileCommand(CanvasCommandInterface canvas) {
		super(canvas);
	}

	// Handle action event
	@Override
	public void action(ActionEvent e) {
		Log.info("Handle New File Event");
		getCanvas();
		_canvas.newFile();
		_canvas.setFile("");
	}

}
