package GUI;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Regra{

	public String getRegra() {
		String nome = JOptionPane.showInputDialog("Qual o metodo");
	
		
		nome = nome.toLowerCase();
		return nome;
	}

}
