package GUI;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import br.unb.cic.lp.gol.GameEngine;
import br.unb.cic.lp.gol.Interfaces.IGameController;
import br.unb.cic.lp.gol.Interfaces.IGameEngine;
import br.unb.cic.lp.gol.Interfaces.IStatistics;

public class Menu extends JFrame {
	private JRadioButton automatic, semiautomatic, makecellalive, nextgeneration, halt;
	private JLabel myLabel;
	private ButtonGroup grupo1, grupo2;

	private ButtonHandler handlerButton;
	JPanel radiopanel1;
	JButton cont;
	private JRadioButton chooseestrategia;
	private IGameEngine engine;
	private IGameController controller;
	private IStatistics statistics;
	
	public Menu(IGameEngine engine2, IGameController controller, IStatistics statistics) {
		super("Game of Life");
		this.engine = engine2;
		this.controller = controller;
		this.statistics = statistics;
		setLayout(new FlowLayout());

		handlerButton = new ButtonHandler();

		automatic = new JRadioButton("Automatic", false);
		semiautomatic = new JRadioButton("Semiautomatic", false);
		makecellalive = new JRadioButton("MakeCellAlive", false);

		chooseestrategia = new JRadioButton("ChooseStrategy", false);
		halt = new JRadioButton("Halt", false);
		cont = new JButton("continue");

		radiopanel1 = new JPanel();

		add(automatic);
		add(semiautomatic);
		add(makecellalive);
		add(chooseestrategia);
		add(halt);
		add(cont);

		grupo1 = new ButtonGroup();
		grupo1.add(automatic);
		grupo1.add(semiautomatic);
		grupo1.add(makecellalive);
		grupo1.add(chooseestrategia);
		grupo1.add(halt);

		grupo2 = new ButtonGroup();
		grupo2.add(cont);

		radiopanel1.setLayout(new GridLayout(9, 1));
		radiopanel1.add(automatic);
		radiopanel1.add(semiautomatic);
		radiopanel1.add(makecellalive);

		radiopanel1.add(chooseestrategia);
		radiopanel1.add(halt);
		radiopanel1.add(cont);

		radiopanel1
				.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Selecione uma opcao"));
		setContentPane(radiopanel1);
		pack();

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(450, 200);
		setLocationRelativeTo(null);
		setVisible(true);

		cont.addActionListener(handlerButton);

	}

	private class ButtonHandler implements ActionListener {

		// trata evento de botao
		public void actionPerformed(ActionEvent event) {

			if (event.getSource() == cont && automatic.isSelected()) {
				dispose();
				
				TabuleiroAutomatico tab = new TabuleiroAutomatico(engine, controller);
				
			

			} else if (event.getSource() == cont && semiautomatic.isSelected()) {
				dispose();
				Tabuleiro tab = new Tabuleiro(engine, controller, statistics);

			} else if (event.getSource() == cont && chooseestrategia.isSelected()) {
				engine.chooseEstrategia();

			}

			else if (event.getSource() == cont && makecellalive.isSelected()) {

				MakeAlive name = new MakeAlive(engine, controller);
				dispose();

			} else if (event.getSource() == cont && halt.isSelected()) {
				dispose();

			}

		}
	}
}
