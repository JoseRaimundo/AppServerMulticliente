package Cliente;


import Cliente.ParseHost;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author gpds-gpu
 */
public class teste {
    public static void main(String[] args) {
        ParseHost parseHost = new ParseHost("192.168.1.212:6500/index.html");
        parseHost.parse();
    }
    
}
