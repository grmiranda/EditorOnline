/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 *
 * @author grmir
 */
public class Cliente {
    
    public void conectar(String porta){
        try{
        Registry myRegistry = LocateRegistry.getRegistry(porta, 1099);
        
        
        }catch(Exception e){
           e.printStackTrace();
        }
    }
    
    public boolean logIn(String nome, String senha) {
        
    }
    
}
