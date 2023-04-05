package vue;

import static java.lang.Math.random;

import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import controler.Controler;
import modele.Modele;

public class Vue extends JFrame implements KeyListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private final int colonnes = 9;
	private final int lignes = 9;
	private JTextField[][] grilleVue = new JTextField[this.lignes][this.colonnes];
	private Font font = new Font("Lucida Console", Font.BOLD, 28);
	private Modele model = new Modele(this.lignes, this.colonnes);
	private Controler controler;
	// private Controller controller = new Controller(this.grilleVue);

	/**
	 * 
	 * Methode qui colorier la grille
	 */

	public void colorier() {

		for (int i = 0; i < this.grilleVue.length; i++) {
			for (int j = 0; j < this.grilleVue[i].length; j++) {
				if ((i / 3 == 1 || j / 3 == 1) && !(i / 3 == 1 && j / 3 == 1)) {
					this.grilleVue[i][j].setBackground(Color.lightGray);
				}

			}

		}

	}

	/**
	 * 
	 * methode qui transmet les information du modéle à la grille
	 */

	public void afficheModel() {

		for (int i = 0; i < grilleVue.length; i++) {
			for (int j = 0; j < grilleVue.length; j++) {
				if (model.matrice[i][j] != 0) {
					this.grilleVue[i][j].setText(Integer.toString(model.matrice[i][j]));
					this.grilleVue[i][j].setEditable(false);
				}

			}

		}

	}

	/**
	 * Methode qui remplie quelque cellule à la demande de l'utilisateur
	 *
	 */

	public void aideUtilisateur() {
		int cmpt = 0;
		for (int i = 0; i < grilleVue.length; i++) {
			for (int j = 0; j < grilleVue.length; j++) {
				if (grilleVue[i][j].getText().equals("")) {
					this.grilleVue[i][j].setText(Integer.toString(model.copieMatrice[i][j]));
					this.grilleVue[i][j].setEditable(false);
					cmpt++;

					if (cmpt >= 3)
						return;
				}
			}
		}
	}

	/**
	 * Générer la grille correctemment remplie
	 */

	public void genereGrilleCorrect() {

		for (int i = 0; i < grilleVue.length; i++) {
			for (int j = 0; j < grilleVue.length; j++) {
				if (model.matrice[i][j] == 0) {
					this.grilleVue[i][j].setText(Integer.toString(model.copieMatrice[i][j]));
					this.grilleVue[i][j].setEditable(false);
				}

			}

		}

	}

	/**
	 * 
	 * Colorier une ligne correctemment remplie
	 * 
	 * @param M
	 * @param K
	 * @param x
	 */

	public void validerLigne(int x) {
		boolean valide = true;
		for (int i = 0; i < 9; i++) {

			if (getGrilleVue(x, i) != model.copieMatrice[x][i])
				valide = false;
			// System.out.print(" x " + x +" i " + i );
		}
		if (valide == true) {
			for (int i = 0; i < 9; i++) {
				grilleVue[x][i].setBackground(Color.YELLOW);
				grilleVue[x][i].setEditable(false);
			}

		}
	}

	/**
	 * Colorier une colonne correctemment remplie
	 * @param K
	 * @param L
	 * @param x
	 */
	public void validerColonne(int y) {
		boolean valide = true;
		int i;

		// int x = 0;
		for (i = 0; i < 9; i++) {

			if (getGrilleVue(i, y) != model.copieMatrice[i][y])
				valide = false;
			// System.out.print(" x " + x +" i " + i );
		}
		if (valide == true) {
			for (i = 0; i < 9; i++) {
				grilleVue[i][y].setBackground(Color.YELLOW);
				grilleVue[i][y].setEditable(false);
			}

		}
	}
	
	/**
	 * 
	 * Valider un carre 3*3 et lui changer de couleur
	 * @param controler
	 */
	     public void ValiderCarre3X3(int line,int col)
	     {
	      int l=0;int c =0;boolean valide=true;int ligne, colonne;
	      switch(line){
	          case 0:case 1: case 2:{
	              switch (col){
	                  case 0:case 1: case 2:{ l=0;c=0;break;}
	                     case 3:case 4: case 5: { l=0;c=3;break;}
	                         case 6:case 7: case 8:{ l=0;c=6;break;} 
	              }break;
	          }
	          case 3:case 4: case 5:{
	              switch (col){
	                  case 0:case 1: case 2:{ l=3;c=0;break;}
	                     case 3:case 4: case 5: { l=3;c=3;break;}
	                         case 6:case 7: case 8:{ l=3;c=6;break;} 
	              }break;
	          }
	          case 6:case 7: case 8:{
	        	   switch (col){
	                  case 0:case 1: case 2:{ l=6;c=0;break;}
	                     case 3:case 4: case 5: { l=6;c=3;break;}
	                         case 6:case 7: case 8:{ l=6;c=6;break;} 
	              }break;
	          }
	      } 
	     
	      for (int i = 0; i<9;i++)
	      {
	          ligne=(i/3)+l;
	          colonne = (i%3)+c;
	      	if (getGrilleVue(ligne, colonne) != model.copieMatrice[ligne][colonne])
				valide = false;
	      }
	      
	      if (valide==true){
	          for (int i=0;i<9;i++)
	          {
	           ligne=(i/3)+l;
	           colonne = (i%3)+c;  
	           
	           grilleVue[ligne][colonne].setBackground(Color.orange);
	           grilleVue[ligne][colonne].setEditable(false);
	        }
	     
	    }
	    
	    }
	     
	     public boolean verifierGrille(){
	         for (int i =0;i<9;i++){
	            for (int j =0;j<9;j++){
	               // if(!(grilleVue[i][j].getText().equals(L[i][j].getText()))) return false;
	                if (getGrilleVue(i, j) != model.copieMatrice[i][j])return false; 
	            }  
	         }
	         return true;
	     }
	
	public Vue(Controler controler) {

		this.controler = controler;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(10, 10, 600, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		contentPane.setVisible(true);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		// JMenu mnMenu = new JMenu("Menu");
		// menuBar.add(mnMenu);

		/*
		 * JMenu mnNiveau = new JMenu("Niveau");
		 * 
		 * mnNiveau.addActionListener(new ActionListener() { public void
		 * actionPerformed(ActionEvent e) { } });
		 * 
		 * mnMenu.add(mnNiveau);
		 * 
		 * JMenuItem mntmFacile = new JMenuItem("Facile");
		 * 
		  mntmFacile.addActionListener(new ActionListener() { public void
		  actionPerformed(ActionEvent e) {
		  
		  model.genererFacile(); colorier(); afficheModel();
		  keyPressed1
		  } });
		  
		  
		  mnNiveau.add(mntmFacile);
		  
		  JMenuItem mntmMoyen = new JMenuItem("Moyen");
		  
		  mntmMoyen.addActionListener(new ActionListener() { public void
		  actionPerformed(ActionEvent e) { model.genererMoyen(); colorier();
		  afficheModel();
		  
		  } });
		  
		  mnNiveau.add(mntmMoyen);
		  
		  JMenuItem mntmDificille = new JMenuItem("Dificille");
		  
		  mntmDificille.addActionListener(new ActionListener() { public void
		  actionPerformed(ActionEvent e) { model.genererDifficile(); colorier();
		  afficheModel();
		  
		  } });
		  
		  mnNiveau.add(mntmDificille);*/
		 
		
		

		JButton btnNewButton = new JButton("Aide");

		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				aideUtilisateur();
			}
		});

		menuBar.add(btnNewButton);

		JButton btnNewButton1 = new JButton("Résoudre");

		btnNewButton1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				genereGrilleCorrect();
			}
		});

		menuBar.add(btnNewButton1);
		

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(9, 9, 10, 10));
		for (int i = 0; i < lignes; i++) {
			for (int j = 0; j < colonnes; j++) {
				grilleVue[i][j] = new JTextField();
				grilleVue[i][j].setFont(font);
				grilleVue[i][j].setForeground(Color.black);
				grilleVue[i][j].setHorizontalAlignment(SwingConstants.CENTER);
				grilleVue[i][j].addKeyListener((KeyListener) this);
				contentPane.add(grilleVue[i][j]);

			}
		}

		this.colorier();
		this.afficheModel();
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyReleased(KeyEvent e) {
		int valeurGrilleVue = 0;
		int valeurGrilleCopie = 0;
		JTextField textfield = (JTextField) e.getSource();
		textfield.setFont(new Font("Arial Black", Font.BOLD, 28));
		textfield.setForeground(Color.GREEN);
		String text = textfield.getText();

		if ((!text.equals("1")) && (!text.equals("2")) && (!text.equals("3")) && (!text.equals("4"))
				&& (!text.equals("5")) && (!text.equals("6")) && (!text.equals("7")) && (!text.equals("8"))
				&& (!text.equals("9"))) {
			textfield.setText("");
		}

		/**
		 *  trouver la correspendance i j pour le JTextfield
		 */
		boolean test = false;
		int i, j = 0;
		for (i = 0; i < grilleVue.length; i++) {
			for (j = 0; j < grilleVue[i].length; j++) {
				test = (grilleVue[i][j] == textfield || grilleVue[i][j].equals(textfield));
				if (test)
					break;
			}
			if (test)
				break;
		}

		validerLigne(i);
		validerColonne(j);
		ValiderCarre3X3(i, j);

		valeurGrilleVue = getGrilleVue(i, j);
		valeurGrilleCopie = model.copieMatrice[i][j];
		if (valeurGrilleVue != valeurGrilleCopie) {
			textfield.setForeground(Color.red);

		}
		
		if( verifierGrille())  JOptionPane.showMessageDialog(contentPane,"Bravo!! vous avez gagné "); 

		

	}

	public int getGrilleVue(int i, int j) {
		try {
			return Integer.parseInt(grilleVue[i][j].getText());
		} catch (NumberFormatException nfe) {
			return 0;
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	

}
