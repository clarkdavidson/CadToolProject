package csci240.prinCad.ui;

import java.io.IOException;

import javafx.scene.paint.Color;

public interface AppInterface {
	//All methods to store and load settings
	public void saveSettings()throws IOException;
	public void setSceneWidth(int sceneWidth);
	public int getSceneWidth();
	public int getSceneHeight();
	public void setSceneHeight(int sceneHeight);
	public Color getSceneBG();
	public void setSceneColor(Color bgColor);
	public int getCanvasWidth();
	public void setCanvasWidth(int canvasWidth);
	public void setCanvasHeight(int canvasHeight);
	public int getCanvasHeight();
	public Color getCanvasColor();
	public void setCanvasColor(Color canvasColor);  
}
