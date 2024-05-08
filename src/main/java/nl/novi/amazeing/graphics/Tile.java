package nl.novi.amazeing.graphics;

import nl.novi.amazeing.helpers.DrawHelper;
import nl.novi.amazeing.models.position.GraphicsPoint;

import java.awt.*;

public class Tile implements Drawable {
    private int factorBase = 100;
    private Color borderColor;
    private Color tileColor;
    private int baseLength;
    private float borderWidth = 4;

    public Tile() {
        borderColor = Color.DARK_GRAY;
        tileColor = Color.GRAY;
        baseLength = 100;
    }

    public Tile(Color borderColor, Color tileColor, int baseLength) {
        this.borderColor = borderColor;
        this.tileColor = tileColor;
        this.baseLength = baseLength;
    }

    private static Polygon GetPolygon(int[][] points) {
        Polygon polygon = new Polygon();
        for (int[] point : points) {
            polygon.addPoint(point[0], point[1]);
        }
        return polygon;
    }

    public int getBaseLength() {
        return baseLength;
    }

    private void AddBordertoPolygon(Graphics2D g2d, Polygon polygon) {
        g2d.setColor(borderColor);
        g2d.setStroke(new BasicStroke(borderWidth , BasicStroke.CAP_SQUARE, BasicStroke.JOIN_MITER));
        g2d.draw(polygon);
    }

    private void fillPolygon(Graphics2D g2d, Polygon polygon) {
        g2d.setColor(tileColor);
        g2d.fill(polygon);
    }

    public void draw(Graphics2D g2d, GraphicsPosition position) {
        int[][] points = GetTileLayoutPoints(position.elementFactor());

        CalculatePolygonPointPositions(position, points);
        // Create a polygon from the points
        Polygon polygon = GetPolygon(points);
        fillPolygon(g2d, polygon);
        AddBordertoPolygon(g2d, polygon);
    }

    private int[][] GetTileLayoutPoints(double elementFactor) {

        var baseLength = (int) (this.baseLength * elementFactor);

        return new int[][]{
                {-baseLength / 2, -baseLength / 2}, // Top left point
                {baseLength / 2, -baseLength / 2}, // Top right point
                {baseLength / 2, baseLength / 2}, // bottom right point
                {-baseLength / 2, baseLength / 2}, // Bottom left point
        };
    }

    private void CalculatePolygonPointPositions(GraphicsPosition position, int[][] points) {
        for (int i = 0; i < points.length; i++) {
            GraphicsPoint rotatedPoint = DrawHelper.rotatePointFromOrigin(points[i][0], points[i][1], position.angle());
            points[i][0] = rotatedPoint.getX() + position.x();
            points[i][1] = rotatedPoint.getY() + position.y();
        }
    }

    public int getFactorBase() {
        return factorBase;
    }
}
