package mg.sandratra.mattrex.model;

public class Block {
    private Dimension dimension;

    // Constructors
    public Block() {
    }

    public Block(Dimension dimension) {
        this.setDimension(dimension);
    }

    // Getters and setters
    public Dimension getDimension() {
        return dimension;
    }

    public void setDimension(Dimension dimension) {
        this.dimension = dimension;
    }

    public void setDimension(double width, double height, double depth) {
        this.setDimension(new Dimension(width, height, depth));
    }
}
