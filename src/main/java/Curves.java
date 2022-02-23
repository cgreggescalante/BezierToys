import processing.core.PApplet;
import processing.core.PVector;

public class Curves extends PApplet {
    BezierCurve curve;

    int currentNode = -1;

    public void settings() {
        size(600, 600);
    }

    public void setup() {
        curve = new BezierCurve();
    }

    public void draw() {
        PVector mouse = new PVector(mouseX, mouseY);
        if (currentNode > -1) {
            curve.setPoint(mouse, currentNode);
        }

        background(200);

        stroke(0);
        strokeWeight(3);

        if (curve.hasPoints()) {
            for (float t = 0; t <= 1; t += .001) {
                PVector pos = curve.pointFromTValue(t);
                this.point(pos.x, pos.y);
            }
        }

        curve.drawBounds(this);
    }

    public void mouseClicked() {
        if (mouseButton == RIGHT) {
            curve.points.add(new PVector(mouseX, mouseY));
        }
    }

    public void mousePressed() {
        if (mouseButton == LEFT) {
            currentNode = curve.selectedNode(mouseX, mouseY);
        }
    }

    public void mouseReleased() {
        currentNode = -1;
    }

    public static void main(String[] args) {
        String[] pArgs = new String[]{"Curves"};

        PApplet.runSketch(pArgs, new Curves());
    }
}