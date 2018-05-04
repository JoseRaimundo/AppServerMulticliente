


# Projeto de Programação Distribuída
## Descrição do repositório
Primeira entrega da projeto da disciplina de programação distribuída do curso de Sistemas para Internet.

### Tecnologias
- [Java SE 8](http://www.oracle.com/technetwork/pt/java/javase/downloads/index.html) - Máquina virtual e ferramentas para execução de aplicações desenvolvidas com linguagem Java. 
- [Netbeans 8](http://www.oracle.com/technetwork/pt/java/javase/downloads/jdk-netbeans-jsp-3413153-ptb.html) - Ambiente de desenvolvimento Java. 


## Descrição do projeto 

A aplicação Servidor:
Implemente um servidor de páginas web estáticas, utilizando sockets TCP/IP. O servidor conterá, em apenas uma pasta pública, arquivos do tipo mime text/html e text/plain. Este servidor deverá retornar o conteúdo dos arquivos procurados por aplicações clientes, que as requisitarem. Para isso, um protocolo de mensagem deverá ser implementado. Se o arquivo existir no servidor, este deverá enviar uma mensagem de status 200 e, em seguida, transferir o conteúdo do arquivo para o cliente. Caso contrário, o servidor retornará o status 400, o qual define que o arquivo não foi encontrado no servidor.
O servidor deverá funcionar de forma a atender as requisições de multiusuários, simultaneamente.


A aplicação Cliente: 
Desenvolva uma aplicação cliente, para o servidor descrito acima, que permita, através de um terminal shell, realizar a busca de um arquivo através de uma URI, obedecendo a seguinte sintaxe:

		[host]:[port]/[file_name]

Onde:
 - host : host do servidor
 - port : porta a qual o servidor web encontra-se em execução
 - file_name : nome do arquivo procurado

Exemplo de uma requisição possível: 
	
		192.168.1.212:6500/index.html.

Na aplicação clinte deverão ser tratadas questões como:
- Extrair da URI as informações para conexão com o servidor; 
- Informar uma mensagem ao usuário, caso não consiga conexão com o servidor;
- Se o servidor retornou o status 200, exibir o conteúdo do arquivo buscado no próprio terminal. Caso contrário, o status seja 400, informe ao usuário uma mensagem de arquivo não encontrado no servidor.




## Observações:
Equipes com no máximo DOIS integrantes;
Entrega e Defesa: 08/05/2018

## Abordagem utilizada


![Cliente Servidor](https://github.com/JoseRaimundo/AppServerMulticliente/blob/master/arquivos/modelo_client_servidor.png)

O Socket no cliente tenta a conexão com o ServerSocket que está em um loop. Ao aceitar a conexão, o ServerSoket cria um socket (que representa a comunicação com o cliente) e passa como parâmetro para uma nova thread (Thread Auxiliar), essa thread é responsável por manipular o socket do cliente durante todo o restante da comunicação com este cliente. Enquanto isso, o ServerSocket continua a executar em loop, retornando a um estado de espera por novas conexões.
