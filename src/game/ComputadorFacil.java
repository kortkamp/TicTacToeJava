// Classe Computador com m�nima dificuldade.
// Faz apenas jogadas aleat�rias.
package game;

public class ComputadorFacil extends Computador {
	public ComputadorFacil(int playerNumber) {
		//this.playerNumber = playerNumber;
		super(playerNumber);
		this.name = "Computador F�cil";
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
