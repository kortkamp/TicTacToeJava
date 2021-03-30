// Classe que centraliza as entradas e sa�das de mensagens ao jogador.
// Classe implementada para facilitar a migra��o para interface gr�fica.
package game;
import java.util.Scanner;

public class Interface {
	Scanner scanner;
	Jogada jogada = new Jogada();
	
	
	public void initialize() {
		scanner = new Scanner(System.in);		
	}
	public void terminate() {
		scanner.close();
	}
	// Fun��o para sa�da de mensagens ao jogador.
	public void print(String mensagem) {
		System.out.println(mensagem);		
	}
	
	public void printInt(int valor) {
		System.out.print(valor);		
	}
	// Fun��o que retorna as coordenadas da jogada escolhida pelo jogador.
	public Jogada input() {
		
		this.print("Informe a linha da jogada:");
		// Registra na var linha o valor recebido do jogador e subtrai 1 para um sistema de coordenadas mais intuitivo.
		jogada.linha = scanner.nextInt() - 1;
		this.print("Informe a coluna da jogada:");
		// Registra na var coluna o valor recebido do jogador e subtrai 1 para um sistema de coordenadas mais intuitivo.
		jogada.coluna = scanner.nextInt() - 1;
		return(jogada);
	}
	// Fun��o que retorna uma confirma��o do usu�rio, true if S, false if N
	boolean confirm(String text) {
		this.print(text);
		Character option;
		option = scanner.next().charAt(0);
		switch(option) {
		case 's':
			return(true);
		case 'n':
			return(false);
		case 'S':
			return(true);
		case 'N':
			return(false);
		}
		// Caso receba outra letra, chama a fun��o novamente at� que S ou N seja recebido.
		return(this.confirm(text));
	}
	
	// Recebe uma op��o em formato de inteiro.
	public int getOption(int limit) {
		int value;
		do {
			value = scanner.nextInt();
		}while((value <= 0) || (value>limit));
		return(value);
		
	}
	
	
	// Desenha o tabuleiro na tela.
	public void printTable(Tabuleiro table) {
		char[] mark = { 'O',' ','X'};
		for(int i = 0; i < Tabuleiro.BOARD_SIZE; i++ ) {
			for(int j = 0 ; j < Tabuleiro.BOARD_SIZE; j++) {
				// Escreve na tela o item do Array mark equivalente ao conte�do da c�lula + 1.
				System.out.print(" " + mark[table.content[i][j] + 1]);
				// Caso seja a �ltima c�lula n�o denha a barra vertical e sim a quebra de linha.
				if(j == Tabuleiro.BOARD_SIZE-1) System.out.println("");
				else System.out.print(" |");
			}
			// Caso seja a �ltima linha n�o desenha a separa��o das linhas.
			if(i != Tabuleiro.BOARD_SIZE-1) {
				for(int k = 0; k < (Tabuleiro.BOARD_SIZE * 4 - 1);  k++ ) 
					System.out.print("_");
			 	System.out.println("_");
			}
		}
		System.out.println("");
	}
	
}
