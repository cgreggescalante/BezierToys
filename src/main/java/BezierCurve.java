import processing.core.PApplet;
import processing.core.PVector;

import java.util.ArrayList;
import java.util.List;

public class BezierCurve implements Curve {
    List<PVector> points;

    int NODE_DISPLAY_SIZE = 15;

    public BezierCurve() {
        points = new ArrayList<>();
    }

    @Override
    public PVector pointFromTValue(float t) {
        PVector[] layer = new PVector[points.size()];

        for (int i = 0; i < points.size(); i++) {
            layer[i] = points.get(i);
        }

        while (layer.length > 1) {
            PVector[] newLayer = new PVector[layer.length - 1];
            for (int i = 0; i < layer.length - 1; i++) {
                newLayer[i] = linear_interpolation(layer[i], layer[i + 1], t);
            }
            layer = newLayer;
        }

        return layer[0];
    }

    @Override
    public PVector pointFromDistance(float distance) {
        return null;
    }

    @Override
    public void drawBounds(PApplet applet) {
        applet.pushStyle();

        applet.stroke(50);
        applet.strokeWeight(1);

        for (int i = 0; i < points.size() - 1; i++) {
            applet.line(points.get(i).x, points.get(i).y, points.get(i + 1).x, points.get(i + 1).y);
        }

        for (PVector point : points) {
            applet.ellipse(point.x, point.y, NODE_DISPLAY_SIZE, NODE_DISPLAY_SIZE);
        }

        applet.popStyle();
    }

    private static PVector linear_interpolation(PVector a, PVector b, float t) {
        return PVector.add(a, PVector.mult(PVector.sub(b, a), t));
    }

    public int selectedNode(int x, int y) {
        double min_distance = Math.pow(NODE_DISPLAY_SIZE, 2);

        for (int i = 0; i < points.size(); i++) {
            if (Math.pow(points.get(i).x - x, 2) + Math.pow(points.get(i).y - y, 2) < min_distance) {
                return i;
            }
        }
        return -1;
    }

    public boolean hasPoints() {
        return !points.isEmpty();
    }

    public void setPoint(PVector point, int index) {
        points.set(index, point);
    }
}
