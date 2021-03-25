package csci240.prinCad.command;

import csci240.prinCad.control.SquareMarker;
import csci240.prinCad.util.Log;
import javafx.event.ActionEvent;

public class SquareCommand extends CommandHandler{
	
	public SquareCommand(CanvasCommandInterface canvas) {
		super(canvas);
	}

	@Override
	public void action(ActionEvent e) {
		Log.info("Square Tool Selected");
		
		getCanvas().setActiveTool(new SquareMarker());
		
	}
	
	

}
