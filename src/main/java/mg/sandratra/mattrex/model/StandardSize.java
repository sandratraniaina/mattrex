package mg.sandratra.mattrex.model;

public class StandardSize extends Dimension {
    private int id;
    private String name;
    private double sellingPrice;

    // Constructors
    public StandardSize() {
    }

    public StandardSize(int id) {
        this();
        this.setId(id);
    }

    public StandardSize(String name, Dimension dimension, double sellingPrice) {
        this();
        this.setName(name);
        this.setWidth(dimension.getWidth());
        this.setHeight(dimension.getHeight());
        this.setDepth(dimension.getDepth());
        this.setSellingPrice(sellingPrice);
    }

    public StandardSize(int id, String name, Dimension dimension, double sellingPrice) {
        this(name, dimension, sellingPrice);
        this.setId(id);

    }

    // Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(double sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

}
