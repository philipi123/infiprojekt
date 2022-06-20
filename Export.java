package elektronikverwaltung_projekt;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.json.simple.JSONObject;

public class Export {
	@SuppressWarnings("unchecked") // unchecked: man hat nicht nachgeschaut wegen schlüssel ?? -->json
									// dokumentation nachsehen
	public static void export(Connection c, String export) {
		try {
			FileWriter f = new FileWriter(export);
			JSONObject json = new JSONObject();
			String s = "";

			Statement s1 = c.createStatement();
			String sql = "select kunde, produkt, anzahl, produktname, artikelnummer, preis, vorname, nachname, id, adresse, "
					+ "plz, anzahl*preis as rechnungsbetrag from bestellen b join produkte p on b.produkt = p.artikelnummer "
					+ "join kunden k on b.kunde = k.id;";
			ResultSet rs = s1.executeQuery(sql);

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

				// vorname, nachname, id, adresse, plz, produktname, anzahl, preis,
				// rechnungsbetrag

				json.put("vorname", vorname);
				json.put("nachname", nachname);
				json.put("id", id);
				json.put("adresse", adresse);
				json.put("plz", plz);
				json.put("produktname", produktname);
				json.put("anzahl", anzahl);
				json.put("preis", preis);
				json.put("rechnungsbetrag", rechnungsbetrag);
				s = s + json;
			}
			f.write(s);
			f.flush();
			f.close();
			rs.close();
			System.out.printf("datei erfolgreich erstellt! \nspeicherort: %s\n", export);
			System.out.println();

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
