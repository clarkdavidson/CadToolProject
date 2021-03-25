package csci240.prinCad.model;

import csci240.prinCad.fx3d.Map3d;
import csci240.prinCad.fx3d.Xform;
import javafx.scene.Node;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.CullFace;
import javafx.scene.shape.DrawMode;
import javafx.scene.shape.MeshView;
import javafx.scene.shape.TriangleMesh;

public class ExMarkerItem extends CadItem {
	CadPoint point;

	public ExMarkerItem(CadPoint point) {
		this.point = point;

	}

	@Override
	public void draw(GraphicsContext gc) {
		double x = point.x;
		double y = point.y;
		gc.strokeLine(x - MarkerSize, y - MarkerSize, x + MarkerSize, y + MarkerSize);
		gc.strokeLine(x + MarkerSize, y - MarkerSize, x - MarkerSize, y + MarkerSize);
	}

	// load criss cross marker item from string data
	public static ExMarkerItem load(String data) {

		CadPoint point = CadItem.toPoint(data);
		if (point == null)
			return null;

		return new ExMarkerItem(point);
	}

	// Copy cad item
	@Override
	public CadItem Copy() {
		return new ExMarkerItem(point);
	}

	// Get 3d object
	@Override
	public Node get3dObject(Map3d map) {

		final PhongMaterial material = new PhongMaterial();
		material.setDiffuseColor(Color.GOLDENROD);
		material.setSpecularColor(Color.YELLOW);

		double s = map.Scale(MarkerSize) * 2.0;
		float b = (float) (s * 0.18);

		// right
		TriangleMesh pyramidMesh1 = new TriangleMesh();
		pyramidMesh1.getTexCoords().addAll(0, 0);
		pyramidMesh1.getPoints().addAll((float) s, 0.0f, 0.0f, // point 0 - top
				b, b, b, // point 1 - front
				b, b, -b, // point 2 - left
				b, -b, -b, // point 3 - back
				b, -b, b // point 4 - right
		);
		pyramidMesh1.getFaces().addAll(0, 0, 2, 0, 1, 0, // Front left face
				0, 0, 3, 0, 2, 0, // Front right face
				0, 0, 4, 0, 3, 0, // Back right face
				0, 0, 1, 0, 4, 0 // Back left face
		);

		MeshView pyramid1 = new MeshView(pyramidMesh1);
		pyramid1.setDrawMode(DrawMode.FILL);
		pyramid1.setMaterial(material);
		pyramid1.setCullFace(CullFace.NONE);

		// left
		TriangleMesh pyramidMesh2 = new TriangleMesh();
		pyramidMesh2.getTexCoords().addAll(0, 0);
		pyramidMesh2.getPoints().addAll(-(float) s, 0.0f, 0.0f, // point 0 - top
				-b, b, b, // point 1 - front
				-b, b, -b, // point 2 - left
				-b, -b, -b, // point 3 - back
				-b, -b, b // point 4 - right
		);
		pyramidMesh2.getFaces().addAll(0, 0, 1, 0, 2, 0, // Front left face
				0, 0, 2, 0, 3, 0, // Front right face
				0, 0, 3, 0, 4, 0, // Back right face
				0, 0, 4, 0, 1, 0 // Back left face
		);

		MeshView pyramid2 = new MeshView(pyramidMesh2);
		pyramid2.setDrawMode(DrawMode.FILL);
		pyramid2.setMaterial(material);
		pyramid2.setCullFace(CullFace.NONE);

		// top
		TriangleMesh pyramidMesh3 = new TriangleMesh();
		pyramidMesh3.getTexCoords().addAll(0, 0);
		pyramidMesh3.getPoints().addAll(0.0f, (float) s, 0.0f, // point 0 - top
				b, b, b, // point 1 - front
				b, b, -b, // point 2 - left
				-b, b, -b, // point 3 - back
				-b, b, b // point 4 - right
		);
		pyramidMesh3.getFaces().addAll(0, 0, 1, 0, 2, 0, // Front left face
				0, 0, 2, 0, 3, 0, // Front right face
				0, 0, 3, 0, 4, 0, // Back right face
				0, 0, 4, 0, 1, 0 // Back left face
		);

		MeshView pyramid3 = new MeshView(pyramidMesh3);
		pyramid3.setDrawMode(DrawMode.FILL);
		pyramid3.setMaterial(material);
		pyramid3.setCullFace(CullFace.NONE);

		// bottom
		TriangleMesh pyramidMesh4 = new TriangleMesh();
		pyramidMesh4.getTexCoords().addAll(0, 0);
		pyramidMesh4.getPoints().addAll(0.0f, -(float) s, 0.0f, // point 0 - top
				b, -b, b, // point 1 - front
				b, -b, -b, // point 2 - left
				-b, -b, -b, // point 3 - back
				-b, -b, b // point 4 - right
		);
		pyramidMesh4.getFaces().addAll(0, 0, 2, 0, 1, 0, // Front left face
				0, 0, 3, 0, 2, 0, // Front right face
				0, 0, 4, 0, 3, 0, // Back right face
				0, 0, 1, 0, 4, 0 // Back left face
		);

		MeshView pyramid4 = new MeshView(pyramidMesh4);
		pyramid4.setDrawMode(DrawMode.FILL);
		pyramid4.setMaterial(material);
		pyramid4.setCullFace(CullFace.NONE);

		// front
		TriangleMesh pyramidMesh5 = new TriangleMesh();
		pyramidMesh5.getTexCoords().addAll(0, 0);
		pyramidMesh5.getPoints().addAll(0.0f, 0.0f, (float) s, // point 0 - top
				b, b, b, // point 1 - front
				-b, b, b, // point 2 - left
				-b, -b, b, // point 3 - back
				b, -b, b // point 4 - right
		);
		pyramidMesh5.getFaces().addAll(0, 0, 1, 0, 2, 0, // Front left face
				0, 0, 2, 0, 3, 0, // Front right face
				0, 0, 3, 0, 4, 0, // Back right face
				0, 0, 4, 0, 1, 0 // Back left face
		);

		MeshView pyramid5 = new MeshView(pyramidMesh5);
		pyramid5.setDrawMode(DrawMode.FILL);
		pyramid5.setMaterial(material);
		pyramid5.setCullFace(CullFace.NONE);

		// front
		TriangleMesh pyramidMesh6 = new TriangleMesh();
		pyramidMesh6.getTexCoords().addAll(0, 0);
		pyramidMesh6.getPoints().addAll(0.0f, 0.0f, -(float) s, // point 0 - top
				b, b, -b, // point 1 - front
				-b, b, -b, // point 2 - left
				-b, -b, -b, // point 3 - back
				b, -b, -b // point 4 - right
		);
		pyramidMesh6.getFaces().addAll(0, 0, 2, 0, 1, 0, // Front left face
				0, 0, 3, 0, 2, 0, // Front right face
				0, 0, 4, 0, 3, 0, // Back right face
				0, 0, 1, 0, 4, 0 // Back left face
		);

		MeshView pyramid6 = new MeshView(pyramidMesh6);
		pyramid6.setDrawMode(DrawMode.FILL);
		pyramid6.setMaterial(material);
		pyramid6.setCullFace(CullFace.NONE);

		Xform xform = new Xform();
		xform.getChildren().add(pyramid1);
		xform.getChildren().add(pyramid2);
		xform.getChildren().add(pyramid3);
		xform.getChildren().add(pyramid4);
		xform.getChildren().add(pyramid5);
		xform.getChildren().add(pyramid6);
		xform.setTx(map.ToWorldX(point.x));
		xform.setTy(map.ToWorldY(point.y));
		xform.setTz(map.ToWorldZ(getzValue()));

		return xform;
	}

	@Override
	public String save() {
		return String.format("%1$f %2$f", point.x, point.y);
	}

	@Override
	public CadBox getRect() {
		return new CadBox(point.x - MarkerSize, point.y - MarkerSize, 2 * MarkerSize, 2 * MarkerSize);
	}

	@Override
	public boolean intersects(CadLine line) {
		return line.intersects(getRect());
	}

}
