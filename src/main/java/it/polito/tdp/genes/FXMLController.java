/**
 * Sample Skeleton for 'Scene.fxml' Controller Class
 */

package it.polito.tdp.genes;

import java.net.URL;
import java.util.ResourceBundle;

import it.polito.tdp.genes.model.Adiacenza;
import it.polito.tdp.genes.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {
	
	private Model model ;

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="btnContaArchi"
    private Button btnContaArchi; // Value injected by FXMLLoader

    @FXML // fx:id="btnRicerca"
    private Button btnRicerca; // Value injected by FXMLLoader

    @FXML // fx:id="txtSoglia"
    private TextField txtSoglia; // Value injected by FXMLLoader

    @FXML // fx:id="txtResult"
    private TextArea txtResult; // Value injected by FXMLLoader

    @FXML
    void doContaArchi(ActionEvent event) {
    	
      	Double min= model.getPesoMin();
    	double max= model.getPesoMax();
    	double soglia;
    	try {
    		soglia= Double.parseDouble(txtSoglia.getText());
    	}catch(NumberFormatException e) {
    		e.printStackTrace();
    		txtResult.setText("Inserire valore numerico compreso tra i valori minimo e massimo "+min+" "+max);
    		return;
    	}
    	
    	if(soglia<min || soglia>max) {
    		txtResult.setText("Inserire valore numerico compreso tra i valori minimo e massimo "+min+" "+max);
    		return;
    	}
    	
//    	txtResult.appendText("Archi peso <S: \n");
//    	for(Adiacenza a: model.getContaArchiMinore(soglia)) {
//    		txtResult.appendText(a.toString()+"\n");
//    	}
//    	txtResult.appendText("Archi peso >S: \n");
//    	for(Adiacenza a: model.getContaArchiMaggiore(soglia)) {
//    		txtResult.appendText(a.toString()+"\n");
//    	}
    	
    	txtResult.appendText("Archi peso <S: "+model.getContaArchiMinore(soglia).size()+"; ");
    	txtResult.appendText("Archi peso >S: "+model.getContaArchiMaggiore(soglia).size()+"\n");


    	

    }

    @FXML
    void doRicerca(ActionEvent event) {

    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert btnContaArchi != null : "fx:id=\"btnContaArchi\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnRicerca != null : "fx:id=\"btnRicerca\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtSoglia != null : "fx:id=\"txtSoglia\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Scene.fxml'.";

    }

	public void setModel(Model model) {
		this.model = model ;
		
		model.creaGrafo();
		txtResult.appendText("Grafo creato: #vertici "+model.getNVertici()+" #archi "+model.getNArchi()+"\n"+
		"Peso minimo: "+model.getPesoMin()+" Peso massimo: "+model.getPesoMax()+"\n");
		
		
	}
}
