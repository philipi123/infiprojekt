package elektronikverwaltung_projekt;

//  /Users/philip/Desktop/HTL/INFI/kunden.csv

// /Users/philip/Desktop/HTL/INFI/produkte2.csv
// /Users/philip/Desktop/HTL/INFI/datei.txt

import java.io.Console;
import java.io.FileNotFoundException;
import java.util.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class menu {

	public static Connection createConnection() {

		try {
			return DriverManager.getConnection("jdbc:mysql://localhost:3306/infiprojekt", "root", "");
		} catch (SQLException e) {
			e.printStackTrace();
			System.exit(1);
			return null;
		}
	}

	public static void printMenu(String[] options) {
		for (String option : options) {
			System.out.println(option);
		}
		System.out.println();
		System.out.print("Option wählen : ");
	}

	private static String[] options = { "1- datei einlesen (datensatz kunden)",
			"2- datei einlesen (datensatz produkte)", "3- bestellung", "4- export als JSON", "5- beenden" };

	public static void main(String[] args) {
		Connection c = createConnection();
		sql.dropbestellen(c);
		sql.dropkunden(c);
		sql.dropprodukte(c);
		sql.createkunden(c);
		sql.createprodukte(c);
		sql.createbestellen(c);

		Scanner scanner = new Scanner(System.in);
		int option = 1;
		while (option != 6) {
			System.out.println("Willkommen!");
			System.out.println();

			printMenu(options);
			try {
				option = scanner.nextInt();
				switch (option) {
				case 1:
					String pfad;
					System.out.println();
					System.out.println("bitte pfad eingeben");
					pfad = scanner.next();
					option1(c, pfad);
					System.out.println();
					System.out.println(pfad);
					System.out.println();
					break;
				case 2:
					String pfad2;
					System.out.println();
					System.out.println("bitte pfad eingeben");
					pfad2 = scanner.next();
					option2(c, pfad2);
					System.out.println();
					System.out.println(pfad2);
					System.out.println();
					break;
				case 3:
					int kunde;
					int produkt;
					int anzahl;
					
					
					
					System.out.println();
					System.out.println("Welcher Kunde bestellt? (id auswählen)");
					System.out.println("vorname-nachname-id");
					System.out.println();
					sql.kundenanzeige(c);
					System.out.println();
					kunde = scanner.nextInt();
					System.out.println("Welches Produkt wird bestellt? (artikelnummer auswählen)");
					System.out.println("produktname-id-preis");
					System.out.println();
					sql.produktanzeige(c);
					System.out.println();
					produkt = scanner.nextInt();
					System.out.println("Wie oft wird dieses Produkt bestellt? (anzahl eingeben)");
					anzahl = scanner.nextInt();
					option3(c, kunde, produkt, anzahl);
					break;
				case 4:
					String export;
					System.out.println();
					System.out.println("bitte pfad für die neue datei angeben (mit .txt)");
					System.out.println();
					export = scanner.next();
					option4(c, export);
					break;
				case 5:
					System.out.println();
					System.out.println("a u f  w i e d e r s e h e n");
					System.exit(0);
					scanner.close();
					break;

				}

			} catch (Exception ex) {
				ex.printStackTrace();
				// System.out.println("Please enter an integer value between 1 and " +
				// options.length);
				scanner.next();
			}

		}

		
	}

	public static void option1(Connection c, String pfad) {

		Einlesen.einlesenlol(c, pfad);
		System.out.println("datei eingelesen");
	}

	public static void option2(Connection c, String pfad2) {
		Einlesen.einlesenlol2(c, pfad2);
		System.out.println("datei eingelesen");
	}

	public static void option3(Connection c, int kunde, int produkt, int anzahl) {
		Bestellung.bestellen(c, kunde, produkt, anzahl);

	}

	public static void option4(Connection c, String export) {
		Export.export(c, export);
	}

}
