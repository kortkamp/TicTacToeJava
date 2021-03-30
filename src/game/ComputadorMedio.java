// Classe computador com dificuldade m�dia.
// Implemente apenas as vetrifica��es b�sicas.
package game;

public class ComputadorMedio extends Computador {
	public ComputadorMedio(int playerNumber) {
		//this.playerNumber = playerNumber;
		super(playerNumber);
		this.name = "Computador M�dio";
	}
	@Override
	public Jogada getJogada(Tabuleiro table) {
		//System.out.println("Inicio getJogada");
		this.table = table;
		this.jogada.done = false;
		
		jogada = jogadaAtaqueFinal();
		if(!this.jogada.done)
			jogada = jogadaDefesaUrgente();
		if(!this.jogada.done)
			jogada = this.jogadaRandomica();
			
		jogada.player = playerNumber;
		return(jogada);
	}
}