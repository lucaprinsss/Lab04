package it.polito.tdp.lab04.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Studente;

public class StudenteDAO {

	public Studente getNomeCognome(int matricola) {
		final String sql = "SELECT * "
				+ "FROM studente "
				+ "WHERE matricola =? ";
		Studente risultato=null;

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, matricola);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				String cognome=rs.getString("cognome");
				String nome=rs.getString("nome");
				String CDS=rs.getString("CDS");			
				risultato=new Studente(matricola,cognome,nome,CDS);
			}

			rs.close();
			conn.close();
			return risultato;			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Errore Db", e);
		}
	}
	
	public Studente getStudente(Integer matricola) {
		final String sql = "SELECT * "
				+ "FROM studente "
				+ "WHERE matricola = ? ";
		Studente risultato=null;

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, matricola);		
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				String cognome=rs.getString("cognome");
				String nome=rs.getString("nome");
				String CDS=rs.getString("CDS");				
				risultato=new Studente(matricola,cognome,nome,CDS);
			}

			rs.close();
			conn.close();			
			return risultato;			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Errore Db", e);
		}
	}

	public LinkedList<Corso> getCorsidelloStudente(Studente studente) {
		final String sql = "SELECT c.codins, crediti, nome, pd "
				+ "FROM iscrizione i, corso c "
				+ "WHERE i.codins=c.codins AND i.matricola=? ";
		LinkedList<Corso> risultato=new LinkedList<Corso>();

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, studente.getMatricola());		
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				String codins = rs.getString("codins");
				int numeroCrediti = rs.getInt("crediti");
				String nome = rs.getString("nome");
				int periodoDidattico = rs.getInt("pd");				
				risultato.add(new Corso(codins, numeroCrediti, nome, periodoDidattico));
			}

			rs.close();
			conn.close();			
			return risultato;			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Errore Db", e);
		}
	}

}
