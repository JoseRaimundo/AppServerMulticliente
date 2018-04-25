package Cliente;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author gpds-gpu
 */
public class ParseHost {
    private String protoco = "";
    private String ip = "";
    private String porta = "";
    private String arquivo = "";
    private int temp_i = 0;
    
    public ParseHost(String protocolo_host){
        this.protoco = protocolo_host;
    }
    
    /*
    Este método quebra a string protocolo em outras 3 strings (ip, porta e arquivo)
    para isso, o método utiliza loops (for), para varrer a string e utilizando os 
    caracteres especiais como limites para cada substring dentro da string protocolo
    */
    
    public void parse(){
        for (int i = 0; i < protoco.length(); i++) {
            if (protoco.charAt(i) != ':') {
                ip += protoco.charAt(i);
            }else {
                temp_i = i;
                break;
            }
        }
        
        for (int i = temp_i+1; i < protoco.length(); i++) {
            if (protoco.charAt(i) != '/') {
                porta += protoco.charAt(i);
            }else {
                temp_i = i;
                break;
            }
        }
        
        for (int i = temp_i; i < protoco.length(); i++) {
                arquivo += protoco.charAt(i);
        }
   }
    
    public String getIp(){
        return this.ip;
    }
    
    public String getPorta(){
        return this.porta;
    }
    
    public String getArquivo(){
        return this.porta;
    }
}
