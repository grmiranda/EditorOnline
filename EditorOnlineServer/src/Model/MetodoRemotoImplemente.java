/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Util.InterfaceMetodoRemoto;
import Util.Sistema;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author murilo
 */
public class MetodoRemotoImplemente extends UnicastRemoteObject implements InterfaceMetodoRemoto {
    
    private HashMap<String,LinkedList<String>> arquivosAbertos;
    
    public MetodoRemotoImplemente()throws RemoteException{
        super();
        arquivosAbertos = new HashMap<>();      
    }

    @Override
    public boolean loginIn(String username, String senha) throws RemoteException {
        File f = new File(File.separator + "registro.txt");
        String linha = "";
        if(f.exists() == true){
            try {
                Scanner s = new Scanner(f);
                while(s.hasNextLine()){
                    String dados[] =  s.nextLine().split(" ");
                    if(dados[0].equals(username) && dados[1].equals(senha)){
                         s.close();
                        return true;
                    }
                }
                s.close();
            } catch (FileNotFoundException ex) {}
        }
        
        return false;
    }

    @Override
    public boolean criarArquivo(String nomeArquivo) throws RemoteException {
        try {
            Sistema.SalvarSistema(new LinkedList<String>(),nomeArquivo);
            return true;
        } catch (IOException ex) {
            Logger.getLogger(MetodoRemotoImplemente.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean salvarArquivo(String nomeArquivo, int posicao, String linha) throws RemoteException {
        
        try {
            LinkedList<String> arquivo = arquivosAbertos.get(nomeArquivo);
            arquivo.add(posicao, linha);
            Sistema.SalvarSistema(arquivo, nomeArquivo);
            return true;
        } catch (IOException ex) {
            Logger.getLogger(MetodoRemotoImplemente.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public LinkedList<String> editarArquivo(String nomeArquivo,String caractere, int linha) throws RemoteException {
        LinkedList<String> arquivo = arquivosAbertos.get(nomeArquivo);
        arquivo.add(linha, arquivo.get(linha) + caractere);
        
        
        
        return null;
       
    }
    
    @Override
    public String[] listarArquivos(){
        String[] ListaDeDiretorios = null;
        File f = new File("Arquivos");
        if(f.exists() != false){
           return null;
           
        }else
             return f.list();
        
    }
    
    
    
    
    
}
