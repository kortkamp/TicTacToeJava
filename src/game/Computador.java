/* Classe b�sica para o computador com as func��es e vari�veis basicas para o funcionamento e implementa��o 
 * de outros computadores com n�veis de dificuldade diferentes.
 * Essa Classe extende da classe Jogador para deixar o c�digo o mais gen�rico poss�vel.
 * Assim d� pra chamar a fun��o getJogada() de um array de classes Jogador , onde a gente pode
 * ter qualquer combina��o seja Jogador X Jogador, Computador X Jogador e Computador X Computador.
 *
 *
 */

package game;
import java.util.Random;

public class Computador extends Jogador {
	
	//public int playerNumber;
	
	public Computador(int playerNumber) {
		//this.playerNumber = playerNumber;
		super(playerNumber);
	}
	
	public int pontos;
	
	Tabuleiro table = new Tabuleiro();
	Jogada jogada = new Jogada();
	Random random = new Random();
	
	
	// Jogada de ataque onde o computador pode ganhar com apenas uma jogada.
	//private Jogada jogadaAtaqueFinal() {
			
	//}
	
	protected Jogada jogadaAtaqueFinal() {
		return(this.jogadaFinal(this.playerNumber));
	}
	// Jogada de Defesa mais priorit�ria(oponente pode ganhar em um movimento).
	protected Jogada jogadaDefesaUrgente() {
		return(this.jogadaFinal(this.oponentNumber));
	}
	// Verifica se um dos jogadores pode terminar o jogo com apenas mais uma jogada
	// Basicamente essa fun��o testa todas as linha, coluna e diagonais para ferificar o se o player (argumento)
	// j� preencheu 2 casas (soma == 2) e se ainda falta 1 (prouto ==0).
	// Caso essas condi��es sejam ambas verdadeiras ela retorna uma jogada v�lida com as coordenadas da casa.
	// Juro que tentei faze-la mais intelig�vel, por�m usaria tantas var�veis e if`s que estava ficando horrorosa.
	protected Jogada jogadaFinal(int player) {
		int valor;
		int soma;
		int produto;
		for(int k = 0; k <= 6 ; k+=2) {
			for(int i = 0; i < Tabuleiro.BOARD_SIZE; i++) {
				soma = 0;
				produto = 1;
				for(int j = 0 ; j < Tabuleiro.BOARD_SIZE; j++) {
					valor = table.content[(k==0?1:0)*i + (k==2?1:0)*j + ((k&4)/4)*j][(k==0?1:0)*j + (k==2?1:0)*i + (k==4?1:0)*j + (k==6?1:0)*(Tabuleiro.BOARD_SIZE-j-1)];
					soma += valor;
					produto *= valor;
					if(valor == 0) {
						jogada.linha = (k==0?1:0)*i + (k==2?1:0)*j + ((k&4)/4)*j;
						jogada.coluna = (k==0?1:0)*j + (k==2?1:0)*i + (k==4?1:0)*j + (k==6?1:0)*(Tabuleiro.BOARD_SIZE-j-1);
					}
				}
				if(soma == player*(Tabuleiro.BOARD_SIZE - 1)&&produto == 0) {
					jogada.done = true;
					return(this.jogada);
				}
			}	
		}	
		return(this.jogada);
	}
	
	// Jogada randomica, desde que v�lida.
	// N�o pode ser chamada com o jogo em estado DRAW.
	protected Jogada jogadaRandomica() {
		//System.out.println("Inicio jogadaRandomica");
		do {
			jogada.linha = random.nextInt(Tabuleiro.BOARD_SIZE);
			jogada.coluna = random.nextInt(Tabuleiro.BOARD_SIZE);
		}while(this.table.content[jogada.linha][jogada.coluna] != 0);
		this.jogada.done = true;
		return(jogada);
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
