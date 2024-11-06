package mg.sandratra.mattrex.model;

public class Dimension {
    private int id;
    private double width;
    private double height;
    private double depth;

    // Constructors
    public Dimension() {
    }

    public Dimension(double width, double height, double depth) {
        setWidth(width);
        setHeight(height);
        setDepth(depth);
    }

    // Getters and setters
    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getDepth() {
        return depth;
    }

    public void setDepth(double depth) {
        this.depth = depth;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
