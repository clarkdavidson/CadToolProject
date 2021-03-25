package csci240.prinCad.command;

import csci240.prinCad.model.ModelManager;
import csci240.prinCad.util.Log;
import javafx.event.ActionEvent;

public class DeleteCommand extends CommandHandler {

	public DeleteCommand(CanvasCommandInterface canvas) {
		super(canvas);
	}

	@Override
	public void action(ActionEvent e) {
		//get canvas to access getModel() method
		_canvas = getCanvas();
		//assign the model in use to _model
		ModelManager _model = _canvas.getModel();	
		_model.deleteSelected();
		_canvas.draw();
		Log.info("Delete Button Pressed");
	}

}
