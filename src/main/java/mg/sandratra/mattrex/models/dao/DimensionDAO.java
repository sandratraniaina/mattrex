package mg.sandratra.mattrex.models.dao;

import mg.sandratra.mattrex.models.entities.Dimension;
import mg.sandratra.mattrex.utils.db.PgConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DimensionDAO {

    // Method to create a new dimension
    public int create(Dimension dimension, Connection conn) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO dimension (width, height, depth) VALUES (?, ?, ?)";
        boolean isConnectionLocal = (conn == null);

        if (isConnectionLocal) {
            conn = PgConnection.getConnection();
        }

        try (PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setDouble(1, dimension.getWidth());
            pstmt.setDouble(2, dimension.getHeight());
            pstmt.setDouble(3, dimension.getDepth());
            int affectedRows = pstmt.executeUpdate();

            if (affectedRows > 0) {
                try (ResultSet rs = pstmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        return rs.getInt(1); // Return the generated ID
                    }
                }
            }
        } finally {
            if (isConnectionLocal) {
                conn.close();
            }
        }
        return -1; // Return -1 if insertion failed
    }

    // Method to read all dimensions
    public List<Dimension> readAll(Connection conn) throws SQLException, ClassNotFoundException {
        List<Dimension> dimensions = new ArrayList<>();
        String sql = "SELECT * FROM dimension";
        boolean isConnectionLocal = (conn == null);

        if (isConnectionLocal) {
            conn = PgConnection.getConnection();
        }

        try (Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Dimension dimension = new Dimension(
                        rs.getInt("id"),
                        rs.getDouble("width"),
                        rs.getDouble("height"),
                        rs.getDouble("depth"));
                dimensions.add(dimension);
            }
        } finally {
            if (isConnectionLocal) {
                conn.close();
            }
        }
        return dimensions;
    }

    // Method to read a dimension by ID
    public Dimension readById(int id, Connection conn) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM dimension WHERE id = ?";
        boolean isConnectionLocal = (conn == null);

        if (isConnectionLocal) {
            conn = PgConnection.getConnection();
        }

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new Dimension(
                            rs.getInt("id"),
                            rs.getDouble("width"),
                            rs.getDouble("height"),
                            rs.getDouble("depth"));
                }
            }
        } finally {
            if (isConnectionLocal) {
                conn.close();
            }
        }
        return null; // Return null if no dimension found with the given ID
    }

    // Method to delete a dimension by ID
    public int delete(int id, Connection conn) throws SQLException, ClassNotFoundException {
        String sql = "DELETE FROM dimension WHERE id = ?";
        boolean isConnectionLocal = (conn == null);

        if (isConnectionLocal) {
            conn = PgConnection.getConnection();
        }

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            return pstmt.executeUpdate(); // Returns the number of affected rows
        } finally {
            if (isConnectionLocal) {
                conn.close();
            }
        }
    }

    // Method to update a dimension by ID
    public int update(Dimension dimension, Connection conn) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE dimension SET width = ?, height = ?, depth = ? WHERE id = ?";
        boolean isConnectionLocal = (conn == null);

        if (isConnectionLocal) {
            conn = PgConnection.getConnection();
        }

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setDouble(1, dimension.getWidth());
            pstmt.setDouble(2, dimension.getHeight());
            pstmt.setDouble(3, dimension.getDepth());
            pstmt.setInt(4, dimension.getId());
            return pstmt.executeUpdate(); // Returns the number of affected rows
        } finally {
            if (isConnectionLocal) {
                conn.close();
            }
        }
    }
}
