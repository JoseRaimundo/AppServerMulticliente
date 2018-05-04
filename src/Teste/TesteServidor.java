/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Teste;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.Socket;

/**
 *
 * @author gpds-gpu
 */
public class TesteServidor {
    public static void main(String[] args) throws FileNotFoundException, IOException {
        Socket socket = new Socket("127.0.0.1", 7000);
        DataInputStream entrada = new DataInputStream(socket.getInputStream());
        DataOutputStream saida = new DataOutputStream(socket.getOutputStream());
        
        
        saida.writeUTF("/arquivo_teste.html");
        int protocolo = entrada.readInt();
        if (protocolo == 200) {
            System.out.println("Entrou no loop .. ");
            while(entrada.available() != 0){
                System.out.println(entrada.readUTF());
            }
        }else if(protocolo == 400){
            System.out.println("Arquivo não encontrado!");
        }else{
            System.out.println("Falha na conexão!");
        }

    }
    
}