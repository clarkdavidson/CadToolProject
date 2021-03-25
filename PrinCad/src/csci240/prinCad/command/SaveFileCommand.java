package csci240.prinCad.command;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;

import csci240.prinCad.util.Log;
import javafx.event.ActionEvent;
import javafx.stage.FileChooser;
import javafx.stage.Window;

public class SaveFileCommand extends CommandHandler {

	// Constructor
	public SaveFileCommand(CanvasCommandInterface canvas) {
		super(canvas);
	}

	// Handle Action Event
	@Override
	public void action(ActionEvent e) {
		Log.info("Handle Save File Event");

		try {
			getCanvas();
			String openFile = _canvas.getFile();
			if (openFile != null) {
				
				// Open current file
				File current = new File (openFile);
				// Check to see if it exists
				if (current.exists()) {
					// if exists, rename to .bak
					File backup = new File(openFile + ".bak");
					if(backup.exists()) {
						// If .bak exists delete first
						backup.delete();
					}
					current.renameTo(backup);
				}
				
			}
			if (openFile == null) {
				Window stage = getCanvas().getScene().getWindow();

				FileChooser fileChooser = new FileChooser();
				fileChooser.setTitle("Save Model");
				File file = fileChooser.showSaveDialog(stage);

				FileWriter fw = new FileWriter(file);
				BufferedWriter bw = new BufferedWriter(fw);
				PrintWriter out = new PrintWriter(bw, true);

				_canvas.saveToFile(out);
				_canvas.setFile(file.getPath());

				out.flush();
				out.close();
			} else {
				FileWriter fw = new FileWriter(openFile);
				BufferedWriter bw = new BufferedWriter(fw);
				PrintWriter out = new PrintWriter(bw, true);
				_canvas.saveToFile(out);
			}
		} catch (Exception ex) {
			Log.error("", ex);
		}

	}

}
