package csci240.prinCad.ui;

import csci240.prinCad.command.CanvasCommandInterface;
import csci240.prinCad.command.DeleteCommand;
import csci240.prinCad.command.PropertiesCommand;
import csci240.prinCad.command.RedoCommand;
import csci240.prinCad.command.ToggleCommand;
import csci240.prinCad.command.UndoCommand;
import csci240.prinCad.command.View3dCommand;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;

public class EditManager {

	// File commands
	private UndoCommand _undoCommand;
	private RedoCommand _redoCommand;
	private DeleteCommand _deleteCommand;
	private ToggleCommand _toggleCommand;
	private PropertiesCommand _propertiesCommand;
	private View3dCommand _3dCommand;

	// Constructor
	public EditManager(CanvasCommandInterface canvas) {
		_undoCommand = new UndoCommand(canvas);
		_redoCommand = new RedoCommand(canvas);
		_deleteCommand = new DeleteCommand(canvas);
		_toggleCommand = new ToggleCommand(canvas);
		_propertiesCommand = new PropertiesCommand(canvas);
		_3dCommand = new View3dCommand(canvas);
	}

	// Build menu
	public Menu buildMenu() {

		MenuItem edUndo = new MenuItem("Undo");
		edUndo.setOnAction(e -> _undoCommand.action(e));
		MenuItem edRedo = new MenuItem("Redo");
		edRedo.setOnAction(e -> _redoCommand.action(e));
		MenuItem edDelete = new MenuItem("Delete");
		edDelete.setOnAction(e -> _deleteCommand.action(e));
		MenuItem edToggle = new MenuItem("Toggle Selection");
		edToggle.setOnAction(e -> _toggleCommand.action(e));
		MenuItem edProp = new MenuItem("Properties");
		edProp.setOnAction(e -> _toggleCommand.action(e));
		MenuItem ed3d = new MenuItem("3d View");
		ed3d.setOnAction(e -> _3dCommand.action(e));

		// Create a menu
		Menu editMenu = new Menu("Edit");
		ObservableList<MenuItem> editMenuItems = editMenu.getItems();

		// Add Menu items to menu
		editMenuItems.add(edUndo);
		editMenuItems.add(edRedo);
		editMenuItems.add(edDelete);
		editMenuItems.add(edToggle);
		editMenuItems.add(edProp);
		editMenuItems.add(ed3d);

		return editMenu;
	}

	// Add buttons to bar
	public void addButtonsToBar(ObservableList<Node> nodes) {

		// Create buttons
		Button ueb = new Button();
		ueb.setMinWidth(80);
		ueb.setText("Undo");
		ueb.setOnAction(e -> _undoCommand.action(e));

		Button reb = new Button();
		reb.setMinWidth(80);
		reb.setText("Redo");
		reb.setOnAction(e -> _redoCommand.action(e));

		Button deb = new Button();
		deb.setMinWidth(80);
		deb.setText("Delete");
		deb.setOnAction(e -> _deleteCommand.action(e));

		Button seb = new Button();
		seb.setMinWidth(80);
		seb.setText("^Selection");
		seb.setOnAction(e -> _toggleCommand.action(e));

		Button peb = new Button();
		peb.setMinWidth(80);
		peb.setText("Properties");
		peb.setOnAction(e -> _propertiesCommand.action(e));
		
		Button ed3d = new Button();
		ed3d.setMinWidth(80);
		ed3d.setText("3d View");
		ed3d.setOnAction(e->_3dCommand.action(e));
		

		nodes.add(ueb);
		nodes.add(reb);
		nodes.add(deb);
		nodes.add(seb);
		nodes.add(peb);
		nodes.add(ed3d);

	}

}
