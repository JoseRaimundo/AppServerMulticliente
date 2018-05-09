/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Teste;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.Socket;

/**
 *
 * @author gpds-gpu
 */
public class TesteServidor {
    public static void main(String[] args) throws FileNotFoundException, IOException {
                File file = new File ("arquivos/arquivo_teste.html");   
                BufferedReader reader = new BufferedReader(new FileReader(file));
                //"compactando" arquivo
                String text = "";
                
                while (reader.ready()) {
                    text += reader.readLine() + "\n"; 
                    System.out.println("entrou aqui");
                }
                    System.out.println(text);


    }
    
}
