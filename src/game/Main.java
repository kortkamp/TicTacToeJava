/* Trabalho de Programa��o Orientada � Objeto
 * 	MARCELO TEIXEIRA CAMPOS
 *	RU: 3398751
 * 
 *  Jogo da Velha em Java
 * 
 * 
 */
package game;
public class Main {
	
	
	public static void main(String[] args)  {
		InterfaceGrafica userInterface = new InterfaceGrafica();
		//InterfaceGrafica interfaceGrafica = new InterfaceGrafica();
		
		userInterface.initialize();
		
		Tabuleiro table = new Tabuleiro();
		
		// Os itens do array player[] podem ser tanto computadores como jogadores em qualquer combina��o.
		Jogador[] player = new Jogador[2];
		player[0] = new Jogador(Tabuleiro.PLAYER1,userInterface);
		//player[0] = new ComputadorDificil(Tabuleiro.PLAYER1);
		player[0].name = "Jogador 1 ";
		
		// Solicita ao usu�rio que informe o n�vel desejado para o computador.
		userInterface.print("Selecione o n�vel do Computador:");
		userInterface.print("1 - F�cil  , 2 - M�dio  ou 3 - Dif�cil.");
		//switch(userInterface.getOption(3)) {
		switch(3) {
			case 1:
				player[1] = new ComputadorFacil(Tabuleiro.PLAYER2);
				break;
			case 2:
				player[1] = new ComputadorMedio(Tabuleiro.PLAYER2);
				break;
			case 3:
				player[1] = new ComputadorDificil(Tabuleiro.PLAYER2);	
		}
		
		
		// �ndice do array player que indica de quem � a vez de jogar.
		int currentPlayer = 0;
				
		// V�ri�vel que indica que queremos jogar uma nova partida.
		boolean newMatch = true;
		
		
		while(newMatch) {
			// Inicializa o tabueiro.
			table.initialize();
			userInterface.print("#### Jogo da Velha ####");
			userInterface.printTable(table);
			
			// Loop para a partida.
			while(table.state == State.RUNNING) {		
				// Recebe uma jogada v�lida do jogador e registra no tabuleiro.
				try{Thread.sleep(300);}catch(InterruptedException e){System.out.println(e);}
				userInterface.print(player[currentPlayer].name);
				while(!table.set(player[currentPlayer].getJogada(table)));
				// Alterna o �ndice currentPlayer entre 0 e 1 ;
				currentPlayer ^= 1;
				// Imprime na tela o tabuleiro atualizado.
				//userInterface.printTable(table);
				userInterface.printTable(table);
			}
			
			// Retorna mensagem informando se houve vencedor.
			if(table.state == State.DRAW)
				userInterface.print("A partida empatou");
			else {
				// Se chegamos at� aqui � porque um dos jogadores venceu.
				currentPlayer ^= 1;
				player[currentPlayer].pontos++;
				userInterface.print(player[currentPlayer].name + " venceu!");
			  
				userInterface.printInt(player[0].pontos);
				userInterface.print(":" + player[0].name);
				
				userInterface.printInt(player[1].pontos);
				userInterface.print(":" + player[1].name);
			}
				
			
			// Verifica se o usu�rio deseja uma nova partida
			
			if(!userInterface.confirm("Deseja Jogar novamente(S ou N)?"))
				newMatch = false;
			
		}
		userInterface.terminate();
	}

}
