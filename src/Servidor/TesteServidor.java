/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servidor;

import java.io.File;

/**
 *
 * @author gpds-gpu
 */
public class TesteServidor {
    public static void main(String[] args) {
       File file = new File ("test/teste.html"); 
       
        System.out.println(file.isFile());
    }
    
}
