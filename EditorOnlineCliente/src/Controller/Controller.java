/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Cliente;

/**
 *
 * @author grmir
 */
public class Controller {
    private static Controller instance = new Controller();
    private Cliente cliente;
    
    private Controller(){
        instance = new Controller();
        cliente = new Cliente();
    }
    
    public static Controller getInstance(){
        if(instance == null){
            instance = new Controller();
        }
        return instance;
    }
    
    public boolean logIn(String nome, String senha){
        return cliente.logIn(nome, senha);
    }
    
    public void conectar(String ipServidor){
        cliente.conectar(ipServidor);
    }
}
