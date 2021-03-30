// Classe Computador com mínima dificuldade.
// Faz apenas jogadas aleatórias.
package game;

public class ComputadorFacil extends Computador {
	public ComputadorFacil(int playerNumber) {
		//this.playerNumber = playerNumber;
		super(playerNumber);
		this.name = "Computador Fácil";
	}
	@Override
	public Jogada getJogada(Tabuleiro table) {
		//System.out.println("Inicio getJogada");
		this.table = table;
		this.jogada.done = false;
		
		jogada = this.jogadaRandomica();
			
		jogada.player = playerNumber;
		return(jogada);
	}
}
