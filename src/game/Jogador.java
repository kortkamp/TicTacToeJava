// Classe que recebe a jogada do jogador e retorna as coordenadas da mesma.
package game;

public class Jogador {
	// Número referente o jogador ( 1 ou -1);
	public int playerNumber;
	// Número referente o oponente ( 1 ou -1);
	public int oponentNumber;
	// Quantidade de pontos que o jogador já fez.
	public int pontos = 0;
	String name;
	
	public Jogador(int playerNumber) {
		this.playerNumber = playerNumber;
		oponentNumber = 0 - playerNumber;
	}
	
	Jogada jogada = new Jogada();
	
	// Retorna as coordenadas da jogada escolhida pelo jogador.
	public Jogada getJogada(Tabuleiro table) {
		Interface userInterface = new Interface();
		userInterface.initialize();
		jogada = userInterface.input();
		jogada.player = playerNumber;
		return(jogada);
	}
}
