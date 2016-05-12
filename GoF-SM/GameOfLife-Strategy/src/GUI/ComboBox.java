package GUI;

import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Toolkit;

import javax.swing.JFrame;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;

import br.unb.cic.lp.gol.Interfaces.IGameController;
import br.unb.cic.lp.gol.Interfaces.IGameEngine;

public class ComboBox extends JFrame {
	private IGameEngine engine;
	private IGameController controller;

	public ComboBox(final IGameEngine engine, final IGameController controller) {

		super("Game of Life");
		this.engine = engine;
		this.controller = controller;
		setLayout(new FlowLayout());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel panel1 = new JPanel();

		// Combobox
		String[] colunas = { "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24",
				"25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40" };
		String[] linhas = { "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24",
				"25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40" };
		final JComboBox comboBox1 = new JComboBox(colunas);
		final JComboBox comboBox2 = new JComboBox(linhas);

		panel1.add(comboBox1);
		panel1.add(comboBox2);

		panel1.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Colunas | Linhas"));
		add(panel1);

		// Button
		JButton btn = new JButton("Validar");
		btn.addActionListener(new ActionListener() {
			private int num;

			public void actionPerformed(ActionEvent arg0) {
				String tipo1 = (String) comboBox1.getSelectedItem();
				String tipo2 = (String) comboBox2.getSelectedItem();
				int i = Integer.parseInt(tipo1);
				int j = Integer.parseInt(tipo2);
				
				engine.setHeight(i);
				engine.setWidth(j);
				
				MakeAlive.LINHAS = i;
				MakeAlive.COLUNAS = j;
				Tabuleiro.LINHAS = i;
				Tabuleiro.COLUNAS = j;
				TabuleiroAutomatico.LINHAS = i;
				TabuleiroAutomatico.COLUNAS = j;
				
				dispose();

				Menu menu = new Menu(engine, controller);
			}
		});

		getContentPane().add(btn);
		setSize(450, 100);
		setLocationRelativeTo(null);
		setVisible(true);

	}

}
