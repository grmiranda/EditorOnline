package Util;

import java.rmi.Remote;

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
    
}
