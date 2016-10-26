package Util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Sistema {
    

    /**
     * Metodo que salva os dados de um objeto em um arquivo de texto
     *
     * @param obj objeto a ser salvo no HD
     * @throws FileNotFoundException
     * @throws IOException
     */
    public static void SalvarTexto(String diretorio, String texto){
       File arquivo = new File(diretorio);
       PrintStream saida;
       
        try {
            saida = new PrintStream(arquivo);
            saida.println(texto);
            saida.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Sistema.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    /**
     * Metodos que carrega os dados de um objeto que foram salvos em um arquivo
     * de texto
     *
     * @return o objeto carregado pelo arquivo
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public static String CarregarTexto(String dir){
        String conteudo = "";
        File arquivo = new File(dir);
        Scanner entrada;
        try {
            entrada = new Scanner(arquivo);
              while(entrada.hasNextLine())
                conteudo = conteudo + entrada.nextLine();
        
        return conteudo;
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Sistema.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
      
    }
}
