package csci240.prinCad.command;
import csci240.prinCad.util.Log;
import javafx.event.ActionEvent;

public class PropertiesCommand extends CommandHandler {

	public PropertiesCommand(CanvasCommandInterface canvas) {
		super(canvas);
	}

	@Override
	public void action(ActionEvent e) {
		Log.info("Properties Button Pressed");
		
		_canvas.handlePropertiesCommand();
		_canvas.draw();
	}
	

}
