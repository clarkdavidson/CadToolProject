package csci240.prinCad.command;

import csci240.prinCad.control.PolyTool;
import csci240.prinCad.util.Log;
import javafx.event.ActionEvent;

public class PolyCommand extends CommandHandler {

	public PolyCommand(CanvasCommandInterface canvas) {
		super(canvas);
	}

	@Override
	public void action(ActionEvent e) {
		Log.info("Poly Tool Selected");
		getCanvas().setActiveTool(new PolyTool());
	}

}
