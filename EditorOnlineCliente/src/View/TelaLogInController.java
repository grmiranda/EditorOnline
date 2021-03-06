
package View;

import Controller.Controller;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 * Responsável por controlar as ações da tela de LogIn
 *
 * @author grmir
 */
public class TelaLogInController implements Initializable {

    @FXML
    private TextField usuarioInput;
    @FXML
    private TextField senhaInput;

    
    private ControladorDeTelas controlador;
    @FXML
    private Button logIn;
    @FXML
    private TextField ipServidorInput;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        controlador = new ControladorDeTelas();

    }

    @FXML
    private void logIn(ActionEvent event) throws IOException {
        if(!ipServidorInput.getText().isEmpty() && !senhaInput.getText().isEmpty() && !usuarioInput.getText().isEmpty()){
            controlador.conectar(ipServidorInput.getText());
            controlador.logIn(usuarioInput.getText(), senhaInput.getText());
        }
    }
    
    

    
    
}
