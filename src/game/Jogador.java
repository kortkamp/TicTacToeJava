// Classe que recebe a jogada do jogador e retorna as coordenadas da mesma.
package game;

public class Jogador {
	InterfaceGrafica interfaceGrafica;
	// N?mero referente o jogador ( 1 ou -1);
	public int playerNumber;
	// N?mero referente o oponente ( 1 ou -1);
	public int oponentNumber;
	// Quantidade de pontos que o jogador j? fez.
	public int pontos = 0;
	String name;
	
	public Jogador(int playerNumber,InterfaceGrafica interfaceGrafica  ) {
		this.interfaceGrafica = interfaceGrafica;
		this.playerNumber = playerNumber;
		oponentNumber = 0 - playerNumber;
	}
	public Jogador(int playerNumber  ) {
		this.playerNumber = playerNumber;
		oponentNumber = 0 - playerNumber;
	}
	
	Jogada jogada = new Jogada();
	
	// Retorna as coordenadas da jogada escolhida pelo jogador.
	public Jogada getJogada(Tabuleiro table) {
		
		//userInterface.initialize();
		jogada = interfaceGrafica.input();
		jogada.player = playerNumber;
		return(jogada);
	}
}
