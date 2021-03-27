// Classe Computador com maior dificuldade.
package game;

public class ComputadorDificil extends Computador {
	public ComputadorDificil(int playerNumber) {
		super(playerNumber);
	}
	
	int[][] path = new int[][]{
		{0 , 0},
		{2 , 2},
		{2 , 0},
		{0 , 2}
	};
	
	// Segue um caminho predefinido para as jogadas.
	protected Jogada jogadaPath() {
		for(int i = 0 ; i < path.length; i++) {
			jogada.linha = path[i][0];
			jogada.coluna = path[i][1];
			if(table.validate(jogada)) {
				jogada.done = true;
				return(jogada);
			}
		}
		return(jogada);
	}
	
	@Override
	public Jogada getJogada(Tabuleiro table) {
		this.table = table;
		this.jogada.done = false;
		
		jogada = jogadaAtaqueFinal();
		if(!this.jogada.done)
			jogada = jogadaDefesaUrgente();
		if(!this.jogada.done)
			jogada = jogadaPath();
		if(!this.jogada.done)	
			jogada = this.jogadaRandomica();
			
		jogada.player = playerNumber;
		return(jogada);
	}
}
