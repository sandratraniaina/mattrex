package mg.sandratra.mattrex.models.entities;

import java.sql.Timestamp;

public class Foam {
    private int id;
    private Dimension dimension; // Foreign key relationship with Dimension
    private Foam parentFoam; // Self-referencing foreign key for parent_id
    private double costPrice;
    private double residue;
    private Timestamp creationDate;
    private boolean isLast;

    // Method
    public double getVolume() {
        return  getDimension().calculateVolume();
    }

    // Default constructor
    public Foam() {
    }

    // Constructor with all fields, including ID
    public Foam(int id, Dimension dimension, Foam parentFoam, double costPrice, double residue, Timestamp creationDate,
            boolean isLast) {
        this.setId(id);
        this.setDimension(dimension);
        this.setParentFoam(parentFoam);
        this.setCostPrice(costPrice);
        this.setResidue(residue);
        this.setCreationDate(creationDate);
        this.setLast(isLast);
    }

    // Constructor without ID for new objects
    public Foam(Dimension dimension, Foam parentFoam, double costPrice, double residue, Timestamp creationDate,
            boolean isLast) {
        this.setDimension(dimension);
        this.setParentFoam(parentFoam);
        this.setCostPrice(costPrice);
        this.setResidue(residue);
        this.setCreationDate(creationDate);
        this.setLast(isLast);
    }

    // Getters and Setters
    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Dimension getDimension() {
        return this.dimension;
    }

    public void setDimension(Dimension dimension) {
        this.dimension = dimension;
    }

    public Foam getParentFoam() {
        return this.parentFoam;
    }

    public void setParentFoam(Foam parentFoam) {
        this.parentFoam = parentFoam;
    }

    public double getCostPrice() {
        return this.costPrice;
    }

    public void setCostPrice(double costPrice) {
        this.costPrice = costPrice;
    }

    public double getResidue() {
        return this.residue;
    }

    public void setResidue(double residue) {
        this.residue = residue;
    }

    public Timestamp getCreationDate() {
        return this.creationDate;
    }

    public void setCreationDate(Timestamp creationDate) {
        this.creationDate = creationDate;
    }

    public boolean isLast() {
        return this.isLast;
    }

    public void setLast(boolean last) {
        this.isLast = last;
    }

    // toString method for easy display
    @Override
    public String toString() {
        return "Foam{" +
                "id=" + this.getId() +
                ", dimension=" + this.getDimension() +
                ", parentFoam=" + (this.getParentFoam() != null ? this.getParentFoam().getId() : "null") +
                ", costPrice=" + this.getCostPrice() +
                ", residue=" + this.getResidue() +
                ", creationDate=" + this.getCreationDate() +
                ", isLast=" + this.isLast() +
                '}';
    }
}
