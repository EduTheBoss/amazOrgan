package amazOrgan.jdbc;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCManager {

	private Connection c = null;

	public JDBCManager() {
		try {
			// Open database connection
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:./db/amazOrgan.db");
			c.createStatement().execute("PRAGMA foreign_keys=ON");
			System.out.println("Database connection opened.");
			// Create tables
			this.createTables();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			System.out.println("Libraries not loaded");
		}
	}

	public void disconnect() {
		try {
			c.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Connection getConnection() {
		return c;
	}

	private void createTables() {
		// Create Tables
		try {
			
			
			Statement stmt = c.createStatement();
			String sql = "CREATE TABLE owners (" 
					+ "	id	    INTEGER PRIMARY KEY AUTOINCREMENT,"
					+ "	name	TEXT NOT NULL," 
			        + "	phone	INTEGER NOT NULL," 
					+ "	email	TEXT NOT NULL,"
					+ "	cardNumber	INTEGER NOT NULL" + ");";
			stmt.executeUpdate(sql);
			
			//antibody
			sql = "CREATE TABLE \"antibody\" ( "
					+ "	\"id\"	INTEGER PRIMARY KEY AUTOINCREMENT, "
					+ "	\"class_I\"	BOOLEAN NOT NULL, "
					+ "	\"class_II\"	BOOLEAN NOT NULL "
					+ ")";
			stmt.executeUpdate(sql);
			
			//antigen
			sql = "CREATE TABLE \"antigen\" ( "
					+ "	\"id\"	INTEGER PRIMARY KEY AUTOINCREMENT, "
					+ "	\"a\"	BOOLEAN NOT NULL, "
					+ "	\"b\"	BOOLEAN NOT NULL, "
					+ "	\"c\"	BOOLEAN NOT NULL, "
					+ "	\"dp\"	BOOLEAN NOT NULL, "
					+ "	\"dq\"	BOOLEAN NOT NULL, "
					+ "	\"dr\"	BOOLEAN NOT NULL "
					+ ")";
			stmt.executeUpdate(sql);
			
			//doctor
			sql = "CREATE TABLE \"doctor\" ("
					+ "	\"medical_id\"	INTEGER,"
					+ "	\"phone_number\"	INTEGER NOT NULL,"
					+ "	\"name\"	TEXT NOT NULL,"
					+ "	PRIMARY KEY(\"medical_id\")"
					+ ")";
			stmt.executeUpdate(sql);
			
			//donor
			sql = "CREATE TABLE donor("
					+ "dni INTEGER PRIMARY KEY,"
					+ "dob date, "
					+ "blood_type TEXT,"
					+ "alive BOOLEAN,"
					+ "id_antigen INTEGER REFERENCES \"antigen\"(id),"
					+ "id_antibody INTEGER REFERENCES \"antibody\"(id),"
					+ "id_location INTEGER REFERENCES location(id),"
					+ "id_doctor_charge INTEGER REFERENCES doctor(medical_id),"
					+ "id_organ INTEGER REFERENCES \"organ\"(id),"
					+ "	CHECK (blood_type IN ('A+', 'B+', 'AB+', 'O+', 'A-', 'B-', 'AB-', 'O-'))"
					+ "	)";
			stmt.executeUpdate(sql);
			
			//examines
			sql = "CREATE TABLE examines( "
					+ " "
					+ "\"medical_id\" INTEGER, "
					+ "\"receptor_id\" INTEGER, "
					+ "FOREIGN KEY (\"medical_id\") REFERENCES doctor, "
					+ "FOREIGN KEY (\"receptor_id\") REFERENCES receptor, "
					+ "PRIMARY KEY (\"medical_id\", \"receptor_id\") "
					+ ")";
			stmt.executeUpdate(sql);
			
			
			//location
			sql = "CREATE TABLE \"location\" ( "
					+ "	\"id\"	INTEGER PRIMARY KEY AUTOINCREMENT, "
					+ "	\"latitude\"	FLOAT NOT NULL, "
					+ "	\"longitude\"	FLOAT NOT NULL "
					+ ")";
			stmt.executeUpdate(sql);
			
			//organ
			sql = "CREATE TABLE \"organ\" ( "
					+ "	\"id\"	INTEGER PRIMARY KEY AUTOINCREMENT, "
					+ "	\"id_type_organ\"	INTEGER, "
					+ "	\"size_organ\"	FLOAT, "
					+ "	\"available\"	BOOLEAN, "
					+ "	FOREIGN KEY(\"id_type_organ\") REFERENCES \"type_of_organ\"(\"id\") "
					+ ")";
			stmt.executeUpdate(sql);
			
			//receptor
			sql = "CREATE TABLE \"receptor\" ( "
					+ "	\"dni\"	INTEGER, "
					+ "	\"dob\"	date NOT NULL, "
					+ "	\"status\"	TEXT NOT NULL, "
					+ "	\"blood_type\"	TEXT NOT NULL, "
					+ "	\"alive\"	BOOLEAN NOT NULL, "
					+ "	\"urgency\" INTEGER NOT NULL, "
					+ "	\"id_antigen\"	INTEGER, "
					+ "	\"id_antibody\"	INTEGER, "
					+ "	\"id_location\"	INTEGER, "
					+ "	\"id_doctor_charge\"	INTEGER, "
					+ "	\"id_request\"	INTEGER, "
					+ "	PRIMARY KEY(\"dni\"), "
					+ "	CHECK(status IN ('Waiting','Accepted','Rejected','Operating')), "
					+ "	CHECK(blood_type IN ('A+','B+','AB+','O+','A-','B-','AB-','O-')), "
					+ "	FOREIGN KEY(\"id_antigen\") REFERENCES \"antigen\"(\"id\"), "
					+ "	FOREIGN KEY(\"id_location\") REFERENCES \"location\"(\"id\"), "
					+ "	FOREIGN KEY(\"id_antibody\") REFERENCES \"antibody\"(\"id\"), "
					+ "	FOREIGN KEY(\"id_doctor_charge\") REFERENCES \"doctor\"(\"medical_id\"), "
					+ "	FOREIGN KEY(\"id_request\") REFERENCES \"request\"(\"id\") "
					+ ")";
			stmt.executeUpdate(sql);
			
			//request
			sql = "CREATE TABLE request( "
					+ "id INTEGER PRIMARY KEY AUTOINCREMENT, "
					+ "id_type_organ INTEGER REFERENCES type_of_organ (id), "
					+ "received BOOLEAN, "
					+ "donor_DNI INTEGER DEFAULT NULL, "
					+ "size_organ FLOAT "
					+ ")";
			stmt.executeUpdate(sql);
			
			//type of organ
			sql = "CREATE TABLE type_of_organ ( "
					+ "id INTEGER PRIMARY KEY, "
					+ "name TEXT, "
					+ "lifespan INTEGER "
					+ ")";
			stmt.executeUpdate(sql);
			
		} catch (SQLException e) {
			// Do not complain if tables already exist
			if (!e.getMessage().contains("already exists")) {
				e.printStackTrace();
			}
		}
	}

}
