package it.polito.tdp.parole;

import it.polito.tdp.parole.model.*;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController 
{
	Parole elenco;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField txtParola;

    @FXML
    private Button btnInserisci;
    
    @FXML
    private Button btnCancella;
    
    @FXML
    private TextArea txtResult;
    
    @FXML
    private TextArea txtInfo;

    @FXML
    private Button btnReset;

    @FXML
    void doInsert(ActionEvent event) 
    {    	
    	String parolaInserita = this.txtParola.getText();
    	this.txtParola.clear();
		this.txtInfo.clear();
    	if(parolaInserita.isEmpty() || parolaInserita.isBlank())
    		this.displayInserisciParola();
    	else
    	{
    		long istanteIniziale = System.nanoTime();
    		
    		if(!this.elenco.addParola(parolaInserita))
    		{
    			this.displayParolaGiaInserita(parolaInserita);
    			return;
    		}
    		this.displayParolaInseritaCorrettamente(parolaInserita);
    		this.displayTempoDiEsecuzioneDa(istanteIniziale);
        	this.displayElenco(this.elenco.getElenco());
    	} 
    }

	private void displayElenco(List<String> elenco)
	{
		String titolo = "- Elenco parole inserite:\n";
		StringBuilder risultato = new StringBuilder();
		for(String s : elenco)
		{
			if(risultato.length() > 0)
				risultato.append("\n");
			
			risultato.append(s);
		}
		risultato.insert(0, titolo);
		this.txtResult.setText(risultato.toString());	
	}
	
	@FXML
    void doDelete(ActionEvent event) 
	{		
		String parolaInserita = this.txtResult.getSelectedText();
    	
		this.txtInfo.clear();
    	if(parolaInserita.isEmpty() || parolaInserita.isBlank())
    		this.displaySelezionaParola();
    	else
    	{
    		long istanteIniziale = System.nanoTime();
    		if(!this.elenco.deleteParola(parolaInserita))
    		{
    			this.displayParolaNonAncoraInserita(parolaInserita);
    			return;
    		}
    		this.displayParolaRimossaCorrettamente(parolaInserita);
        	this.displayTempoDiEsecuzioneDa(istanteIniziale);
        	this.displayElenco(this.elenco.getElenco());
    	}    
    }

	@FXML
    void doReset(ActionEvent event) 
    {
		long istanteIniziale = System.nanoTime();
    	this.elenco.reset();
    	this.txtResult.clear();
    	this.txtInfo.setText("Reset di tutte le parole effettuato");
    	this.displayTempoDiEsecuzioneDa(istanteIniziale);
    }

    @FXML
    void initialize() 
    {
    	assert txtParola != null : "fx:id=\"txtParola\" was not injected: check your FXML file 'Scene_lab01.fxml'.";
        assert btnInserisci != null : "fx:id=\"btnInserisci\" was not injected: check your FXML file 'Scene_lab01.fxml'.";
        assert btnCancella != null : "fx:id=\"btnCancella\" was not injected: check your FXML file 'Scene_lab01.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Scene_lab01.fxml'.";
        assert txtInfo != null : "fx:id=\"txtInfo\" was not injected: check your FXML file 'Scene_lab01.fxml'.";
        assert btnReset != null : "fx:id=\"btnReset\" was not injected: check your FXML file 'Scene_lab01.fxml'.";

        elenco = new ArrayParole() ;
    }
    
    private void displayTempoDiEsecuzioneDa(long istanteInizialeInNanoSec)
	{
    	double microSecondi = (double)(System.nanoTime() - istanteInizialeInNanoSec)/ Math.pow(10, 6);
    	this.txtInfo.appendText(String.format("\nTempo di esecuzione dell'operazione: %.3f microsecondi", microSecondi));	
	}
    
    private void displayParolaGiaInserita(String parolaGiaInserita)
    {
    	this.txtInfo.setText(String.format("Errore: la parola \"%s\" e' gia' stata inserita!", parolaGiaInserita));
    }

	private void displayParolaInseritaCorrettamente(String parolaInserita)
	{
		this.txtInfo.setText(String.format("Parola \"%s\" inserita correttamente!", parolaInserita));
	}

	private void displayParolaNonAncoraInserita(String parolaInserita)
	{
		this.txtInfo.setText(String.format("Errore: la parola \"%s\" non e' ancora stata inserita!", parolaInserita));		
	}

	private void displayParolaRimossaCorrettamente(String parolaRimossa)
	{
		this.txtInfo.setText(String.format("Parola \"%s\" rimossa correttamente!", parolaRimossa));
	}
	
	private void displayInserisciParola()
	{
		this.txtInfo.setText("Errore: inserisci una parola valida!");	
	}
	private void displaySelezionaParola()
	{
		this.txtInfo.setText("Errore: seleziona una parola valida!");
	}

}
