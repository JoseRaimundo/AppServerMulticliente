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
    public static void main(String[] args) throws IOException {
        int porta = 7000;
        ServerSocket server = new ServerSocket(porta);
        while(true){
           System.out.println("Aguardando conexão ... ");
           Socket socket = server.accept();
           System.out.println("Cliente: " + socket.getInetAddress() + " Conectado!");
           new ThreadAuxiliar(socket).start();
        }        
    }
}
