package elektronikverwaltung_projekt;

import java.sql.*;

import java.io.*;

import hausuebung_3.Personen;

public class sql {

	public static void dropprodukte(Connection c) {
		try {
			Statement s = c.createStatement();
			String sql = "drop table if exists produkte;";
			s.executeUpdate(sql);
			s.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void createprodukte(Connection c) {
		try {
			Statement s = c.createStatement();
			String sql = "create table if not exists produkte (produktname varchar(100), artikelnummer int not null, preis int, primary key(artikelnummer));";
			s.executeUpdate(sql);
			s.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void insertprodukte(Connection c, String produktname, int artikelnummer, int preis) {
		try {
			String sql = "insert into produkte (produktname, artikelnummer, preis) values (?, ?, ?);";
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setString(1, produktname);
			ps.setInt(2, artikelnummer);
			ps.setInt(3, preis);
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void dropkunden(Connection c) {
		try {
			Statement s = c.createStatement();
			String sql = "drop table if exists kunden;";
			s.executeUpdate(sql);
			s.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void createkunden(Connection c) {
		try {
			Statement s = c.createStatement();
			String sql = "create table if not exists kunden (vorname varchar(20), nachname varchar(20), id int not null, adresse varchar(50), plz int, primary key(id));";
			s.executeUpdate(sql);
			s.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void insertkunden(Connection c, String vorname, String nachname, int id, String adresse, int plz) {
		try {
			String sql = "insert into kunden (vorname, nachname, id, adresse, plz) values (?, ?, ?, ?, ?);";
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setString(1, vorname);
			ps.setString(2, nachname);
			ps.setInt(3, id);
			ps.setString(4, adresse);
			ps.setInt(5, plz);
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void dropbestellen(Connection c) {
		try {
			Statement s = c.createStatement();
			String sql = "drop table if exists bestellen;";
			s.executeUpdate(sql);
			s.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void createbestellen(Connection c) {
		try {
			Statement s = c.createStatement();
			String sql = "create table if not exists bestellen (kunde int, produkt int, anzahl int, "
					+ "foreign key(kunde) references kunden(id) on delete restrict, foreign key (produkt) references produkte(artikelnummer) on delete restrict);";
			s.executeUpdate(sql);
			s.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void bestellen(Connection c, int id, int artikelnummer, String vorname, String nachname) {
		try {
			String sql = "insert into bestellen (id, artikelnummer, vorname, nachname) values (?, ?, ?, ?);";
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setInt(1, id);
			ps.setInt(2, artikelnummer);
			ps.setString(3, vorname);
			ps.setString(4, nachname);
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void kundenanzeige(Connection c) {
		try {
			Statement stmt = c.createStatement();
			String sql = "select vorname, nachname, id from kunden;";
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				String vorname = rs.getString("vorname");
				String nachname = rs.getString("nachname");
				int id = rs.getInt("id");
				System.out.printf("%s \t %2s \t %s \t", vorname, nachname, id);
				System.out.println();
			}
			rs.close();
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void produktanzeige(Connection c) {
		try {
			Statement stmt = c.createStatement();
			String sql = "select produktname, artikelnummer, preis from produkte;";
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				String produktname = rs.getString("produktname");
				int artikelnummer = rs.getInt("artikelnummer");
				int preis = rs.getInt("preis");
				System.out.printf("%s \t %2s \t %s \t", produktname, artikelnummer, preis);
				System.out.println();
			}
			rs.close();
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
