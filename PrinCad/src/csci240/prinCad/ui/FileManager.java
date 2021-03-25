package csci240.prinCad.ui;

import csci240.prinCad.command.CanvasCommandInterface;
import csci240.prinCad.command.NewFileCommand;
import csci240.prinCad.command.OpenFileCommand;
import csci240.prinCad.command.SaveAsFileCommand;
import csci240.prinCad.command.SaveFileCommand;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;

public class FileManager {

	// File commands
	private NewFileCommand _newFileCommand;
	private OpenFileCommand _openFileCommand;
	private SaveFileCommand _SaveFileCommand;
	private SaveAsFileCommand _SaveAsFileCommand;

	// Constructor

	public FileManager(CanvasCommandInterface canvas) {
		_newFileCommand = new NewFileCommand(canvas);
		_openFileCommand = new OpenFileCommand(canvas);
		_SaveFileCommand = new SaveFileCommand(canvas);
		_SaveAsFileCommand = new SaveAsFileCommand(canvas);

	}

	// Build menu
	public Menu buildMenu() {
		// Create Menu items
		MenuItem miNew = new MenuItem("New");
		miNew.setOnAction(e -> _newFileCommand.action(e));

		MenuItem miOpen = new MenuItem("Open");
		miOpen.setOnAction(e -> _openFileCommand.action(e));

		MenuItem miSave = new MenuItem("Save");
		miSave.setOnAction(e -> _SaveFileCommand.action(e));

		MenuItem miSaveAs = new MenuItem("Save As");
		miSaveAs.setOnAction(e -> _SaveAsFileCommand.action(e));

		// Create a menu
		Menu fileMenu = new Menu("File");
		ObservableList<MenuItem> fileMenuItems = fileMenu.getItems();

		// Add menu items to menu
		fileMenuItems.add(miNew);
		fileMenuItems.add(miOpen);
		fileMenuItems.add(miSave);
		fileMenuItems.add(miSaveAs);

		return fileMenu;
	}

	// Add buttons to bar
	public void addButtonsToBar(ObservableList<Node> nodes) {

		// Create buttons
		Button nfb = new Button();
		nfb.setMinWidth(80);
		nfb.setText("Set Text");
		nfb.setOnAction(e -> _newFileCommand.action(e));

		Button ofb = new Button();
		ofb.setMinWidth(80);
		ofb.setText("Open File");
		ofb.setOnAction(e -> _openFileCommand.action(e));
		
		Button sfb = new Button();
		sfb.setMinWidth(80);
		sfb.setText("Save File");
		sfb.setOnAction(e -> _SaveFileCommand.action(e));
		
		Button safb = new Button();
		safb.setMinWidth(80);
		safb.setText("Save As File");
		safb.setOnAction(e -> _SaveAsFileCommand.action(e));
		
		nodes.add(nfb);
		nodes.add(ofb);
		nodes.add(sfb);
		nodes.add(safb);

	}
}
