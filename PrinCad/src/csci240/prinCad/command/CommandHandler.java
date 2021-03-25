package csci240.prinCad.command;

import javafx.event.ActionEvent;

public abstract class CommandHandler {
	CanvasCommandInterface _canvas;

	public final CanvasCommandInterface getCanvas() {
		return _canvas;
	}

	public CommandHandler(CanvasCommandInterface canvas) {
		_canvas = canvas;
	}
	
	public abstract void action(ActionEvent e);

}
