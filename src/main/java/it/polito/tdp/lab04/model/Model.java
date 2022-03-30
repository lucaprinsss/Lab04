package it.polito.tdp.lab04.model;

import java.util.List;

import it.polito.tdp.lab04.DAO.CorsoDAO;

public class Model {
	
	CorsoDAO corsoDAO=new CorsoDAO();
	
	public List<Corso> getTuttiICorsi() {
		return corsoDAO.getTuttiICorsi();
	}
	
	

}
