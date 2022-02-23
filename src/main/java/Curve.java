import processing.core.PApplet;
import processing.core.PVector;

public interface Curve {
    PVector pointFromTValue(float t);

    PVector pointFromDistance(float distance);

    void drawBounds(PApplet applet);
}
