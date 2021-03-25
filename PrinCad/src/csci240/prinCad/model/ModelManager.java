package csci240.prinCad.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;

import csci240.prinCad.fx3d.Item3dInterface;
import csci240.prinCad.fx3d.Model3dInterface;
import csci240.prinCad.ui.ElevationPropForm;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class ModelManager implements Model3dInterface {
	public ArrayList<CadItem> _items;

	@Override
	public Iterable<Item3dInterface> get3dItems() {

		ArrayList<Item3dInterface> items = new ArrayList<Item3dInterface>();
		for (CadItem item : _items) {
			items.add(item);
		}
		return items;
	}

	public ArrayList<CadItem> _temp;

	// Size of undo Buffer
	final private int _undoSize;

	private Deque<ModelData> _undoQueue;
	private Deque<ModelData> _redoQueue;

	public ModelManager(int undoSize) {
		_items = new ArrayList<CadItem>();
		_temp = new ArrayList<CadItem>();
		_undoSize = undoSize;
		_undoQueue = new ArrayDeque<ModelData>(_undoSize);
		_redoQueue = new ArrayDeque<ModelData>(_undoSize);

	}

	public void saveState() {
		saveModelData();
		_redoQueue.clear();
	}

	public void saveModelData() {
		ModelData modelData = new ModelData(_items);
		if (_undoQueue.size() == _undoSize) {
			_undoQueue.removeLast();
		}
		_undoQueue.push(modelData);

	}

	public void draw(GraphicsContext gc) {
		for (CadItem item : _items) {
			item.draw(gc, Color.ORANGERED, Color.BLUEVIOLET);
		}
	}

	public void undo() {
		if (_undoQueue.isEmpty()) {
			return;
		}

		ModelData current = new ModelData(_items);
		_redoQueue.push(current);

		ModelData undoData = _undoQueue.pop();
		_items = undoData.getItems();
	}

	public void redo() {
		if (_redoQueue.isEmpty())
			return;

		saveModelData();

		ModelData redoData = _redoQueue.pop();
		_items = redoData.getItems();
	}

	public void clear() {
		_items.clear();
	}

	public void add(CadItem cadItem) {
		saveState();
		_items.add(cadItem);
	}

	public void save(PrintWriter out) {
		for (CadItem item : _items) {
			String name = item.getClass().getName();
			String text = name + " " + item.save();
			out.println(text);
		}
	}

	final String circlename = "csci240.prinCad.model.CircleItem";
	final String squarename = "csci240.prinCad.model.SquareItem";
	final String linename = "csci240.prinCad.model.LineItem";
	final String polyname = "csci240.prinCad.model.PolyItem";

	public void load(BufferedReader in) throws IOException {

		// Fix this for statement
		for (String data = in.readLine(); data != null; data = in.readLine()) {

			if (data.startsWith(circlename)) {
				int begin = circlename.length();
				String variables = data.substring(begin);
				CircleItem circle = CircleItem.load(variables);
				add(circle);
			} else if (data.startsWith(squarename)) {
				int begin = squarename.length();
				String variables = data.substring(begin);
				SquareItem square = new SquareItem(variables);
				add(square);
			} else if (data.startsWith(linename)) {
				int begin = linename.length();
				String variables = data.substring(begin);
				LineItem line = new LineItem(variables);
				add(line);
			} else if (data.startsWith(polyname)) {
				int begin = linename.length();
				String variables = data.substring(begin);
				PolyItem poly = new PolyItem(variables);
				add(poly);
			}
		}
	}

	public void select(CadBox boxselect) {
		for (CadItem item : _items) {
			item.select(boxselect);
		}
	}

	public void select(CadLine line) {
		for (CadItem item : _items) {
			item.select(line);
		}
	}

	public void deleteSelected() {
		saveState();
		// Pull the arraylist of items from modelmanager
		// loop through the items in the array
		for (int i = 0; i < _items.size(); i++) {
			// if an array item is selected
			if (!_items.get(i)._isSelected) {
				_temp.add(_items.get(i));
			}
		}
		_items.clear();
		for (int i = 0; i < _temp.size(); i++) {
			_items.add(_temp.get(i));
		}
	}

	public void setElevation(double z) {
		for (int i = 0; i < _items.size(); i++) {
			if (_items.get(i).IsSelected()) {
				_items.get(i).setzValue(z);
			}
		}

	}

}
