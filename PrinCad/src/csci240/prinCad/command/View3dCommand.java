package csci240.prinCad.command;

import csci240.prinCad.util.Log;
import javafx.event.ActionEvent;
import csci240.prinCad.fx3d.View3dScene;
import csci240.prinCad.model.ModelManager;

public class View3dCommand extends CommandHandler {
	public View3dCommand(CanvasCommandInterface canvas) {
		super(canvas);
	}

	@Override
	public void action(ActionEvent e) {
		Log.info("3d View Button Pressed");
		_canvas=getCanvas();
		ModelManager _model=_canvas.getModel();
		View3dScene.Show(_model);
		
	}
}
