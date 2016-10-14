
package View;

import Controller.Controller;
import java.io.File;
import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Gabriel Miranda e Murilo Lopes
 */
public class ControladorDeTelas extends Application{
    private Stage window;
    private Scene tela;
    private Controller controller;
    
    public ControladorDeTelas(){
        controller = Controller.getInstance();
    }
    
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        window = new Stage();
        window.setTitle("Editor Online");
        window.setResizable(false);
        telaLogIn();
    }

    private void telaLogIn() throws IOException {
        // LEMBRAR DE PASSAR O FXML PARA UMA PASTA E MUDAR A REFERENCIA DO MÉTODO NA HORA DE UTILIZAR O .jar!!
        Parent root = (Parent) FXMLLoader.load(new File("src"+ File.separator +"View"+ File.separator +"TelaLogIn.fxml").toURI().toURL());
        // ----------------------------------------------------------------------------------------------------
        tela = new Scene(root);
        window.setScene(tela);
        window.show();
    }
}
