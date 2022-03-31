package it.polito.tdp.lab04.model;

import java.util.List;

import it.polito.tdp.lab04.DAO.CorsoDAO;
import it.polito.tdp.lab04.DAO.StudenteDAO;

public class Model {
	
	CorsoDAO corsoDAO=new CorsoDAO();
	StudenteDAO studenteDAO=new StudenteDAO();
	
	public List<Corso> getTuttiICorsi() {
		return this.corsoDAO.getTuttiICorsi();
	}
	
	public Studente getNomeCognome(int matricola) {
		return studenteDAO.getNomeCognome(matricola);
	}
	
	public Corso getCorso(String codins) {
		return this.corsoDAO.getCorso(codins);
	}

	public List<Studente> getStudentiIscrittiAlCorso(Corso corso) {
		return this.corsoDAO.getStudentiIscrittiAlCorso(corso);
	}
	
	public Studente getStudente(Integer matricola) {
		return studenteDAO.getStudente(matricola);
	}

	public List<Corso> getCorsiDelloStudente(Studente studente) {
		return studenteDAO.getCorsidelloStudente(studente);
	}
	
	public List<Corso> getCorsidelloStudente(Studente studente) {
		return studenteDAO.getCorsidelloStudente(studente);
	}
	
	public boolean inscriviStudenteACorso(Studente studente, Corso corso) {
		return corsoDAO.inscriviStudenteACorso(studente, corso);
	}
}
