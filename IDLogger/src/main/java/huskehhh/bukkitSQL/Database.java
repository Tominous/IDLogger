package main.java.huskehhh.bukkitSQL;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.bukkit.plugin.Plugin;

// Parent class for all database types
public abstract class Database {

	// Spigot plugin instance
	protected Plugin plugin;
	protected Connection connection;

	protected Database(Plugin plugin) {
		this.plugin = plugin;
	}

	// Start the connection with the DB
	public abstract Connection openConnection() throws SQLException, ClassNotFoundException;

	// Close the connection
	public boolean closeConnection() throws SQLException {
		if (connection == null)
			return false;

		connection.close();
		return true;
	}

	// Check if the connection is open
	public boolean checkConnection() throws SQLException {
		return connection != null && !connection.isClosed();
	}

	// Return the connection
	public Connection getConnection() {
		return connection;
	}

	// Execute a query and return a ResultSet
	public ResultSet querySQL(String query) throws SQLException, ClassNotFoundException {
		if (!checkConnection())
			openConnection();

		Statement statement = connection.createStatement();

		ResultSet result = statement.executeQuery(query);

		return result;
	}

	// Execute an update, return status
	public int updateSQL(String query) throws SQLException, ClassNotFoundException {
		if (!checkConnection())
			openConnection();

		Statement statement = connection.createStatement();

		int result = statement.executeUpdate(query);

		return result;
	}
}