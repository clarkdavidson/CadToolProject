package csci240.prinCad.ui;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

import csci240.prinCad.command.CanvasCommandInterface;
import csci240.prinCad.util.Log;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;

public class MainForm extends Application {

	public static AppSettings appSettings;
	
	//Manager of File Commands
	private FileManager _fileManager;
	//Manager of Edit Tab/Commands
	private EditManager _editManager;
	//Manage of CAD Tool
	private CadManager _cadManager;

	public static void main(String[] args) throws IOException {


		appSettings = new AppSettings();
		
		Log.info("PrinCad begin execution");

		// Launch the javaFX application
		launch(args);
		
		Log.info("PrinCad end execution");

		// Test code to change the settings on next boot up

		appSettings.saveSettings();
		
		
	}

	// Override the start
	@Override
	public void start(Stage primaryStage) throws IOException {
		// Create drawing canvas
		PrinCanvas canvas = new PrinCanvas(appSettings.getCanvasWidth(), appSettings.getCanvasHeight());
		
		//Create File Manager
		_fileManager = new FileManager(canvas);
		//Create Edit Manager
		_editManager = new EditManager(canvas);
		//Create CAD Tool Choice
		_cadManager = new CadManager(canvas);
		
		
		// Create the typical monolithic border layout
		// Attach canvas to center of layout
		BorderPane pane = new BorderPane(canvas);
		Color sceneBackgroundColor=appSettings.getSceneBG();
		pane.setStyle(FormatStyleColor(sceneBackgroundColor));
		
		//Create menu bar
		MenuBar mb = new MenuBar();
		pane.setTop(mb);
		
		ObservableList<Menu> menus=mb.getMenus();
		
		//add file to menu bar
		Menu fileMenu = _fileManager.buildMenu();
		Menu editMenu = _editManager.buildMenu();
		Menu cadTool = _cadManager.buildMenu();
		menus.add(fileMenu);
		menus.add(editMenu);
		menus.add(cadTool);	
		
		//Create Vbox to hold the buttons
		VBox Vbox = new VBox(5);
		Vbox.setPadding(new Insets(10));
		Vbox.setAlignment(Pos.TOP_CENTER);
		pane.setRight(Vbox);
		
		//Create Left VBox
		VBox Lvbox = new VBox(5);
		Lvbox.setPadding(new Insets(10));
		Lvbox.setAlignment(Pos.TOP_CENTER);
		pane.setLeft(Lvbox);

		//Add buttons to bar
		ObservableList<Node>nodes=Vbox.getChildren();
		_fileManager.addButtonsToBar(nodes);
		_editManager.addButtonsToBar(nodes);
		
		//Add buttons to left bar
		ObservableList<Node>nodes1=Lvbox.getChildren();
		_cadManager.addButtonsToBar(nodes1);

		Scene scene = new Scene(pane, appSettings.getSceneWidth(), appSettings.getSceneHeight(),
				sceneBackgroundColor);
		
		//Apply application styles
		File file = new File("AppStyles.css");
		if(!file.exists()) {
			Log.info(file.toString()+" does not exist");
		}
		else {
			URL url = file.toURI().toURL();
			scene.getStylesheets().add(url.toExternalForm());
		}

		// Attach scene to stage
		primaryStage.setScene(scene);
		primaryStage.setTitle("CSCI 240 PrinCad Project");
		primaryStage.show();
	}
	
	private String FormatStyleColor(Color color) {
		String rx = String.format("%2X", Math.round(color.getRed()*255.0));
		String gx = String.format("%2X", Math.round(color.getGreen()*255.0));
		String bx = String.format("%2X", Math.round(color.getBlue()*255.0));
		
		String fx ="-fx-background-color:#"+rx+gx+bx+";";
		
		return fx;
		
	}
	
}