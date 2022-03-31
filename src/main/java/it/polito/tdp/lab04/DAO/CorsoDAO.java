package it.polito.tdp.lab04.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Studente;

public class CorsoDAO {
	
	
	 //Ottengo tutti i corsi salvati nel Db
	public List<Corso> getTuttiICorsi() {
		final String sql = "SELECT * FROM corso";
		List<Corso> corsi = new LinkedList<Corso>();

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				String codins = rs.getString("codins");
				int numeroCrediti = rs.getInt("crediti");
				String nome = rs.getString("nome");
				int periodoDidattico = rs.getInt("pd");
				corsi.add(new Corso(codins,numeroCrediti,nome,periodoDidattico));
			}

			rs.close();
			conn.close();
			return corsi;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Errore Db", e);
		}
	}
	
	//Dato un codice insegnamento, ottengo il corso
	public Corso getCorso(String codins) {
		final String sql = "SELECT * "
				+ "FROM corso "
				+ "WHERE codins = ? ";
		Corso risultato=null;

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, codins);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				int numeroCrediti = rs.getInt("crediti");
				String nome = rs.getString("nome");
				int periodoDidattico = rs.getInt("pd");
				risultato=new Corso(codins, numeroCrediti, nome, periodoDidattico);
			}

			rs.close();
			conn.close();
			return risultato;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Errore Db", e);
		}
	}


	//Ottengo tutti gli studenti iscritti al Corso
	public List<Studente> getStudentiIscrittiAlCorso(Corso corso) {
		final String sql = "SELECT s.matricola, cognome, nome, CDS "
				+ "FROM iscrizione i, studente s "
				+ "WHERE i.matricola=s.matricola AND codins = ? ";
		List<Studente> studenti = new LinkedList<Studente>();

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, corso.getCodins());		
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				Integer matr = rs.getInt("matricola");
				String cognome = rs.getString("cognome");
				String nome = rs.getString("nome");
				String CDS = rs.getString("CDS");
				studenti.add(new Studente(matr,cognome,nome,CDS));
			}

			rs.close();
			conn.close();
			return studenti;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Errore Db", e);
		}
	}


	 //Data una matricola ed il codice insegnamento, iscrivi lo studente al corso.
	public boolean inscriviStudenteACorso(Studente studente, Corso corso) {
		final String sql = "INSERT INTO iscrizione (matricola,codins) VALUES (?, ?)";

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, studente.getMatricola());
			st.setString(2, corso.getCodins());
			ResultSet rs = st.executeQuery();

			rs.close();
			conn.close();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

}
