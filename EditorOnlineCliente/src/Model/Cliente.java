/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Controller.Controller;
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
public class Cliente implements Runnable {

    private InterfaceMetodoRemoto remote;
    private Controller controller;
    private String usuario;

    public void conectar(String ip) {
        try {
            Registry myRegistry = LocateRegistry.getRegistry(ip, 1099);
            remote = (InterfaceMetodoRemoto) myRegistry.lookup("EditorOnlineService");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean logIn(String nome, String senha) {
        try {
            controller = Controller.getInstance();
            usuario = nome;
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
            new Thread(this).start();
            return remote.abrirArquivo(nomeArquivo, usuario);
        } catch (RemoteException ex) {
            System.out.println("Erro ao abrir arquivo");
        }
        return null;
    }

    public void atualizarArquivo() {
        try {
            String msg = remote.atualizarArquivo(usuario);
            if (msg != null) {
                controller.atualizarArquivo(msg);
            }
        } catch (RemoteException ex) {
            System.out.println("Erro ao atualizar arquivo");
        }
    }

    @Override
    public void run() {
        atualizarArquivo();
    }

    public void editarArquivo(String msg, String nomeArquivo) {
        try {
            remote.editarArquivo(msg, usuario, nomeArquivo);
        } catch (RemoteException ex) {
            System.out.println("Erro ao editar o arquivo");
        }
    }

    public void fecharAquivo(String nomeArquivo) {
        try {
            remote.fecharArquivo(usuario, nomeArquivo);
        } catch (RemoteException ex) {
            System.out.println("Erro ao fechar o arquivo");
        }
    }
}
