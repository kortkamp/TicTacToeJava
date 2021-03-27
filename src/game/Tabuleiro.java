package game;

public class Tabuleiro {
	
	public static final int PLAYER1 = 1;
	public static final int PLAYER2 = -1;
	
	int state;
	Interface userInterface = new Interface();
	
	// Tamanho do tabuleiro.
	public static final int BOARD_SIZE = 3;
	
	// Para efeitos de visualização, o primeiro índice se refere às linhas e o segundo às colunas.
	// Os itens do Array content são inicializados com 0 e preenchidos ao longo da partida com valores 1 para o jogador e -1 para o jogador 2.
	public int[][] content = new int[BOARD_SIZE][BOARD_SIZE];
	
	public void initialize() {
		
		for(int i = 0 ; i < BOARD_SIZE; i ++) 
			for(int j = 0 ; j < BOARD_SIZE ; j++) 
				this.content[i][j] = 0;
		
		state = State.RUNNING;
		// Na corrente implementação não é necessário inicializar os valores do tabuleiro
		// pois o Java já iniciliza arrays com o valor 0.
	}
	
	// Função de validação para a entrada do jogador.
	public boolean validate(Jogada jogada) {
		
		//Verifica se as coordenadas estão dentro dos limites do tabuleiro. 
		if( jogada.linha < 0 || jogada.linha >= BOARD_SIZE || jogada.coluna < 0 || jogada.coluna >= BOARD_SIZE) {
			userInterface.print("Jogada inválida");
			return(false);
		}
		// Verifica se a posição já foi preenchida.
		if(this.content[jogada.linha][jogada.coluna] != 0) {
			userInterface.print("Casa já preenchida");
			return(false);
		}
		
		//userInterface.print("jogada válida");
		return(true);
	}
	
	// Registra uma jogada no tabuleiro.
	public boolean set(Jogada jogada) {
		if(!this.validate(jogada))
			return(false);
		
		// Atribui à casa escolhida o valor referente ao número do jogador.
		this.content[jogada.linha][jogada.coluna] = jogada.player;
		
		this.state = testVictory();
		//System.out.println(content[jogada.linha][jogada.coluna]);
		return(true);
	}
	
	// Verifica se algum dos jogadores venceu e retorna o número do mesmo.
	// Possível retornos: RUNNING, DRAW, PLAYER1WIN, PLAYER2WIN.
	public int testVictory() {
		// Soma dos valores das linhas, culunas, diagonal principal e diagonal secundária.
		int linha, coluna,diagonalP,diagonalS;
		diagonalP = 0;
		diagonalS = 0;
		for(int i = 0 ; i < BOARD_SIZE; i ++) {	
			linha = 0;
			coluna = 0;
			for(int j = 0 ; j < BOARD_SIZE ; j++) {
				// Soma as linhas e colunas.
				linha+=content[i][j];
				coluna+=content[j][i];	
			}
			// Soma as diagonais.
			diagonalP+=content[i][i];
			diagonalS+=content[i][BOARD_SIZE-i-1];
			
			// A partir de aqui, verifica se qualquer das somas foi 3 ou -3 e retorna qual jogador venceu, senão retorna 0.
			if(Math.abs(linha) == BOARD_SIZE)
				return(linha/BOARD_SIZE);
			if(Math.abs(coluna) == BOARD_SIZE)
				return(coluna/BOARD_SIZE);
		}
		if(Math.abs(diagonalP) == BOARD_SIZE)
			return(diagonalP/BOARD_SIZE);
		if(Math.abs(diagonalS) == BOARD_SIZE)
			return(diagonalS/BOARD_SIZE);
		
		// Finalmente caso ninguém tenha vencido , testa se a partida estpa empatada.
		if(this.testDraw())
			return(State.DRAW);
		// O retorno de 0 significa que a partida ainda não terminou.
		return(State.RUNNING);
	}	
	// Verifica se houve empate.
	public boolean testDraw() {
		// Se ao menos uma das casas ainda contiver o valor 0, retorna false, ou seja o jogo não está empadado.
		for(int i = 0 ; i < BOARD_SIZE; i ++)
			for(int j = 0 ; j < BOARD_SIZE ; j++)
				if(this.content[i][j] == 0)
					return(false);
		// Todas as casas estão preenchidas e ninguém venceu, então a partida empatou.
		return(true);
	}
}
