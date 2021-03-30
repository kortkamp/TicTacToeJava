// Classe criada para que as funcções de jogada das classes Computador e Jogador possam retornar 
// apenas uma variável contendo as duas coordenadas.
package game;

public class Jogada {
	
	Jogada(){
		
	}
	Jogada(int linha, int coluna, int player){
		this.linha = linha;
		this.coluna = coluna;
		this.player = player;
		this.done = true;
	}
	// Coordenada de linha da jogada.
	public int linha;
	// Coordenada de coluna da jogada.
	public int coluna;
	// Jogada foi registrada.
	public boolean done;
	// Valor referente ao PLAYER que fez a jogada. 
	public int player;
		
	
}
