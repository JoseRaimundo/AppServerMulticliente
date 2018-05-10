package Cliente;

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
public class Cliente {
    public static void main(String[] args) throws IOException, Exception {
        int STATUS_OK=200, STATUS_NOT_FOUND=400;
        
        //Pega a URI
        System.out.println("Informe uma URI (ex: 127.0.0.1:7000/arquivo_teste.html): ");
        Scanner input = new Scanner(System.in);        
        String uri = input.nextLine();
        
        if (uri.equals("") || uri.equals(null)){
           System.err.println("URI incorreta, por favor, digite novamente");
           System.exit(0);
        }
        
        //Valida a URI
        UriParse uri_parse = new UriParse(uri.trim()); //ex "192.168.1.212:6500/index.html" 127.0.0.1:7000/arquivo_teste.html
              
        //URI inválida
        if (uri_parse.validate() == false){
           System.err.println("URI incorreta, por favor, tente novamente");
           System.exit(0);
        }
        
        //URI válida
        uri_parse.parse();
        int port = Integer.parseInt(uri_parse.getPort());
        String host = uri_parse.getHost();
        String file_name = uri_parse.getFileName();
        
        try{
            //Instancia socket com host e porta do servidor
            System.out.println("Tentando conectar com o host " + host + ":" + port);
            Socket socket = new Socket(host, port);

            DataInputStream entrada = new DataInputStream(socket.getInputStream());
            DataOutputStream saida = new DataOutputStream(socket.getOutputStream());

            //Manda nome do arquivo para servidor
            System.out.println("Solicitando o arquivo: " + file_name);
            saida.writeUTF(file_name);

            //Recebe o setatus sobre o arquivo do servidor
            int status = entrada.readInt();           
            
            if (status == STATUS_OK) {
                
                System.out.println("Cod. 200: Arquivo encontrado!\nRecebendo arquivo .. ");
                //tempo para envio completo dos buffers
                String text = entrada.readUTF();
                System.out.println(text); //Imprime contaúdo do arquivo
                
            }else if(status == STATUS_NOT_FOUND){
                System.out.println("Cod. 400: Arquivo não encontrado!");
            }
            socket.close(); 
            entrada.close();
            saida.close();
            
        } catch (IOException ex) {
            System.err.println("Falha na conexão: " + ex);
        }
    }
}
