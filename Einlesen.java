package elektronikverwaltung_projekt;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import hausuebung_3.Personen;

public class Einlesen {
	public static void einlesenlol(Connection c, String pfad) {
		try {
			File f = new File(pfad); // TODO bei programmstart nach path fragen?
			Scanner s = new Scanner(f); // scanner nicht in try/catch?
			String string = "";
			s.nextLine();

			while (s.hasNextLine()) {
				string = s.nextLine();
				String[] str = string.split(";");
				// System.out.println(str.length);

				String sql = "insert into kunden (vorname, nachname, id, adresse, plz) values (?, ?, ?, ?, ?);";
				PreparedStatement stmt = c.prepareStatement(sql);
				stmt.setString(1, str[0]);
				stmt.setString(2, str[1]);
				stmt.setInt(3, Integer.parseInt(str[2])); // setint parseinteger?
				stmt.setString(4, str[3]);
				stmt.setInt(5, Integer.parseInt(str[4]));
				stmt.executeUpdate();
				stmt.close();
			}
			s.close();
			show(c); // TODO methode programmieren
			// c.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}

	public static void einlesenlol2(Connection c, String pfad) {
		try {
			File f = new File(pfad); // TODO bei programmstart nach path fragen?
			Scanner s = new Scanner(f); // scanner nicht in try/catch?
			String string = "";
			s.nextLine();

			while (s.hasNextLine()) {
				string = s.nextLine();
				String[] str = string.split(";");
				// System.out.println(str.length);

				String sql = "insert into produkte (produktname, artikelnummer, preis) values (?, ?, ?);";
				PreparedStatement stmt = c.prepareStatement(sql);
				stmt.setString(1, str[0]);
				stmt.setInt(2, Integer.parseInt(str[1]));
				stmt.setInt(3, Integer.parseInt(str[2]));
				stmt.executeUpdate();
				stmt.close();
			}
			s.close();
			show2(c); // TODO methode programmieren
			// c.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}

	public static void show(Connection c) {
		try {
			Statement stmt = c.createStatement();
			String sql = "select vorname, nachname, id, adresse, plz from kunden;";
			ResultSet rs = stmt.executeQuery(sql);
			System.out.println("folgende daten wurden eingelesen:");
			while (rs.next()) {
				String vorname = rs.getString("vorname"); // string bei int??
				String nachname = rs.getString("nachname");
				String id = rs.getString("id");
				String adresse = rs.getString("adresse");
				String plz = rs.getString("plz");

				System.out.println();

				System.out.printf("%s \t %2s \t %s \t %s \t %s \n", vorname, nachname, id, adresse, plz);
				System.out.println();
				// System.out.printf("Vorname: " + vorname + " Nachname: " + nachname + " ID: "
				// + id + "Adresse" + adresse + "PLZ" + plz);

			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void show2(Connection c) {
		try {
			Statement stmt = c.createStatement();
			String sql = "select produktname, artikelnummer, preis from produkte;";
			ResultSet rs = stmt.executeQuery(sql);
			System.out.println("folgende daten wurden eingelesen:");
			while (rs.next()) {
				String produktname = rs.getString("produktname"); // string bei int??
				int artikelnummer = rs.getInt("artikelnummer");
				int preis = rs.getInt("preis");

				System.out.println();
				System.out.printf("%s \t %2s \t %s \t \n", produktname, artikelnummer, preis);
				System.out.println();
				// System.out.printf("Vorname: " + vorname + " Nachname: " + nachname + " ID: "
				// + id + "Adresse" + adresse + "PLZ" + plz);

			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
