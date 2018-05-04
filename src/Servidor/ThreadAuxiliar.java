/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servidor;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author gpds-gpu
 */
public class ThreadAuxiliar extends Thread{
    private Socket socket = null;
    private String pasta_publica = "arquivos";
    private DataInputStream entrada;
    private DataOutputStream saida;
   
    public ThreadAuxiliar(Socket socket){
        this.socket= socket;       
    }
    
    @Override
    public void run(){
        try {
            
            this.entrada = new DataInputStream(socket.getInputStream());
            this.saida  = new DataOutputStream(socket.getOutputStream());
            
            String entrada_temp = entrada.readUTF();
            System.out.println("Procurando arquivo: " + pasta_publica+entrada_temp);
            File file = new File (pasta_publica+entrada_temp);        
                                 
            if (file.exists()) {
                //tempo para estabilizar os pipes dos streams
                this.saida.writeInt(200);
                   
                BufferedReader reader = new BufferedReader(new FileReader(file));
                String text = "";
               
                System.out.println("Enviando arquivo: " + pasta_publica+entrada_temp);
                while (true) {
                    text = reader.readLine(); 
                    if(text == null) break;
                    saida.writeUTF(text);
                }
                System.out.println("Arquivo enviado!");
                
            }else{
                //se não exitir
                System.out.println("Arquivo solicitado por "+ socket.getInetAddress() + " não encontrado!");
                this.saida.writeInt(400);
            }
            
            entrada.close();
            saida.close();
            socket.close();
            
        } catch (IOException ex) {
            System.err.println("Problemas no envio do arquivo, verifique a conexão: " + ex);;           
        }
    }
}
