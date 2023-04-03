package classes;

import java.awt.*;
import java.awt.geom.Rectangle2D;

/**
 * constructor voor de blocks in de simulatie
 */
public class Block extends Rectangle2D.Double {
    private double width;
    private double height;
    private double x;
    private double y;
    private Color color;

    public Block(double width, double height, double x, double y, Color color) {
        this.width = width;
        this.height = height;
        this.x = x;
        this.y = y;
        this.color = color;
        setRect(x, y, width, height);
    }

//    public void setPos(double x, double y) {
//        this.x = x;
//        this.y = y;
//        setRect(x, y, width, height);
//    }

//    public void changeSizeOfBlock(double width, double height) {
//        this.width = width;
//        this.height = height;
//        setRect(x, y, width, height);
//    }

//    public Color getColor() {
//        return color;
//    }

//    public void setColor(Color color) {
//        this.color = color;
//    }

    @Override
    public String toString() {
        return "Block{" +
                "width=" + width +
                ", height=" + height +
                ", x=" + x +
                ", y=" + y +
                ", color=" + color +
                '}';
    }
}
