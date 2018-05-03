package Cliente;


import Cliente.UriParse;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author gpds-gpu
 */
public class TesteCliente {
    public static void main(String[] args) throws IOException, Exception {
        
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
//        System.out.println("IP: "+ uri_parse.getHost()+ "\nPorta: " + Integer.parseInt(uri_parse.getPort()) + 
//                "\nArquivo: " + uri_parse.getFileName());

        int port = Integer.parseInt(uri_parse.getPort());
        String host = uri_parse.getHost(); //obs.: verificar caso não consiga conexão
        String file_name = uri_parse.getFileName();
        
        //Instancia socket com host e porta do servidor
        Socket socket = new Socket(host, port);
        
        DataInputStream entrada = new DataInputStream(socket.getInputStream());
        DataOutputStream saida = new DataOutputStream(socket.getOutputStream());
            
        //Manda arquivo para servidor
        saida.writeUTF(file_name);
                   
        //Servidor retorna status de erro
        if (entrada.readInt() == 400){
            System.out.println("Não foi possível localizar o arquivo.");
        }

        //Status 200
        //exibe no terminal
        
        socket.close();       
    }
    
}
