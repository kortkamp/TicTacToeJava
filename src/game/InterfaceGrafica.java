package game;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Scanner;



public class InterfaceGrafica extends JFrame {
	
	private velhaJButton[][] button =  new velhaJButton[Tabuleiro.BOARD_SIZE][Tabuleiro.BOARD_SIZE];
	private velhaJButton currentButton = new velhaJButton("");
	
	Thread thread = new Thread();
	boolean clicked = false;
	int clickedButtonLine,clickedButtonCol; 
	
	public InterfaceGrafica() {
        super("Jogo da Velha");
 
        initComponents();
       
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(Tabuleiro.BOARD_SIZE,Tabuleiro.BOARD_SIZE));
         
        pack();
        setSize(500, 500);
        
        setVisible(true);
    }
	
	public void initialize() {
		//scanner = new Scanner(System.in);		
	}
	public void terminate() {
		//scanner.close();
	}
	private void initComponents() {
		for(int i = 0 ;i < Tabuleiro.BOARD_SIZE; i++)
			for(int j = 0 ; j < Tabuleiro.BOARD_SIZE; j++) {
				button[i][j] = new velhaJButton("");
				button[i][j].setFont(new Font("Arial", Font.PLAIN, 70));
				button[i][j].linha = i;
				button[i][j].coluna = j;
				button[i][j].addActionListener(new ActionListener() {
				    @Override
				    public void actionPerformed(ActionEvent evt) {
				    	
				    	
				    	currentButton = (velhaJButton)evt.getSource();
				    	clickedButtonLine = currentButton.linha;
				    	clickedButtonCol = currentButton.coluna;
				    	//System.out.println(clickedButtonLine+ " " + clickedButtonCol);
				    	
				    	//System.out.println(clickedButtonLine+ " " + clickedButtonCol);
				    	clicked = true;
				    	
				    }
				});
				
				this.add(button[i][j]);
			}
	}
	
	// Função que retorna as coordenadas da jogada escolhida pelo jogador.
	public Jogada input() {
			while(clicked != true)
				try{Thread.sleep(200);}catch(InterruptedException e){System.out.println(e);}
			clicked = false;
			//System.out.println(clickedButtonLine+ " " + clickedButtonCol);
			return(new Jogada(clickedButtonLine,clickedButtonCol,0));
	}
	public void print(String mensagem) {
		System.out.println(mensagem);		
	}
	public void printInt(int valor) {
		System.out.print(valor);		
	}
	
	boolean confirm(String text) {
		this.print(text);
		Character option;
		option = 's';
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
		// Caso receba outra letra, chama a função novamente até que S ou N seja recebido.
		return(this.confirm(text));
	}
	
	public void printTable(Tabuleiro table) {
		String[] mark = { "O","","X"};
		
		for(int i = 0; i < Tabuleiro.BOARD_SIZE; i++ ) 
			for(int j = 0 ; j < Tabuleiro.BOARD_SIZE; j++) 
				button[i][j].setText(mark[table.content[i][j]+1]);
			
	}
}
