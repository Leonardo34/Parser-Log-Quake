	PARSER LOG QUAKE

Autor: Leonardo Broch de Morais
Contato: leo_broch@hotmail.com

-> Este aplicativo escrito em Java � um parser para o Log gerado por um servidor de Quake III.
-> Ele agrupa as informa��es em um ranking geral, com informa��es de todas as partidas disputadas no servidor.
-> Agrupa as informa��es de cada partida, como os players que disputaram ela, o total de mortes ocorridas durante a 
partida, a pontua��o de cada jogador e os tipos de morte que ocorreram.

Como rodar o aplicativo

-> Com o Java devidamente configurado na maquina, abra o prompt de Comando e entre no diretorio onde se encontra o arquivo
Parser-Quake.jar.
-> Rode ele com o comando 'java -jar Parser-Quake.jar
-> Voc� visualizara a tela de apresenta��o do aplicativo, em seguida sera requisitado o endere�o de onde se encontra o 
arquivo de Log com a extens�o .txt
-> Digite o diretorio, por exemplo: "C:\Users\Fulano\Desktop\Parser-Log-Quake\Log.txt".
-> Caso o diretorio esteja correto o aplicativo ira apresentar na tela o relatorio completo de partidas do servidor.

Algoritmo utilizado no Programa

-> Primeiramente foi analizado o Log gerado pelo servidor de Quake, foi constatado ent�o as palavras de cada evento:
	A partida inicia com "InitGame";
		0:00 InitGame: \sv_floodProtect\1\sv_maxPing...
	Cada jogador que entra na partida gera um comando "ClientUserinfoChanged" e um respectivo numero Identificador
que � unico para cada Jogador;
		Ex: 21:51 ClientUserinfoChanged: 3 n\Dono da Bola\t\0\...
	Uma vez um Jogador tenha entrado na partida com o comando "ClientUserinfoChanged" ele � registrado no programa,
sendo reconhecido pelo nome e pelo seu numero identificador;
	O evento de morte de algum Jogador � reconhecido atrav�s de "Kill", neste evento obtemos os numeros 
identificadores do player que realizou a kill, do player que foi morto e tamb�m do tipo de morte ocorrido;
		22:06 Kill: 2 3 7: Isgalamido killed Mocinha by MOD_ROCKET_SPLASH

-> Logo ap�s foi elaborada a estrutura de como o programa iria salvar as informa��es, para isso foram criadas as 
seguintes Classes:
	Game: Cont�m o nome ( game1, game2 ...) e uma Array com os players que participaram daquela partida.
	Player: Cont�m o ID do player, seu nome e seu PlayerKD.
	PlayerKD: Cont�m a contagem de kills, mortes e tipos de morte de cada jogador.
	TiposDeMorte: Uma Enum com todos os tipos de morte presentes no jogo e seus ID.

-> Para realizar o Parser do Log nessas informa��es foram criadas as seguintes Classes: 
	Parser: Cont�m o metodo main respons�vel por ler o arquivo Log.txt, separ�-lo em uma Array de Strings contendo 
cada linha gerada pelo Log. Esta classe possui tamb�m o m�todo printGames, que recebe uma array de Games e apresenta suas
informa��es na tela para o usu�rio.
	ParserGame: Recebe como param�tro as linhas presentes no Log e retorna uma Array com as partidas contidas nesse Log.
	LinhasGame: Separa as linhas contidas no Log por Partida.
	Analyser: Analisa as informa��es de Registro e Kill, e as organiza em um hash contendo os Players de cada partida. 