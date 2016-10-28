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

    private HashMap<String, LinkedList<String>> pilhaExecucao; //pilha é um Hash com chave como nome do usuario e sua pilha de execucao
    private HashMap<String, LinkedList<String>> arquivosAbertos;

    //arquivosAbertos contem como chave o nome do arquivo e uma lista de IPs dos usuarios que estão editando
    public MetodoRemotoImplemente() throws RemoteException {
        super();

        arquivosAbertos = new HashMap<>();
        pilhaExecucao = new HashMap<>();

    }

    @Override
    public boolean loginIn(String username, String senha) throws RemoteException {
        File f = new File("registro.txt");
        String linha = "";
        if (f.exists() == true) {
            try {
                Scanner s = new Scanner(f);
                while (s.hasNextLine()) {
                    String dados[] = s.nextLine().split(" ");
                    if (dados[0].equals(username) && dados[1].equals(senha)) {
                        s.close();
                        if (!pilhaExecucao.containsKey(username)) {
                            pilhaExecucao.put(username, new LinkedList<String>());
                        }
                        return true;
                    }
                }
                s.close();
            } catch (FileNotFoundException ex) {
            }
        }

        return false;
    }

    @Override
    public boolean criarArquivo(String nomeArquivo) throws RemoteException {
        File arquivo = new File(nomeArquivo);
        if (!arquivo.exists()) {
            try {
                arquivo.createNewFile();
                return true;
            } catch (IOException ex) {
                Logger.getLogger(MetodoRemotoImplemente.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        return false;
    }

    @Override
    public boolean salvarArquivo(String nomeArquivo, String texto) throws RemoteException {

        if (arquivosAbertos.containsKey(nomeArquivo)) {
            Sistema.SalvarTexto(nomeArquivo, texto);
            return true;

        }
        return false;
    }

    @Override
    public String abrirArquivo(String nomeArquivo, String ip) throws RemoteException {
        String conteudo = "";
        conteudo = Sistema.CarregarTexto("Arquivos" + "/" + nomeArquivo);
        if (arquivosAbertos.containsKey(nomeArquivo)) {
            arquivosAbertos.get(nomeArquivo).add(ip);
            return conteudo;
        } else {
            arquivosAbertos.put(nomeArquivo, new LinkedList<String>());
            arquivosAbertos.get(nomeArquivo).add(ip);
            return conteudo;
        }
    }

    @Override
    public String[] listarArquivos() {
        String[] ListaDeDiretorios = null;
        File f = new File("Arquivos");
        if (f.exists() == true) {

            return f.list();

        } else {
            return null;

        }

    }

    @Override
    public void editarArquivo(String informacao, String usuario, String nomeArquivo) throws RemoteException {
        verificarPosicao(informacao, usuario, nomeArquivo);

    }

    public synchronized void verificarPosicao(String informacao, String usuario, String nomeArquivo) {
        for (String str : pilhaExecucao.keySet()) {
            if(arquivosAbertos.get(nomeArquivo).contains(usuario));
               pilhaExecucao.get(str).add(informacao);
            
        }

    }

    @Override
    public String atualizarArquivo(String usuario) throws RemoteException {
        
        return null;
    }

}
