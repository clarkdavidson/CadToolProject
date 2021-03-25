package csci240.prinCad.ui;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import javafx.scene.paint.Color;

public class EmmaWay implements AppInterface {
	private int _CanvasWidth = 275;
	private int _SceneHeight = 250;
	private int _CanvasHeight = 225;
	private Color _SceneBackgroundColor = Color.DARKGOLDENROD;
	private int _SceneWidth = 300;
	private Color _CanvasBackgroundColor = Color.BLACK;

	public EmmaWay() throws FileNotFoundException {
		File Settings = new File("windowSettings.properties");

		if (Settings.exists()) {
			System.out.print("Exists" + "\n");
			Scanner s = new Scanner(Settings);
			while (s.hasNext()) {
				String Line = s.next();
				String[] tokens = Line.split("=");
				System.out.println(Line);
				System.out.println(tokens[0]);
				System.out.println(tokens[1]);

				switch (tokens[0]) {
				case "CanvasWidth":
					_CanvasWidth = Integer.parseInt(tokens[1]);
					break;
				case "SceneHeight":
					_SceneHeight = Integer.parseInt(tokens[1]);
					break;
				case "CanvasHeight":
					_CanvasHeight = Integer.parseInt(tokens[1]);
					break;
				case "SceneBackgroundColor":
					_SceneBackgroundColor = Color.web(tokens[1]);
					break;
				case "SceneWidth":
					_SceneWidth = Integer.parseInt(tokens[1]);
					break;
				case "CanvasBackgroundColor":
					_CanvasBackgroundColor = Color.web(tokens[1]);
					break;
				}

			}
			s.close();
		} else {
			System.out.println("Does Not Exist");
		}
	}

	public void saveSettings() throws IOException {

		System.out.print("Exists" + "\n");
		BufferedWriter out = new BufferedWriter(new FileWriter("windowSettings.properties"));
		out.write("CanvasWidth=" + Integer.toString(_CanvasWidth));
		out.newLine();
		out.write("SceneHeight=" + Integer.toString(_SceneHeight));
		out.newLine();
		out.write("CanvasHeight=" + Integer.toString(_CanvasHeight));
		out.newLine();
		out.write("SceneBackgroundColor=" + _SceneBackgroundColor.toString());
		out.newLine();
		out.write("SceneWidth=" + Integer.toString(_SceneWidth));
		out.newLine();
		out.write("CanvasBackgroundColor=" + _CanvasBackgroundColor.toString());
		out.close();
	}

	public void setSceneWidth(int scenewidth) {
		_SceneWidth = scenewidth;
	}

	public int getSceneWidth() {
		return _SceneWidth;
	}

	public int getSceneHeight() {
		return _SceneHeight;
	}

	public void setSceneHeight(int sceneHeight) {
		_SceneHeight = sceneHeight;
	}

	public Color getSceneBG() {

		return _SceneBackgroundColor;
	}

	public void setSceneColor(Color bgColor) {
		_SceneBackgroundColor = bgColor;
	}

	public int getCanvasWidth() {
		return _CanvasWidth;
	}

	public void setCanvasWidth(int canvasWidth) {
		_CanvasWidth = canvasWidth;
	}

	public void setCanvasHeight(int canvasHeight) {
		_CanvasHeight = canvasHeight;
	}

	public int getCanvasHeight() {
		return _CanvasHeight;
	}

	public Color getCanvasColor() {
		return _CanvasBackgroundColor;
	}

	public void setCanvasColor(Color canvasColor) {
		_CanvasBackgroundColor = canvasColor;
	}

}
