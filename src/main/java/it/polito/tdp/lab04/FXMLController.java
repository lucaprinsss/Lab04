/**
 * Sample Skeleton for 'Scene.fxml' Controller Class
 */

package it.polito.tdp.lab04;

import java.net.URL;
import java.util.LinkedList;
import java.util.ResourceBundle;

import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Model;
import it.polito.tdp.lab04.model.Studente;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {
	
	private Model model;

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="btbCercaIscrittiCorso"
    private Button btbCercaIscrittiCorso; // Value injected by FXMLLoader

    @FXML // fx:id="btnCercaCorsi"
    private Button btnCercaCorsi; // Value injected by FXMLLoader

    @FXML // fx:id="btnIscrivi"
    private Button btnIscrivi; // Value injected by FXMLLoader

    @FXML // fx:id="btnMatricola"
    private Button btnMatricola; // Value injected by FXMLLoader

    @FXML // fx:id="btnReset"
    private Button btnReset; // Value injected by FXMLLoader

    @FXML // fx:id="cmbCorso"
    private ComboBox<String> cmbCorso; // Value injected by FXMLLoader
    
    @FXML // fx:id="lblErrore"
    private Label lblErrore; // Value injected by FXMLLoader

    @FXML // fx:id="txtArea"
    private TextArea txtArea; // Value injected by FXMLLoader

    @FXML // fx:id="txtCognome"
    private TextField txtCognome; // Value injected by FXMLLoader

    @FXML // fx:id="txtMatricola"
    private TextField txtMatricola; // Value injected by FXMLLoader

    @FXML // fx:id="txtNome"
    private TextField txtNome; // Value injected by FXMLLoader

    @FXML
    void cercaNomeCognome(ActionEvent event) {
    	String matricola=txtMatricola.getText();
    	Integer matrInt=Integer.parseInt(matricola);
    	Studente ris=model.getNomeCognome(matrInt);
    	if(ris==null) {
    		lblErrore.setText("Matricola non trovata");
    		txtNome.clear();
    		txtCognome.clear();
    	} else {
    		lblErrore.setText("");
    		txtNome.setText(ris.getNome());
    		txtCognome.setText(ris.getCognome());
    	}
    }

    @FXML
    void doCercaCorsi(ActionEvent event) {
    	String matricola=txtMatricola.getText();
    	Integer matrInt=Integer.parseInt(matricola);
    	Studente studente= model.getStudente(matrInt); 
    	LinkedList<Corso> listaCorsi=  new LinkedList<Corso>();
    	Boolean trovato=true;
    	try {
			studente.getMatricola();
		} catch (Exception e) {
			trovato=false;
			txtArea.clear();
			lblErrore.setText("Matricola non trovata");
		}
    		
    	if(trovato) {
    		lblErrore.setText("");
    		listaCorsi = new LinkedList<Corso>(model.getCorsiDelloStudente(studente));
    		txtArea.clear();
    		for(Corso c: listaCorsi)
    			txtArea.appendText(c+"\n");
    	}
    }

    @FXML
    void doCercaIscittiCorso(ActionEvent event) {
    	String codCorso=cmbCorso.getValue();
    	if(codCorso.compareTo("")==0) {
    		lblErrore.setText("Inserire un corso");
    	} else {
    		lblErrore.setText("");
    		Corso corso=model.getCorso(codCorso);
      		txtArea.clear();
    		for(Studente s: model.getStudentiIscrittiAlCorso(corso)) {
    			txtArea.appendText(s.toString()+"\n");
    		}
    	}
    }

    @FXML
    void doIscrivi(ActionEvent event) {
    	String cor=cmbCorso.getValue();
    	String mat=txtMatricola.getText();
    	Integer matricola=Integer.parseInt(mat);
    	Corso corso=model.getCorso(cor);
    	Studente studente=model.getStudente(matricola);
    	LinkedList<Studente> listaStudenti=new LinkedList<Studente>(model.getStudentiIscrittiAlCorso(corso));
    	Boolean trovato=false;
    	
    	for(Studente s:listaStudenti) 
    		if(s.equals(studente)) { 
    			trovato=true;
    			break;
    		}
    	
    	if(trovato) {
    		txtArea.setText("Studente già iscritto al corso");	
    	} else {
    		if(model.inscriviStudenteACorso(studente, corso)) {
    			txtArea.setText("Lo studente è stato iscritto correttamente al corso");
    		} else {
    			txtArea.setText("Non è stato possibile iscrivere lo studente al corso");
    		}
    	}
    	lblErrore.setText("");
    }

    @FXML
    void doreset(ActionEvent event) {
    	txtMatricola.clear();
    	txtNome.clear();
    	txtCognome.clear();
    	txtArea.clear();
    	lblErrore.setText("");
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
    	 assert btbCercaIscrittiCorso != null : "fx:id=\"btbCercaIscrittiCorso\" was not injected: check your FXML file 'Scene.fxml'.";
         assert btnCercaCorsi != null : "fx:id=\"btnCercaCorsi\" was not injected: check your FXML file 'Scene.fxml'.";
         assert btnIscrivi != null : "fx:id=\"btnIscrivi\" was not injected: check your FXML file 'Scene.fxml'.";
         assert btnMatricola != null : "fx:id=\"btnMatricola\" was not injected: check your FXML file 'Scene.fxml'.";
         assert btnReset != null : "fx:id=\"btnReset\" was not injected: check your FXML file 'Scene.fxml'.";
         assert cmbCorso != null : "fx:id=\"cmbCorso\" was not injected: check your FXML file 'Scene.fxml'.";
         assert lblErrore != null : "fx:id=\"lblErrore\" was not injected: check your FXML file 'Scene.fxml'.";
         assert txtArea != null : "fx:id=\"txtArea\" was not injected: check your FXML file 'Scene.fxml'.";
         assert txtCognome != null : "fx:id=\"txtCognome\" was not injected: check your FXML file 'Scene.fxml'.";
         assert txtMatricola != null : "fx:id=\"txtMatricola\" was not injected: check your FXML file 'Scene.fxml'.";
         assert txtNome != null : "fx:id=\"txtNome\" was not injected: check your FXML file 'Scene.fxml'.";


    }
    
    public void setModel (Model model) {
    	this.model=model;
    	cmbCorso.getItems().clear();
    	cmbCorso.getItems().add("");
    	for(Corso c: model.getTuttiICorsi()) {
    		cmbCorso.getItems().add(c.getCodins());
    	}
    }

}
