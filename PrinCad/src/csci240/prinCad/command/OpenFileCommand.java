package csci240.prinCad.command;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import csci240.prinCad.util.Log;
import javafx.event.ActionEvent;
import javafx.stage.FileChooser;
import javafx.stage.Window;

public class OpenFileCommand extends CommandHandler {
	//Constructor
	public OpenFileCommand(CanvasCommandInterface canvas) {
		super(canvas);
	}

	//Handle action event
	@Override
	public void action(ActionEvent e) {
		Log.info("Handle Open File Event");
		
		try {
			Window stage = getCanvas().getScene().getWindow();
			
			FileChooser fileChooser = new FileChooser();
			fileChooser.setTitle("Load Model");
			File file = fileChooser.showOpenDialog(stage);
			
			FileReader fw = new FileReader(file);
			BufferedReader bw = new BufferedReader(fw);
			
			getCanvas().loadFile(bw);
			String openfile = file.getPath();
			_canvas.setFile(openfile);
			
		}
		catch(Exception ex) {
			Log.error("", ex);
		}
	}
	
}
