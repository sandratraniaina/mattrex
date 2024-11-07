package mg.sandratra.mattrex.models.service;

import mg.sandratra.mattrex.models.dao.FoamDAO;
import mg.sandratra.mattrex.models.entities.Foam;
import mg.sandratra.mattrex.utils.db.PgConnection;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class FoamService {

    private FoamDAO foamDAO;

    public FoamService() {
        this.foamDAO = new FoamDAO();
    }

    // Create a new Foam record
    public int createFoam(Foam foam) throws SQLException, ClassNotFoundException {
        try (Connection connection = PgConnection.getConnection()) {
            return foamDAO.create(foam, connection);
        }
    }

    // Retrieve all Foam records
    public List<Foam> getAllFoams() throws SQLException, ClassNotFoundException {
        try (Connection connection = PgConnection.getConnection()) {
            return foamDAO.readAll(connection);
        }
    }

    // Retrieve a Foam record by ID
    public Foam getFoamById(int id) throws SQLException, ClassNotFoundException {
        try (Connection connection = PgConnection.getConnection()) {
            return foamDAO.readById(id, connection);
        }
    }

    // Update an existing Foam record
    public int updateFoam(Foam foam) throws SQLException, ClassNotFoundException {
        try (Connection connection = PgConnection.getConnection()) {
            return foamDAO.update(foam, connection);
        }
    }

    // Delete a Foam record by ID
    public int deleteFoam(int id) throws SQLException, ClassNotFoundException {
        try (Connection connection = PgConnection.getConnection()) {
            return foamDAO.delete(id, connection);
        }
    }
}
