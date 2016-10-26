/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Util.InterfaceMetodoRemoto;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author grmir
 */
public class Cliente {
    
    InterfaceMetodoRemoto remote;
    
    public void conectar(String porta){
        try{
        Registry myRegistry = LocateRegistry.getRegistry(porta, 1099);
        remote = (InterfaceMetodoRemoto) myRegistry.lookup("EditorOnlineService");
        
        }catch(Exception e){
           e.printStackTrace();
        }
    }
    
    public boolean logIn(String nome, String senha) {
        try {
            return remote.loginIn(nome, senha);
        } catch (RemoteException ex) {
            System.out.println("Erro ao invocar metodo Remoto");
            return false;
        }
    }

    public String[] getListaArquivos() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public LinkedList<String> getArquivo(String nomeArquivo){
    
    }
}
