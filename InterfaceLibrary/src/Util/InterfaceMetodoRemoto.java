package Util;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.LinkedList;

/**
 * Esta interface contem os metodos que deve ser implementados no servidor da aplicação e que podem ser utilizados via RMI
 * A mesma é disponibilizada paras as aplicações Server e Client como uma biblioteca, imporatando o ".jar"
 * 
 * -- OBSERVAÇÃO --
 * 
 *  TODA VEZ QUE ESTA CLASSE SOFRER ALGUMA ALTERAÇÃO, A MESMA DEVE SER CONSTRUIDA NOVAMENTE E SEU EXECUTÁVEL ".jar" DEVE
 *  SER COPIADO PARA A PASTA "Biblioteca" CONTIDA NA PASTA PADRÃO DO SERVIDOR E DO CLIENTE!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
 * 
 * ----------------
 * @author Gabriel Miranda e Murilo Lopes
 */
public interface InterfaceMetodoRemoto extends Remote{
    
    public boolean loginIn(String nome, String senha) throws RemoteException;
    
    public boolean criarArquivo(String nomeFile)throws RemoteException;
    
    public boolean salvarArquivo(String nomeArquivo, int posicao, String linha)throws RemoteException;
    
<<<<<<< HEAD
    public LinkedList<String> editarArquivo(String nomeArquivo, String caractere, int linha)throws RemoteException;
    
=======
    public String abrirArquivo(String nomeArquivo,  String ip)throws RemoteException;

>>>>>>> c61b207fb5a2d56e62ec8a2c4bcb886aab46c1e7
    public String[] listarArquivos() throws RemoteException;
    
    public void editarArquivo(String modificacao) throws RemoteException;
    // [0] comando
    // [1] caractere  se for add
    // [2] posicao
    
    
}
