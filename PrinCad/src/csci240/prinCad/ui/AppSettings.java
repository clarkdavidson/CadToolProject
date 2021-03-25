package csci240.prinCad.ui;

import java.util.Properties;

import csci240.prinCad.util.Log;
import csci240.prinCad.util.Log.LoggingLevel;
import javafx.scene.paint.Color;

import java.io.*;

public class AppSettings implements AppInterface {

	// Create private instance of Properties named appSettings
	private Properties appSettings = new Properties();

	// look for file to load settings
	// If not found load defaults
	public AppSettings() {
		try {
			FileInputStream in = new FileInputStream("windowSettings.properties");
			appSettings.load(in);
			in.close();
		} catch (IOException e) {
			setSceneWidth(600);
			setSceneHeight(400);
			setSceneColor(Color.DARKGOLDENROD);
			setCanvasWidth(275);
			setCanvasHeight(225);
			setCanvasColor(Color.BLACK);
			setLoggingLevel(LoggingLevel.Information);
			setUndoSize(5);
		}
	}

	public void saveSettings() throws IOException {
		// Write Settings to a file
		FileOutputStream out = new FileOutputStream("windowSettings.properties");
		appSettings.store(out, null);
		out.close();
	}

	public void setSceneWidth(int sceneWidth) {
		appSettings.setProperty("SceneWidth", Integer.toString(sceneWidth));
	}

	public int getSceneWidth() {
		return getint("SceneWidth", 300);

	}

	private int getint(String key, int defaultvalue) {
		int i = defaultvalue;
		String s = appSettings.getProperty(key);
		try {
			i = Integer.parseInt(s);
		} catch (Exception e) {
			i = defaultvalue;
		}
		return i;
	}

	public int getSceneHeight() {
		return getint("SceneHeight", 300);
	}

	public void setSceneHeight(int sceneHeight) {
		appSettings.setProperty("SceneHeight", Integer.toString(sceneHeight));
	}

	public Color getSceneBG() {
		return Color.web(appSettings.getProperty("SceneBackgroundColor"));
	}

	public void setSceneColor(Color bgColor) {
		appSettings.setProperty("SceneBackgroundColor", bgColor.toString());
	}

	public int getCanvasWidth() {
		return Integer.parseInt(appSettings.getProperty("CanvasWidth"));
	}

	public void setCanvasWidth(int canvasWidth) {
		appSettings.setProperty("CanvasWidth", Integer.toString(canvasWidth));
	}

	public void setCanvasHeight(int canvasHeight) {
		appSettings.setProperty("CanvasHeight", Integer.toString(canvasHeight));
	}

	public int getCanvasHeight() {
		return Integer.parseInt(appSettings.getProperty("CanvasHeight"));
	}

	public Color getCanvasColor() {
		return Color.web(appSettings.getProperty("CanvasBackgroundColor"));
	}

	public void setCanvasColor(Color canvasColor) {
		appSettings.setProperty("CanvasBackgroundColor", canvasColor.toString());
	}

	public LoggingLevel getLoggingLevel() {
		String s = appSettings.getProperty("LoggingLevel");
		return Log.LoggingLevel.valueOf(s);
	}

	public void setLoggingLevel(LoggingLevel level) {
		appSettings.setProperty("LoggingLevel", level.toString());
	}
	
	public void setUndoSize(int undoSize) {
		appSettings.setProperty("UndoSize", Integer.toString(undoSize));
	}
	
	public int getUndoSize() {
		return Integer.parseInt(appSettings.getProperty("UndoSize"));
	}

}
