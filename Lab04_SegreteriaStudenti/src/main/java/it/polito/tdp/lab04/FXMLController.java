/**
 * Sample Skeleton for 'Scene.fxml' Controller Class
 */

package it.polito.tdp.lab04;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Model;
import it.polito.tdp.lab04.model.Studente;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {
	private Model model;
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<Corso> comboBoxCorso;

    @FXML
    private Button btnCerca;

    @FXML
    private TextField txtMat;

    @FXML
    private Button btnMat;

    @FXML
    private TextField nomeMat;

    @FXML
    private TextField cognomeMat;

    @FXML
    private Button btnCercaCorso;

    @FXML
    private Button btnIscrivi;

    @FXML
    private TextArea txtAreaCorsi;

    @FXML
    private Button Reset;

    @FXML
    void Iscrivi(ActionEvent event) {
    	try
    	{
    		Corso c=comboBoxCorso.getValue();
    		int matricola=Integer.parseInt(txtMat.getText());
    		Studente s=this.model.getStudente(matricola);
    		boolean check=this.model.checkStudenteCorso(s, c);
    		if(check==true)
    			txtAreaCorsi.setText("Studente iscritto a corso");
    		else txtAreaCorsi.setText("Studente non iscritto a corso");
    	}
    	catch(NullPointerException npe)
    	{
    		txtAreaCorsi.setText("Errore! Seleziona un corso");
    	}
    	catch(NumberFormatException nfe)
    	{
    		txtAreaCorsi.setText("Errore! Devi inserire un numero");
    	}	
    }

    @FXML
    void Reset(ActionEvent event) {
    	txtMat.clear();
    	nomeMat.clear();
    	cognomeMat.clear();
    	txtAreaCorsi.clear();
    	comboBoxCorso.setValue(null);
    }
    
    @FXML
    void trovaValori(ActionEvent event) {
    	int matricola;
    	Studente s=null;
    	try
    	{
    		matricola=Integer.parseInt(txtMat.getText());
    	}
    	catch(NumberFormatException nfe)
    	{
    		txtAreaCorsi.setText("Devi inserire un numero!");
    		return;
    	}
    	s=this.model.getStudente(matricola);
    	if(s!=null)
    	{
    		nomeMat.setText(s.getNome());
    		cognomeMat.setText(s.getCognome());
    	}
    	else
    	{
    		nomeMat.setText("matricola non trovata");
    	}
    	
    }

    @FXML
    void cercaIscritti(ActionEvent event) {
    	try
    	{
    		txtAreaCorsi.clear();
    		Corso c=comboBoxCorso.getValue();
    		List<Studente>listaStud=model.getStudenti(c);
    		for(Studente s:listaStud)
    		{
    			txtAreaCorsi.appendText(s.getMatricola()+" "+s.getNome()+" "+s.getCognome()+" "+s.getCds()+"\n");
    		}
    	}
    	catch(NullPointerException npe)
    	{
    		txtAreaCorsi.setText("Errore! Seleziona un corso");
    	}
    }
    
    @FXML
    void cercaCorsi(ActionEvent event) {
    	try
    	{
    		txtAreaCorsi.clear();
    		int matricola=Integer.parseInt(txtMat.getText());
    		List<Corso>listaCorsi=model.getCorsiIscritto(matricola);
    		for(Corso c:listaCorsi)
    		{
    			txtAreaCorsi.appendText(c.getCodice()+" "+c.getCrediti()+" "+c.getNome()+" "+c.getPd()+"\n");
    		}
    	}
    	catch(NumberFormatException nfe)
    	{
    		txtAreaCorsi.setText("Errore! Devi inserire un numero");
    	}	
    }
    
    @FXML
    void initialize() {
        assert comboBoxCorso != null : "fx:id=\"choiceCorso\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnCerca != null : "fx:id=\"btnCerca\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtMat != null : "fx:id=\"txtMat\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnMat != null : "fx:id=\"checkRight\" was not injected: check your FXML file 'Scene.fxml'.";
        assert nomeMat != null : "fx:id=\"nomeMat\" was not injected: check your FXML file 'Scene.fxml'.";
        assert cognomeMat != null : "fx:id=\"cognomeMat\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnCercaCorso != null : "fx:id=\"btnCercaCorso\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnIscrivi != null : "fx:id=\"btnIscrivi\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtAreaCorsi != null : "fx:id=\"txtAreaCorsi\" was not injected: check your FXML file 'Scene.fxml'.";
        assert Reset != null : "fx:id=\"Reset\" was not injected: check your FXML file 'Scene.fxml'.";
        model=new Model();
    }
    
    public void setModel(Model model)
    {
    	this.model=model;
    	List<Corso> corsi=this.model.getTuttiCorsi();
    	comboBoxCorso.getItems().addAll(corsi);
    }
}
