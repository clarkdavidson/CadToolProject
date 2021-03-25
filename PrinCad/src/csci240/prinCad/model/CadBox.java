package csci240.prinCad.model;

public class CadBox {
	public final double xMin, yMin, xMax, yMax;

	public CadBox(double xMin, double yMin, double xMax, double yMax) {
		this.xMin = xMin;
		this.xMax = xMax;
		this.yMin = yMin;
		this.yMax = yMax;
	}

	public boolean inside(CadBox SelectionBox) {
		return (SelectionBox.xMin >= this.xMin && SelectionBox.xMax <= this.xMax && SelectionBox.yMin >= this.yMin
				&& SelectionBox.yMax <= this.yMax);
	}
}
