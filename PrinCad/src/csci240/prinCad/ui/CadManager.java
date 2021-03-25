package csci240.prinCad.ui;

import csci240.prinCad.command.BoxMarkerCommand;
import csci240.prinCad.command.CanvasCommandInterface;
import csci240.prinCad.command.CircleCommand;
import csci240.prinCad.command.CrisscrossMarkerCommand;
import csci240.prinCad.command.LineToolCommand;
import csci240.prinCad.command.PlusMarkerCommand;
import csci240.prinCad.command.PolyCommand;
import csci240.prinCad.command.SquareCommand;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;

public class CadManager {

	// CAD Commands
	private PlusMarkerCommand _plusMarker;
	private BoxMarkerCommand _boxMarker;
	private CrisscrossMarkerCommand _crisscrossMarker;
	private LineToolCommand _Line;
	private CircleCommand _Circle;
	private SquareCommand _Square;
	private PolyCommand _Poly;
	

	// Constructor
	public CadManager(CanvasCommandInterface canvas) {
		_plusMarker = new PlusMarkerCommand(canvas);
		_boxMarker = new BoxMarkerCommand(canvas);
		_crisscrossMarker = new CrisscrossMarkerCommand(canvas);
		_Line = new LineToolCommand(canvas);
		_Circle = new CircleCommand(canvas);
		_Square = new SquareCommand(canvas);
		_Poly = new PolyCommand(canvas);
	}

	// Build Menu
	public Menu buildMenu() {

		// Create menu items
		MenuItem miPlus = new MenuItem("Plus");
		miPlus.setOnAction(e -> _plusMarker.action(e));
		
		MenuItem miBox = new MenuItem("Box");
		miBox.setOnAction(e-> _boxMarker.action(e));
		
		MenuItem miCriss = new MenuItem("Crisscross");
		miCriss.setOnAction(e->_crisscrossMarker.action(e));
		
		MenuItem miLine = new MenuItem("Line");
		miLine.setOnAction(e->_Line.action(e));
		
		MenuItem miCircle = new MenuItem("Circle");
		miCircle.setOnAction(e->_Circle.action(e));
		
		MenuItem miSquare = new MenuItem("Square");
		miSquare.setOnAction(e->_Square.action(e));
		
		MenuItem miPoly = new MenuItem("Poly Line");
		miPoly.setOnAction(e->_Poly.action(e));
		

		Menu markerMenu = new Menu("Markers");
		ObservableList<MenuItem> markerMenuItems = markerMenu.getItems();

		// add individual marker menu items to menu
		markerMenuItems.add(miPlus);
		markerMenuItems.add(miBox);
		markerMenuItems.add(miCriss);
		markerMenuItems.add(miLine);
		markerMenuItems.add(miCircle);
		markerMenuItems.add(miSquare);
		markerMenuItems.add(miPoly);

		// create a menu
		Menu cadToolMenu = new Menu("CAD Tools");
		ObservableList<MenuItem> cadToolMenuItems = cadToolMenu.getItems();

		// Add menu items to menu
		cadToolMenuItems.add(markerMenu);

		return cadToolMenu;
	}

	public void addButtonsToBar(ObservableList<Node> nodes) {
		
		// Create Buttons
		Button pb = new Button();
		pb.setMinWidth(80);
		pb.setText("Plus Marker");
		pb.setOnAction(e -> _plusMarker.action(e));
		
		Button bb = new Button();
		bb.setMinWidth(80);
		bb.setText("Box Marker");
		bb.setOnAction(e->_boxMarker.action(e));
		
		Button cc = new Button();
		cc.setMinWidth(80);
		cc.setText("Crisscross Marker");
		cc.setOnAction(e->_crisscrossMarker.action(e));
		
		Button lb = new Button();
		lb.setMinWidth(80);
		lb.setText("Line");
		lb.setOnAction(e->_Line.action(e));
		
		Button cb = new Button();
		cb.setMinWidth(80);
		cb.setText("Circle");
		cb.setOnAction(e->_Circle.action(e));
		
		Button sb = new Button();
		sb.setMinWidth(80);
		sb.setText("Square");
		sb.setOnAction(e->_Square.action(e));
		
		Button plb = new Button();
		plb.setMinWidth(80);
		plb.setText("PolyLine");
		plb.setOnAction(e->_Poly.action(e));
		
		
		
		nodes.add(pb);
		nodes.add(bb);
		nodes.add(cc);
		nodes.add(lb);
		nodes.add(cb);
		nodes.add(sb);
		nodes.add(plb);
	}
}
