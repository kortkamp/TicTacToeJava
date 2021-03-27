package game;
public class Main {
	
	
	public static void main(String[] args)  {
		Interface userInterface = new Interface();
		userInterface.initialize();
		
		Tabuleiro table = new Tabuleiro();
		
		// Os itens do array player[] podem ser tanto computadores como jogadores em qualquer combinação.
		Jogador[] player = new Jogador[2];
		player[0] = new Jogador(Tabuleiro.PLAYER1);
		player[0].name = "Jogador 1 ";
		
		// Solicita ao usuário que informe o nível desejado para o computador.
		userInterface.print("Selecione o nível do Computador:");
		userInterface.print("1 - Fácil  , 2 - Médio  ou 3 - Difícil.");
		switch(userInterface.getOption(3)) {
			case 1:
				player[1] = new ComputadorFacil(Tabuleiro.PLAYER2);
				player[1].name = "Computador Fácil";
				break;
			case 2:
				player[1] = new ComputadorMedio(Tabuleiro.PLAYER2);
				player[1].name = "Computador Médio";
				break;
			case 3:
				player[1] = new ComputadorDificil(Tabuleiro.PLAYER2);
				player[1].name = "Computador Dificil";		
		}
		
		
		// Índice do array player que indica de quem é a vez de jogar.
		int currentPlayer = 0;
				
		// Váriável que indica que queremos jogar uma nova partida.
		boolean newMatch = true;
		
		
		while(newMatch) {
			// Inicializa o tabueiro.
			table.initialize();
			userInterface.print("#### Jogo da Velha ####");
			userInterface.printTable(table);
			
			// Loop para a partida.
			while(table.state == State.RUNNING) {		
				// Recebe uma jogada válida do jogador e registra no tabuleiro.
				userInterface.print(player[currentPlayer].name);
				while(!table.set(player[currentPlayer].getJogada(table)));
				// Alterna o índice currentPlayer entre 0 e 1 ;
				currentPlayer ^= 1;
				// Imprime na tela o tabuleiro atualizado.
				userInterface.printTable(table);
			}
			
			// Retorna mensagem informando se houve vencedor.
			if(table.state == State.DRAW)
				userInterface.print("A partida empatou");
			else {
				// Se chegamos até aqui é porque um dos jogadores venceu.
				currentPlayer ^= 1;
				player[currentPlayer].pontos++;
				userInterface.print(player[currentPlayer].name + " venceu!");
			  
				userInterface.printInt(player[0].pontos);
				userInterface.print(":" + player[0].name);
				
				userInterface.printInt(player[1].pontos);
				userInterface.print(":" + player[1].name);
			}
				
			
			// Verifica se o usuário deseja uma nova partida
			userInterface.print("Deseja Jogar novamente(S ou N)?");
			if(!userInterface.confirm())
				newMatch = false;
			
		}
		userInterface.terminate();
	}

}
