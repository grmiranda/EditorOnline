/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Cliente;
import java.util.LinkedList;

/**
 *
 * @author grmir
 */
public class Controller {
    private static Controller instance = new Controller();
    private Cliente cliente;
    
    private Controller(){
        cliente = new Cliente();
    }
    
    public static Controller getInstance(){
        if(instance == null){
            instance = new Controller();
        }
        return instance;
    }
    
    public boolean logIn(String nome, String senha, String ip){
        return cliente.logIn(nome, senha, ip);
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
}
