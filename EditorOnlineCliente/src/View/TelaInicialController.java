/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author grmir
 */
public class TelaInicialController implements Initializable {

    private ControladorDeTelas controlador;
    @FXML
    private TextArea textoArquivo;
    @FXML
    private TextArea listaArquivos;
    @FXML
    private TextField arquivoInput;
    @FXML
    private Button abrirButton;
    @FXML
    private Button atualizarLista;
    @FXML
    private Button salvarArquivo;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        controlador = new ControladorDeTelas();
    }

    @FXML
    private void abrirArquivo(ActionEvent event) {
        String texto = controlador.getArquivo(arquivoInput.getText());
        textoArquivo.setText(texto);
    }

    @FXML
    private void atualizarLista(ActionEvent event) {
        String[] arquivos = controlador.getListaArquivos();
        if (arquivos != null) {
            for (String arquivo : arquivos) {
                listaArquivos.setText(listaArquivos.getText() + arquivo + "\n");
            }
        }else{
            System.out.println("A lista de arquivos é nula");
        }
    }

    @FXML
    private void salvarArquivo(ActionEvent event) {
    }

}
