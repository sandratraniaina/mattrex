package mg.sandratra.mattrex.model;

import java.sql.Date;

public class Foam extends Dimension {
    private int id;
    private Foam parent;
    private double costPrice;
    private double residue;
    private Date creationDate;
    private boolean isLast;

    // Constructors
    public Foam() {
    }

    public Foam(int id) {
        this();
        this.setId(id);
    }

    public Foam(Foam parent, double costPrice, double residue, Date creationDate, boolean isLast) {
        this();
        this.setParent(parent);
        this.setCostPrice(costPrice);
        this.setResidue(residue);
        this.setCreationDate(creationDate);
        this.setLast(isLast);
    }

    public Foam(int id, Foam parent, double costPrice, double residue, Date creationDate, boolean isLast) {
        this(id);
        this.setParent(parent);
        this.setCostPrice(costPrice);
        this.setResidue(residue);
        this.setCreationDate(creationDate);
        this.setLast(isLast);
    }

    // Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Foam getParent() {
        return parent;
    }

    public void setParent(Foam parent) {
        this.parent = parent;
    }

    public double getCostPrice() {
        return costPrice;
    }

    public void setCostPrice(double costPrice) {
        this.costPrice = costPrice;
    }

    public double getResidue() {
        return residue;
    }

    public void setResidue(double residue) {
        this.residue = residue;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date createDate) {
        this.creationDate = createDate;
    }

    public boolean isLast() {
        return isLast;
    }

    public void setLast(boolean isLast) {
        this.isLast = isLast;
    }

}
