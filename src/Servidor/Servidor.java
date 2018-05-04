/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servidor;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


/**
 *
 * @author gpds-gpu
 */
public class Servidor {
    public static void main(String[] args)  {
        int cont_cliente = 0;
        int porta = 7000;
        String CSI = "\u001B[";
        ServerSocket server = null;
        try {
            //Cria um serversocket (único durante a execução do servidor)
            server = new ServerSocket(porta, 1000);
        } catch (IOException ex) {
           System.err.println("Problema na abertura do servidor: " + ex);
        }
        while(true){
           System.out.println("Aguardando conexão ... ");
           Socket socket;
            try {
                //cria um novo socket para cada cliente a cada tentativa de conexão
                socket = server.accept();
                System.out.print (CSI + "m");
                System.out.print (CSI + "32" + "m");
                System.out.print ("[" + ++cont_cliente + "] Cliente: " + socket.getInetAddress() + " Conectado!");
                //cria uma nova thread para tratar o fluxo de dados de cada cliente
                new ThreadAuxiliar(socket).start();
            } catch (IOException ex) {
                System.err.println("Problema ao conectar um novo cliente: " + ex);
            }
        }        
    }
}
