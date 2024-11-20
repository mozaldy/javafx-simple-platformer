package application;

public class Decoration extends GameObject2D {
    protected double opacity;
    protected int layer;

    public Decoration(String imageName, double x, double y, double width, double height) {
        super(imageName, x, y, width, height);
        this.opacity = 1.0;
        this.layer = 0;
        initializeDecoration();
    }

    protected void initializeDecoration() {
        setPickOnBounds(false);
        setMouseTransparent(true);
    }

    public void setLayer(int layer) {
        this.layer = layer;
        setViewOrder(-layer);
    }

    public int getLayer() {
        return layer;
    }
}