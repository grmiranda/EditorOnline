/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author murilo
 */
public class ComunicacaoTCP{
    
    private Socket socket;
    private PrintStream saida;
    
    
     /* Comandos para a modificacao. 
    codigos:
        #01 - add (caractere, posicao)
        #02 - del (posicao)
        
    */
    
    public void enviarMensagem(String ip, String mensagem){
        try {
            socket = new Socket(ip, 3033);
            saida = new PrintStream (socket.getOutputStream());
            saida.println(mensagem);
        } catch (IOException ex) {
            Logger.getLogger(ComunicacaoTCP.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    
    }
    
    
    
    
    
    
    
    
    }

    
          
    

