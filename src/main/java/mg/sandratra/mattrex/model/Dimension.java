package mg.sandratra.mattrex.model;

public class Dimension {
    private double width;
    private double height;
    private double depth;

    // Methods
    public double calculateVolume() {
        return width * height * depth;
    }

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
}
