package csci240.prinCad.command;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import csci240.prinCad.util.Log;
import javafx.event.ActionEvent;
import javafx.stage.FileChooser;
import javafx.stage.Window;

public class SaveAsFileCommand extends CommandHandler {
	// Constructor

	public SaveAsFileCommand(CanvasCommandInterface canvas) {
		super(canvas);
	}

	// Handle Action Event
	@Override
	public void action(ActionEvent e) {
		Log.info("Handle Save As File Action");
		
		try {
		Window stage = getCanvas().getScene().getWindow();
		
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Save As Model");
		File file = fileChooser.showSaveDialog(stage);
		
		FileWriter fw = new FileWriter(file);
		BufferedWriter bw = new BufferedWriter(fw);
		PrintWriter out = new PrintWriter(bw,true);
		_canvas.saveToFile(out);
		_canvas.setFile(file.getPath());
		
		out.flush();
		out.close();
		
		}catch (Exception ex) {
			Log.error("", ex);
		}
		}

}
