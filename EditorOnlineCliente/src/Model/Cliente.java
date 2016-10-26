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
    String ip;

    public void conectar(String ip) {
        try {
            Registry myRegistry = LocateRegistry.getRegistry(ip, 1099);
            remote = (InterfaceMetodoRemoto) myRegistry.lookup("EditorOnlineService");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean logIn(String nome, String senha, String ip) {
        try {
            this.ip = ip;
            return remote.loginIn(nome, senha);
            
        } catch (RemoteException ex) {
            ex.printStackTrace();
            System.out.println("Erro ao invocar metodo Remoto");
            return false;
        }
    }

    public String[] getListaArquivos() {
        try {
            return remote.listarArquivos();
        } catch (RemoteException ex) {
            System.out.println("Erro ao Receber lista de Arquivos");
        }
        return null;
    }

    public String getArquivo(String nomeArquivo) {
        try {
            return remote.abrirArquivo(nomeArquivo, ip);
        } catch (RemoteException ex) {
            System.out.println("Erro ao abrir arquivo");
        }
        return null;
    }
}
