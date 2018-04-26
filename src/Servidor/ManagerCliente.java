/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servidor;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author gpds-gpu
 */
public class ManagerCliente extends Thread{
    private Socket socket = null;
    private DataInputStream entrada;
    private DataOutputStream saida;
   
    public ManagerCliente(Socket socket){
        this.socket= socket;       
    }
    
    @Override
    public void run(){
        try {
            this.entrada = new DataInputStream(socket.getInputStream());
            this.saida  = new DataOutputStream(socket.getOutputStream());
            
            String entrada_temp = entrada.readUTF();
            
            File file = new File (entrada_temp);
              
            //verifica se o arquivo existe
            //se existir
            if (file.isFile()) {
                this.saida.writeInt(200);
                //outra saida só para o arquivo
                 byte [] mybytearray  = new byte [(int)file.length()];
                FileInputStream fis = new FileInputStream(file);
                BufferedInputStream bis = new BufferedInputStream(fis);
                bis.read(mybytearray,0,mybytearray.length);
                System.out.println("Enviando arquivo para "+ socket.getInetAddress() + "  ... ");
                saida.write(mybytearray,0,mybytearray.length);
                saida.flush();
                System.out.println("Envio para "+ socket.getInetAddress() + " concluido!");
            }else{
                //se não exitir
                System.out.println("Arquivo solicitado por "+ socket.getInetAddress() + " não encontrado!");
                this.saida.writeInt(400);
            }
            
            entrada.close();
            saida.close();
            socket.close();
            
        } catch (IOException ex) {
            Logger.getLogger(ManagerCliente.class.getName()).log(Level.SEVERE, null, ex);
        }        
    }
}
