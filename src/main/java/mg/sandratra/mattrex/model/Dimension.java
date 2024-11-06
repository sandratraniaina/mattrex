package mg.sandratra.mattrex.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class Dimension {
    private double width;
    private double height;
    private double depth;

    // Methods
    public double calculateVolume() {
        return width * height * depth;
    }

    // Methods
    public int create(Connection connection) {
        String sql = "INSERT INTO dimension (width, height, depth) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setDouble(1, width);
            stmt.setDouble(2, height);
            stmt.setDouble(3, depth);
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
