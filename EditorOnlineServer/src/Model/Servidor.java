/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Util.InterfaceMetodoRemoto;
import java.rmi.Naming;
import java.rmi.Remote;
import java.rmi.registry.LocateRegistry;

/**
 *
 * @author grmir
 */
public class Servidor {
    
    public Servidor(){
        try{
            System.setProperty("java.rmi.server.hostname", "127.0.0.1");
            LocateRegistry.createRegistry(1099);
            InterfaceMetodoRemoto c = new MetodoRemotoImplemente();
            Naming.bind("EditorOnlineService", (Remote)c);
            
        }catch(Exception e){
            e.printStackTrace();
        }
        
    }
    
    
    public static void main(String[] args){
        new Servidor();
    }
    
    
    
}
