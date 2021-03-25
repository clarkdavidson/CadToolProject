package csci240.prinCad.command;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import csci240.prinCad.control.CadTool;
import csci240.prinCad.model.ModelManager;
import javafx.scene.Scene;

public interface CanvasCommandInterface {

	public void setActiveTool(CadTool activeTool);

	public void draw();

	public void newFile();

	public void setFile(String string);

	public Scene getScene();
	
	public void loadFile(BufferedReader in) throws IOException;
	
	public void saveToFile(PrintWriter out);
	
	public String getFile();
	
	public void toggleSelectionType();

	public ModelManager getModel();
	
	public void handlePropertiesCommand();

}
