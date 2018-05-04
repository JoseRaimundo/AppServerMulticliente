package Cliente;


import Cliente.UriParse;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author gpds-gpu
 */
public class Cliente {
    public static void main(String[] args) throws IOException, Exception {
        int STATUS_OK=200, STATUS_ERRO=400;
        
        //Pega a URI
        Scanner input = new Scanner(System.in);        
        String uri = input.nextLine();
        
        //Valida a URI
        UriParse uri_parse = new UriParse(uri); //ex "192.168.1.212:6500/index.html"
        System.out.println(uri_parse.validate());
        
        //URI inválida
        if (uri_parse.validate() == false){
           throw new Exception ("URI incorreta, por favor, digite novamente");
        }
        
        //URI válida
        uri_parse.parse();

        int port = Integer.parseInt(uri_parse.getPort());
        String host = uri_parse.getHost();
        String file_name = uri_parse.getFileName();
        
        try{
            //Instancia socket com host e porta do servidor
            Socket socket = new Socket(host, port);

            DataInputStream entrada = new DataInputStream(socket.getInputStream());
            DataOutputStream saida = new DataOutputStream(socket.getOutputStream());

            //Manda nome do arquivo para servidor
            saida.writeUTF(file_name);

            //Recebe arquivo do servidor
            int status = entrada.readInt();           
            
            if (status == STATUS_OK) {
                System.out.println("Entrou no loop .. ");
                while(entrada.available() != 0){
                    System.out.println(entrada.readUTF()); //Imprime contaúdo do arquivo
                }
            }else if(status == STATUS_ERRO){
                System.out.println("Arquivo não encontrado!");
            }else{
                System.out.println("Falha na conexão!");
            }

            socket.close();   
            
        } catch (IOException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
