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
        
        String[] dado = informacao.split(";");
        String[] aux;
        LinkedList<String> auxiliar; //uma lista auxiliar para as listas de requisições
        int posicao = Integer.parseInt(dado[2]);
        for (String str : pilhaExecucao.keySet()) {
            if(arquivosAbertos.get(nomeArquivo).contains(str)){
                if(!str.equals(usuario)){ // IF para barrar que o usuario coloque o caracter em sua propria pilha
                
               auxiliar = pilhaExecucao.get(str);
               for(int i = 0; i <  auxiliar.size(); i++){
                   aux = auxiliar.get(i).split(";");
                   int posicaoAuxiliar = Integer.parseInt(aux[2]);
                   if(posicaoAuxiliar <= posicao)
                       posicao++;
               }
               informacao = dado[0]+";"+dado[1]+";"+posicao;
                pilhaExecucao.get(str).add(informacao);
                posicao = Integer.parseInt(dado[2]);//precisa disso, pois modifico o valor de posicao e preciso dele para inserir nos outros
            }
            }
        }

    }
    
    /*
    Metodo que retorna o primeiro elemento de uma pilha
    Se a pilha estiver fazia ele retorna null.
    */
    @Override
    public String atualizarArquivo(String usuario) throws RemoteException {

        if(pilhaExecucao.get(usuario).isEmpty())
            return null;
        
        return pilhaExecucao.get(usuario).removeFirst();
        
    }

    /*
    Na hora que o usuario fecha o arquivo, esse metodo eh chamado e remove o usuario
    da Hash arquivosAberto e pilhaExecucao
    */
    @Override
    public boolean fecharArquivo(String usuario, String nomeArquivo) throws RemoteException {
        
        pilhaExecucao.remove(usuario);
        if(pilhaExecucao.containsKey(usuario) == false && arquivosAbertos.get(nomeArquivo).remove(usuario) == true)
                return true;
        
        
        return false;
    }

}
