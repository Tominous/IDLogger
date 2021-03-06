package main.java.huskehhh.bukkitSQL.sqlite;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;

import org.bukkit.plugin.Plugin;

import main.java.huskehhh.bukkitSQL.Database;

//SQLite Database; Stored as file locally
public class SQLite extends Database {
	private final String dbLocation;

	public SQLite(Plugin plugin, String dbLocation) {
		super(plugin);
		this.dbLocation = dbLocation;
	}

	@Override
	public Connection openConnection() throws SQLException, ClassNotFoundException {
		if (checkConnection())
			return connection;

		if (!plugin.getDataFolder().exists())
			plugin.getDataFolder().mkdirs();

		File file = new File(plugin.getDataFolder(), dbLocation);

		if (!(file.exists())) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				plugin.getLogger().log(Level.SEVERE, "Unable to create database!");
			}
		}

		Class.forName("org.sqlite.JDBC");
		connection = DriverManager
				.getConnection("jdbc:sqlite:" + plugin.getDataFolder().toPath().toString() + "/" + dbLocation);

		return connection;
	}
}
