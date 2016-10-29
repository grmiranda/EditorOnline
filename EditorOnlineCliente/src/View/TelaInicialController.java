/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.Controller;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

/**
 * FXML Controller class
 *
 * @author grmir
 */
public class TelaInicialController implements Initializable {

    private ControladorDeTelas controlador;
    private Controller controller;
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
    
    private boolean capslook;
    private boolean shift;
    private String nomeArquivo;
    private String texto;
    private int edicao;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        controlador = new ControladorDeTelas();
        controller = Controller.getInstance();
        controller.setControladorTela(this);
        nomeArquivo = "";
        texto = "";
        edicao = 0;
    }

    @FXML
    private void abrirArquivo(ActionEvent event) {
        if(!nomeArquivo.equals("")){
            controller.fecharArquivo(nomeArquivo);
        }
         //sair do arquivo caso ja tenha um aberto
        String texto = controlador.getArquivo(arquivoInput.getText());
        nomeArquivo = arquivoInput.getText();
        this.texto = texto;
        textoArquivo.setText(texto);
        capslook = false;
        shift = false;
    }

    @FXML
    private void atualizarLista(ActionEvent event) {
        String[] arquivos = controlador.getListaArquivos();
        listaArquivos.setText(" ");
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
        //comando para salvar arquivo
    }

    public void atualizarArquivo(String msg) {
        String info[] = msg.split(";");
        if(info[0].equals("#01")){
            //adicionando
            textoArquivo.insertText(Integer.parseInt(info[2]), info[1]);
        }else if(info[0].equals("#02")){
            // removendo
            textoArquivo.deleteText(Integer.parseInt(info[2]), Integer.parseInt(info[2]) + 1);
        }
        texto = textoArquivo.getText();
    }

    @FXML
    private void entradaTeclado(KeyEvent event) {
        if(event.getCode().toString().equals("SHIFT")){
            shift = true;
        }else if(event.getCode().toString().equals("CAPS")){
            if(capslook == true){
                capslook = false;
            }else if(capslook == false){
                capslook = true;
            }
        }
        if(event.getCode().toString().equals("BACK_SPACE")){
            controller.editarArquivo("#02;;" +textoArquivo.getCaretPosition(), nomeArquivo);
        }else if(!event.getText().isEmpty()){
            if(shift == true || capslook == true){
                controller.editarArquivo("#01;" + event.getText().toUpperCase() + ";" + textoArquivo.getCaretPosition(), nomeArquivo);
                shift = false;
            }else{
                controller.editarArquivo("#01;" + event.getText() + ";" + textoArquivo.getCaretPosition(), nomeArquivo);
            }
            
        }
    }

    @FXML
    private void liberaçãoTecla(KeyEvent event) {
        // NADA A FAZER 
    }

}
