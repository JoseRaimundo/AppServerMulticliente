package Cliente;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author gpds-gpu
 */
public class UriParse {
    private String protocolo;
    private String host;
    private String port;
    private String file_name;
    private String regex = "^([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
		"([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
		"([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
		"([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\:" +
                "([0-9]{4})\\/" +
                "(\\w*(\\.\\w*))$";
    private Matcher matcher;
    private Pattern pattern;
    private int temp_i = 0;
    
    public UriParse(String protocolo_host){
        this.protocolo = protocolo_host;
        this.host = new String();
        this.file_name = new String();
        this.port = new String();
    }
    
    /*
    Este método quebra a string protocolo em outras 3 strings (ip, porta e arquivo)
    para isso, o método utiliza loops (for), para varrer a string e utilizando os 
    caracteres especiais como limites para cada substring dentro da string protocolo
    */
    
    public void parse(){
        for (int i = 0; i < protocolo.length(); i++) {
            if (protocolo.charAt(i) != ':') {
                host += protocolo.charAt(i);
            }else {
                temp_i = i;
                break;
            }
        }
        
        for (int i = temp_i+1; i < protocolo.length(); i++) {
            if (protocolo.charAt(i) != '/') {
                port += protocolo.charAt(i);
            }else {
                temp_i = i;
                break;
            }
        }
        
        for (int i = temp_i; i < protocolo.length(); i++) {
                file_name += protocolo.charAt(i);
        }
   }
    
    public boolean validate(){
        this.pattern = Pattern.compile(regex);
        this.matcher = pattern.matcher(protocolo);
        return matcher.matches();
    }
    
    public String getHost(){
        return this.host;
    }
    
    public String getPort(){
        return this.port;
    }
    
    public String getFileName(){
        return this.file_name;
    }
}
