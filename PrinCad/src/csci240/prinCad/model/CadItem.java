package csci240.prinCad.model;

import csci240.prinCad.fx3d.Item3dInterface;
import csci240.prinCad.util.Log;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public abstract class CadItem implements Item3dInterface {

	protected boolean _isSelected;

	public boolean IsSelected() {
		return _isSelected;
	}

	protected double zValue;

	public abstract void draw(GraphicsContext gc);

	public void draw(GraphicsContext gc, Color normColor, Color selectionColor) {
		if (_isSelected) {
			gc.setStroke(selectionColor);
			gc.setLineWidth(1);

			draw(gc);

			gc.setStroke(normColor);
			gc.setLineWidth(0);
		} else {
			draw(gc);
		}
	}

	public abstract String save();

	public abstract CadBox getRect();

	public void select(CadBox box) {
		if (box.inside(this.getRect())) {
			_isSelected = !_isSelected;
		}
	}

	public abstract boolean intersects(CadLine line);

	public void select(CadLine line) {
		if (intersects(line)) {
			_isSelected = !_isSelected;
		}
	}

	public abstract CadItem Copy();

	public static final double MarkerSize = 5;

	public static CadPoint toPoint(String data) {
		CadPoint point = null;
		try {
			String[] tokens = data.split(" ");
			double x = Double.parseDouble(tokens[0]);
			double y = Double.parseDouble(tokens[1]);
			point = new CadPoint(x, y);
		} catch (Exception ex) {
			Log.error("Invalid CadItem Data String");
		}
		return point;
	}

	public double getzValue() {
		return zValue;

	}

	public void setzValue(double z) {
		zValue = z;
	}

}
