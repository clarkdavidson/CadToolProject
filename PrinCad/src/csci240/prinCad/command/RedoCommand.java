package csci240.prinCad.command;


import csci240.prinCad.model.ModelManager;
import csci240.prinCad.util.Log;
import javafx.event.ActionEvent;

public class RedoCommand extends CommandHandler {

	public RedoCommand(CanvasCommandInterface canvas) {
		super(canvas);
	}


	@Override
	public void action(ActionEvent e) {
		Log.info("Redo Button Pressed");
		_canvas = getCanvas();
		ModelManager _model = _canvas.getModel();
		_model.redo();
		_canvas.draw();
	}
}
