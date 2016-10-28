/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Cliente;
import View.ControladorDeTelas;
import View.TelaInicialController;
import View.TelaLogInController;
import java.util.LinkedList;

/**
 *
 * @author grmir
 */
public class Controller {
    private static Controller instance = new Controller();
    private Cliente cliente;
    private TelaInicialController controladorTela;
    
    private Controller(){
        cliente = new Cliente();
    }
    
    public static Controller getInstance(){
        if(instance == null){
            instance = new Controller();
        }
        return instance;
    }

    public void setControladorTela(TelaInicialController controladorTela) {
        this.controladorTela = controladorTela;
    }
    
    public boolean logIn(String nome, String senha){
        return cliente.logIn(nome, senha);
    }
    
    public void conectar(String ipServidor){
        cliente.conectar(ipServidor);
    }
    
    public String getArquivo(String nomeArquivo){
        return cliente.getArquivo(nomeArquivo);
    }
    
    public String[] getListaArquivos(){
        return cliente.getListaArquivos();
    }

    public void atualizarArquivo(String msg) {
        controladorTela.atualizarArquivo(msg);
    }

    public void editarArquivo(String string) {
        
    }
    
    
}
