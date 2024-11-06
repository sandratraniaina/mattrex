package mg.sandratra.mattrex.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Foam extends Dimension {
    private int id;
    private Foam parent;
    private double costPrice;
    private double residue;
    private Date creationDate;
    private boolean isLast;

    // Methods
    public int create(Connection connection) {
        int dimensionId = super.create(connection);
        String sql = "INSERT INTO foam (dimension_id, parent_id, cost_price, residue, creation_date) VALUES (?, ?, ?, ?, ?)";
        try(PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, dimensionId);
            stmt.setInt(2, parent.getId());
            stmt.setDouble(3, costPrice);
            stmt.setDouble(4, residue);
            stmt.setDate(5, creationDate);
            stmt.executeUpdate();

            if (stmt.getGeneratedKeys().next()) {
                return stmt.getGeneratedKeys().getInt(1);
            }
            return -1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    // Constructors
    public Foam() {
    }

    public Foam(int id) {
        this();
        this.setId(id);
    }

    public Foam(Foam parent, Dimension dimension, double costPrice, double residue, Date creationDate, boolean isLast) {
        this();
        this.setParent(parent);
        this.setWidth(dimension.getWidth());
        this.setHeight(dimension.getHeight());
        this.setDepth(dimension.getDepth());
        this.setCostPrice(costPrice);
        this.setResidue(residue);
        this.setCreationDate(creationDate);
        this.setLast(isLast);
    }

    public Foam(int id, Foam parent, Dimension dimension, double costPrice, double residue, Date creationDate,
            boolean isLast) {
        this(parent, dimension, costPrice, residue, creationDate, isLast);
        this.setId(id);
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
