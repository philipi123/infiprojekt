package elektronikverwaltung_projekt;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Bestellung {
	public static void bestellen(Connection c, int kunde, int produkt, int anzahl) {
		try {
			String sql = "insert into bestellen (kunde, produkt, anzahl) values (?, ?, ?);";
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setInt(1, kunde);
			ps.setInt(2, produkt);
			ps.setInt(3, anzahl);
			ps.executeUpdate();
			ps.close();
			show(c);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public static void show(Connection c) {
		try {
			Statement stmt = c.createStatement();
			// String sql = "select * from bestellen b join produkte p on b.produkt =
			// p.artikelnummer join kunden k on b.kunde = k.id;";
			String sql = "select kunde, produkt, anzahl, produktname, artikelnummer, preis, vorname, nachname, id, adresse, "
					+ "plz, anzahl*preis as rechnungsbetrag from bestellen b join produkte p on b.produkt = p.artikelnummer "
					+ "join kunden k on b.kunde = k.id;";
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				int kunde = rs.getInt("kunde");
				int produkt = rs.getInt("produkt");
				int anzahl = rs.getInt("anzahl");
				String produktname = rs.getString("produktname");
				int artikelnummer = rs.getInt("artikelnummer");
				int preis = rs.getInt("preis");
				String vorname = rs.getString("vorname");
				String nachname = rs.getString("nachname");
				String id = rs.getString("id");
				String adresse = rs.getString("adresse");
				String plz = rs.getString("plz");
				int rechnungsbetrag = rs.getInt("rechnungsbetrag");

				System.out.println("So sieht die Bestellung aus:");
				System.out.println();
				System.out.printf(
						" Name: %s %s \n KundenID: %s \n Adresse: %s %s \n Produktname: %s \n Anzahl: %s \n Stückpreis: %s€ \n Gesamtbetrag: %s€\n",
						vorname, nachname, id, adresse, plz, produktname, anzahl, preis, rechnungsbetrag);
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
