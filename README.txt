	PARSER LOG QUAKE

Autor: Leonardo Broch de Morais
Contato: leo_broch@hotmail.com

-> Este aplicativo escrito em Java é um parser para o Log gerado por um servidor de Quake III.
-> Ele agrupa as informações em um ranking geral, com informações de todas as partidas disputadas no servidor.
-> Agrupa as informações de cada partida, como os players que disputaram ela, o total de mortes ocorridas durante a 
partida, a pontuação de cada jogador e os tipos de morte que ocorreram.

Como rodar o aplicativo

-> Com o Java devidamente configurado na maquina, abra o prompt de Comando e entre no diretorio onde se encontra o arquivo
Parser-Quake.jar.
-> Rode ele com o comando 'java -jar Parser-Quake.jar
-> Você visualizara a tela de apresentação do aplicativo, em seguida sera requisitado o endereço de onde se encontra o 
arquivo de Log com a extensão .txt
-> Digite o diretorio, por exemplo: "C:\Users\Fulano\Desktop\Parser-Log-Quake\Log.txt".
-> Caso o diretorio esteja correto o aplicativo ira apresentar na tela o relatorio completo de partidas do servidor.

Algoritmo utilizado no Programa

-> Primeiramente foi analizado o Log gerado pelo servidor de Quake, foi constatado então as palavras de cada evento:
	A partida inicia com "InitGame";
		0:00 InitGame: \sv_floodProtect\1\sv_maxPing...
	Cada jogador que entra na partida gera um comando "ClientUserinfoChanged" e um respectivo numero Identificador
que é unico para cada Jogador;
		Ex: 21:51 ClientUserinfoChanged: 3 n\Dono da Bola\t\0\...
	Uma vez um Jogador tenha entrado na partida com o comando "ClientUserinfoChanged" ele é registrado no programa,
sendo reconhecido pelo nome e pelo seu numero identificador;
	O evento de morte de algum Jogador é reconhecido através de "Kill", neste evento obtemos os numeros 
identificadores do player que realizou a kill, do player que foi morto e também do tipo de morte ocorrido;
		22:06 Kill: 2 3 7: Isgalamido killed Mocinha by MOD_ROCKET_SPLASH

-> Logo após foi elaborada a estrutura de como o programa iria salvar as informações, para isso foram criadas as 
seguintes Classes:
	Game: Contém o nome ( game1, game2 ...) e uma Array com os players que participaram daquela partida.
	Player: Contém o ID do player, seu nome e seu PlayerKD.
	PlayerKD: Contém a contagem de kills, mortes e tipos de morte de cada jogador.
	TiposDeMorte: Uma Enum com todos os tipos de morte presentes no jogo e seus ID.

-> Para realizar o Parser do Log nessas informações foram criadas as seguintes Classes: 
	Parser: Contém o metodo main responsável por ler o arquivo Log.txt, separá-lo em uma Array de Strings contendo 
cada linha gerada pelo Log. Esta classe possui também o método printGames, que recebe uma array de Games e apresenta suas
informações na tela para o usuário.
	ParserGame: Recebe como paramêtro as linhas presentes no Log e retorna uma Array com as partidas contidas nesse Log.
	LinhasGame: Separa as linhas contidas no Log por Partida.
	Analyser: Analisa as informações de Registro e Kill, e as organiza em um hash contendo os Players de cada partida. 