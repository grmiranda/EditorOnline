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
    
    public boolean salvarArquivo(String nomeArquivo,String texto)throws RemoteException;

    public String abrirArquivo(String nomeArquivo,  String ip)throws RemoteException;

    public String[] listarArquivos() throws RemoteException;
    
    public void editarArquivo(String modificacao, String usuario, String nomeArquivo) throws RemoteException;
    // [0] comando
    // [1] caractere  se for add
    // [2] posicao
    
    public String atualizarArquivo(String usuario) throws RemoteException;
    
    
    public boolean fecharArquivo(String usuario, String nomeArquivo) throws RemoteException;
}
