package mg.sandratra.mattrex.models.entities;

public class Dimension {
    private int id;
    private double width;
    private double height;
    private double depth;

    // Constructors
    public Dimension() {
    }

    public Dimension(int id, double width, double height, double depth) {
        this.setId(id);
        this.setWidth(width);
        this.setHeight(height);
        this.setDepth(depth);
    }

    // Constructor without ID for new objects
    public Dimension(double width, double height, double depth) {
        this.setWidth(width);
        this.setHeight(height);
        this.setDepth(depth);
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    // toString method for easy display
    @Override
    public String toString() {
        return "Dimension{" +
                "id=" + id +
                ", width=" + width +
                ", height=" + height +
                ", depth=" + depth +
                '}';
    }
}
