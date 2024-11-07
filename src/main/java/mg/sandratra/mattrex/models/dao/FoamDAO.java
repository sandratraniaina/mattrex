package mg.sandratra.mattrex.models.dao;

import mg.sandratra.mattrex.models.entities.*;
import mg.sandratra.mattrex.utils.db.PgConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FoamDAO {

    // Create a new Foam record
    public int create(Foam foam, Connection connection) throws SQLException, ClassNotFoundException {
        if (connection == null) {
            connection = PgConnection.getConnection();
        }

        // Step 1: Use DimensionDAO to insert the new Dimension first
        DimensionDAO dimensionDAO = new DimensionDAO();
        int dimensionId = dimensionDAO.create(foam.getDimension(), connection); // Insert Dimension and get its ID

        if (dimensionId == -1) {
            throw new SQLException("Failed to insert dimension.");
        }

        // Step 2: Insert the new Foam record using the generated dimension_id
        String foamSql = "INSERT INTO foam (dimension_id, parent_id, cost_price, residue, creation_date, is_last) " +
                "VALUES (?, ?, ?, ?, ?, ?) RETURNING id";

        try (PreparedStatement foamStatement = connection.prepareStatement(foamSql)) {
            foamStatement.setInt(1, dimensionId); // Use the generated dimension_id
            foamStatement.setInt(2, foam.getParentFoam() != null ? foam.getParentFoam().getId() : null);
            foamStatement.setDouble(3, foam.getCostPrice());
            foamStatement.setDouble(4, foam.getResidue());
            foamStatement.setTimestamp(5, foam.getCreationDate());
            foamStatement.setBoolean(6, foam.isLast());

            ResultSet foamResultSet = foamStatement.executeQuery();
            if (foamResultSet.next()) {
                return foamResultSet.getInt("id"); // Return the generated foam id
            }
        }

        return -1; // Return -1 if foam insertion failed
    }

    // Read all Foam records
    public List<Foam> readAll(Connection connection) throws SQLException, ClassNotFoundException {
        if (connection == null) {
            connection = PgConnection.getConnection();
        }

        String sql = "SELECT * FROM foam_dimensions"; // View used to access full data

        List<Foam> foams = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(sql);
                ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Dimension dimension = new Dimension(
                        resultSet.getInt("dimension_id"),
                        resultSet.getDouble("width"),
                        resultSet.getDouble("height"),
                        resultSet.getDouble("depth"));

                Foam parentFoam = null;
                if (resultSet.getInt("parent_id") != 0) {
                    // Only populate parent_id, not the full parent foam object
                    parentFoam = new Foam();
                    parentFoam.setId(resultSet.getInt("parent_id"));
                }

                Foam foam = new Foam(
                        resultSet.getInt("id"),
                        dimension,
                        parentFoam,
                        resultSet.getDouble("cost_price"),
                        resultSet.getDouble("residue"),
                        resultSet.getTimestamp("creation_date"),
                        resultSet.getBoolean("is_last"));
                foams.add(foam);
            }
        }
        return foams;
    }

    // Read a Foam record by ID
    public Foam readById(int id, Connection connection) throws SQLException, ClassNotFoundException {
        if (connection == null) {
            connection = PgConnection.getConnection();
        }

        String sql = "SELECT * FROM foam_dimensions WHERE id = ?"; // View used to access full data

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                Dimension dimension = new Dimension(
                        resultSet.getInt("dimension_id"),
                        resultSet.getDouble("width"),
                        resultSet.getDouble("height"),
                        resultSet.getDouble("depth"));

                Foam parentFoam = null;
                if (resultSet.getInt("parent_id") != 0) {
                    // Only populate parent_id, not the full parent foam object
                    parentFoam = new Foam();
                    parentFoam.setId(resultSet.getInt("parent_id"));
                }

                return new Foam(
                        resultSet.getInt("id"),
                        dimension,
                        parentFoam,
                        resultSet.getDouble("cost_price"),
                        resultSet.getDouble("residue"),
                        resultSet.getTimestamp("creation_date"),
                        resultSet.getBoolean("is_last"));
            }
        }
        return null; // No foam found with that ID
    }

    // Delete a Foam record
    public int delete(int id, Connection connection) throws SQLException, ClassNotFoundException {
        if (connection == null) {
            connection = PgConnection.getConnection();
        }

        String sql = "DELETE FROM foam WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            return statement.executeUpdate(); // Returns number of rows affected
        }
    }

    // Update a Foam record
    public int update(Foam foam, Connection connection) throws SQLException, ClassNotFoundException {
        if (connection == null) {
            connection = PgConnection.getConnection();
        }

        String sql = "UPDATE foam SET dimension_id = ?, parent_id = ?, cost_price = ?, residue = ?, creation_date = ?, is_last = ? WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, foam.getDimension().getId());
            statement.setInt(2, foam.getParentFoam() != null ? foam.getParentFoam().getId() : null);
            statement.setDouble(3, foam.getCostPrice());
            statement.setDouble(4, foam.getResidue());
            statement.setTimestamp(5, foam.getCreationDate());
            statement.setBoolean(6, foam.isLast());
            statement.setInt(7, foam.getId());

            return statement.executeUpdate(); // Returns number of rows affected
        }
    }
}
