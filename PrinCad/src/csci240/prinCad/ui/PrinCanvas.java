package csci240.prinCad.ui;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import csci240.prinCad.control.CanvasToolInterface;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import csci240.prinCad.command.CanvasCommandInterface;
import csci240.prinCad.control.BoxSelectionTool;
import csci240.prinCad.control.CadTool;
import csci240.prinCad.control.LineSelectionTool;
import csci240.prinCad.model.CadItem;
import csci240.prinCad.model.ModelManager;

// Drawing canvas for the Prin CAD tools application
public class PrinCanvas extends Canvas implements CanvasToolInterface, CanvasCommandInterface {
	// Path name string
	String pathname;
	// Variable to manage the model
	public ModelManager _model;

	// Reference to graphics context
	public GraphicsContext _gc;

	// Can change between line and square selection
	CadTool _selectionTool;

	// Active tool
	private CadTool _activeTool;

	public void setActiveTool(CadTool activeTool) {
		_activeTool = activeTool;
	}
	//retrieve undosize.
	private int undoSize = MainForm.appSettings.getUndoSize();

	// Data constructor
	public PrinCanvas(double width, double height) {
		// invoke (call) parent class constructor
		super(width, height);

		MainForm.appSettings.getCanvasHeight();
		MainForm.appSettings.getCanvasWidth();

		// Get graphics context and fill with background color
		_gc = getGraphicsContext2D();

		_gc.setFill(MainForm.appSettings.getCanvasColor());
		_gc.fillRect(0, 0, MainForm.appSettings.getCanvasWidth(), MainForm.appSettings.getCanvasHeight());

		// Subscribe to mouse event
		setOnMousePressed(e -> onMousePressed(e));
		setOnMouseDragged(e -> onMouseDrag(e));
		setOnMouseReleased(e -> onMouseRelease(e));
		setOnMouseMoved(e -> onMouseDrag(e));

		// Subscript to Widnow Event
		// stage.setOnWindowClose(e -> onCloseRequest(e));

		_selectionTool = new BoxSelectionTool();
		_activeTool = _selectionTool;
		_model = new ModelManager(undoSize);
	}

	// Toggle Selection Type
	public void toggleSelectionType() {
		if (_selectionTool instanceof BoxSelectionTool) {
			_selectionTool = new LineSelectionTool();
		} else {
			_selectionTool = new BoxSelectionTool();
		}
		_activeTool = _selectionTool;
	}

	// Set back to selection mode
	public void reset() {
		_activeTool = _selectionTool;
	}

	public void reset(CadItem cadItem) {
		_model.add(cadItem);
		_activeTool = _selectionTool;
	}

	public void draw() {
		_gc.fillRect(0, 0, getWidth(), getHeight());
		_gc.setStroke(Color.ORANGERED);
		_gc.setLineWidth(0);
		_model.draw(_gc);
	}

	public void saveToFile(PrintWriter out) {
		_model.save(out);
	}

	public void loadFile(BufferedReader in) throws IOException {
		_model.load(in);
		draw();
	}

	public void newFile() {
		_model.clear();
		draw();
	}

	public String getFile() {
		return pathname;
	}

	public void setFile(String path) {
		pathname = path;
	}
	// Handle close events
	// stage.setOnCloseRequest((final WindowEvent event))

	public ModelManager getModel() {
		return _model;
	}

	// Handle mouse pressed (button down)
	private void onMousePressed(MouseEvent e) {
		_activeTool.onMousePressed(this, e);
	}

	// Handle mouse drag (only called when mouse button IS depressed)
	private void onMouseDrag(MouseEvent e) {
		_activeTool.onMouseDrag(this, e);
	}

	// Handle mouse release (button up)
	private void onMouseRelease(MouseEvent e) {
		_activeTool.onMouseRelease(this, e);
	}
	
	//Ask for elevation and then set elecation of selected items
	public void handlePropertiesCommand() {
		//Ask for input for elevation
		double z = ElevationPropForm.Show(0.0);
		//Take returned value and set the elevation
		_model.setElevation(z);
		
	}

}
