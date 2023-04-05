package modele;

import static java.lang.Math.random;

public class Modele {

	public int[][] matrice;
	public int[][] copieMatrice;
	

	public Modele(int lignes, int colonnes) {
		this.matrice = new int[lignes][colonnes];
		this.copieMatrice = new int[lignes][colonnes];
		this.generer();
	}

	/**
	 * Méthode qui sert à initialiser la grille, on commence par initialiser la
	 * premiere ligne de 1 à 9 puis on passe puis passe ligne suivante en décalant a
	 * chaque fois les 3 premiers chiffres ajouter 3 a ligne pécédente, pour acceder
	 * a ligne suivnate on doit commencer de à la celulle n°9 , pour determiner la
	 * ligne suivante on doit diviser par 3 , pour accerder à ligne précédente on
	 * fait la ligne en cours - 1
	 */
	public void initialiser() {
		int ligne;
		int colonne;
		int v;

		for (int i = 0; i < 9; i++) {
			this.matrice[0][i] = i + 1;

		}

		for (int j = 9; j < 81; j++) {
			ligne = j / 9;
			colonne = j % 9;
			if (ligne % 3 == 0)
				v = 1;
			else
				v = 0;
			this.matrice[ligne][colonne] = ((this.matrice[ligne - 1][colonne] + (2 + v)) % 9 + 1);
		}
	}

	/**
	 * 
	 * @param x
	 * @param y
	 * @param i
	 * @return
	 */

	public boolean exisiteEnLigne(int x, int y, int i) {
		if ((this.matrice[x][i] == (this.matrice[y][0])) || (this.matrice[x][i] == (this.matrice[y][1]))
				|| (this.matrice[x][i] == (this.matrice[y][2])))
			return true;
		return false;
	}

	/**
	 * 
	 * @param x
	 * @param y
	 * @param i
	 * @return
	 */

	public boolean exisiteEnColonne(int x, int y, int i) {
		if ((this.matrice[i][x] == (this.matrice[0][y])) || (this.matrice[i][x] == (this.matrice[1][y]))
				|| (this.matrice[i][x] == (this.matrice[2][y])))
			return true;
		return false;
	}

	/**
	 * Méthode qui permet de purmmuter les lignes entre elles en utilisant une
	 * variable auxiaire pour pouvoir faire ça, et on génere la numéro de la ligne1
	 * aléatoiremment entre 0 et 9 avec zero exclue. pour faire la permutation les
	 * deux lignes appartient au meme carré pour ne pas avoir de doublant
	 */

	public void permuterLignes() {
		int auxiliaire;
		int ligne1 = (int) (9.0 * random());
		int ligne2 = (int) (9.0 * random());
		int cmptLigne = 0;

		do {
			// System.out.println("Ligne 1 = " + ligne1 + "ligne 2" + ligne2);
			if ((ligne1 / 3 == ligne2 / 3) && (ligne1 != ligne2) || ((this.exisiteEnLigne(ligne1, ligne2, 0))
					&& ((this.exisiteEnLigne(ligne1, ligne2, 1)) && ((this.exisiteEnLigne(ligne1, ligne2, 2)))))) {
				for (int i = 0; i < 9; i++) {
					auxiliaire = this.matrice[ligne2][i];
					this.matrice[ligne2][i] = this.matrice[ligne1][i];
					this.matrice[ligne1][i] = auxiliaire;
				}
			}
			cmptLigne++;
		} while (cmptLigne <= 10);
	}

	/**
	 * Méthode qui permute les colonnes on procéde de la meme maniére que pour les
	 * lignes en respectant la contrainte que les valeurs doivent étres et unique
	 * pour pas que il y'est de doublant
	 * 
	 */

	public void permuterColonnes() {
		int auxiliaire;
		int colonne1 = (int) (9.0 * random());
		int colonne2 = (int) (9.0 * random());
		int cmptColonne = 0;

		do {
			// System.out.println("Colonne 1 = " + colonne1 + "Colonne 2" + colonne2);
			if ((colonne1 / 3 == colonne2 / 3) && (colonne1 != colonne2)
					|| ((this.exisiteEnColonne(colonne1, colonne2, 0))
							&& ((this.exisiteEnColonne(colonne1, colonne2, 1))
									&& ((this.exisiteEnColonne(colonne1, colonne2, 2)))))) {
				for (int i = 0; i < 9; i++) {
					auxiliaire = this.matrice[i][colonne2];
					this.matrice[i][colonne2] = this.matrice[i][colonne1];
					this.matrice[i][colonne1] = auxiliaire;
				}
			}
			cmptColonne++;
		} while (cmptColonne <= 10);

	}

	/**
	 * Méthode qui permet de copier la matrice générer initialement dans une autre
	 * matrice de meme taille pour la réeutiliser
	 */

	public void matriceCopie(int[][] matrice) {
		for (int i = 0; i < this.matrice.length; i++) {
			for (int j = 0; j < this.matrice[i].length; j++) {

				this.copieMatrice[i][j] = this.matrice[i][j];

			}

		}
	}

	/**
	 * Méthode qui permet d'effacer un nombre de case générer aléatoirement le
	 * nombre en question et différent selon le niveau de difficultée
	 * 
	 */

	public void effacer(int nbCase) {
		int cmpt;
		int col;

		for (int i = 0; i < 9; i++) {
			cmpt = 0;
			int valeurEffacer = (int) (nbCase * random()) + 3;
			while (cmpt <= valeurEffacer) {
				col = (int) (9.0 * random());

				if (!(this.matrice[i][col] == 0)) {
					this.matrice[i][col] = 0;
					cmpt++;
				}
			}
		}

	}

	/**
	 * test
	 * 
	 * @param matrice
	 */

	public void affiche(int[][] m) {

		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				System.out.print(m[i][j] + "  ");
			}

			System.out.println();
		}

	}

	public void afficheStr(String[][] copieMatrice2) {

		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				System.out.print(copieMatrice2[i][j] + "  ");
			}

			System.out.println();
		}

	}

	/**
	 * initialiser la matrice qui va contenir la la copie de matrice à zero avant de
	 * la copier
	 */

	public void initCopie(int[][] matrice) {
		for (int i = 0; i < 9; i++) {

			for (int j = 0; j < 9; j++) {
				matrice[i][j] = 0;
			}
		}
	}

	/**
	 * Méthode qui génére la grille on faisant appel au méthodes précédentes
	 * 
	 */

	public void generer() {
		initialiser();
		permuterLignes();
		permuterColonnes();
		initCopie(copieMatrice);
		matriceCopie(this.matrice);
		stringMatrice(copieMatrice);
		stringMatrice(matrice);
		afficheStr(stringMatrice(copieMatrice));
		effacer(3);

	}

	
	 /* public void genererFacile()  {  effacer(3);}
	  
	  public void genererMoyen()   { effacer(5); }
	 
	  public void genererDifficile() { effacer(7); }*/
	 

	/**
	 * convertire la matrice copie en String
	 */

	public String[][] stringMatrice(int[][] matrice2) {
		String[][] matriceString = new String[9][9];

		for (int i = 0; i < matrice2.length; i++) {
			for (int j = 0; j < matrice2.length; j++) {

				matriceString[i][j] = Integer.toString(matrice2[i][j]);
			

			}
		}

		return matriceString;
	}
	
	

	public int[][] getMatrice() {
		return matrice;
	}

	public void setMatrice(int[][] matrice) {
		this.matrice = matrice;
	}

	public int[][] getCopieMatrice() {
		return copieMatrice;
	}

	public void setCopieMatrice(int[][] copieMatrice) {
		this.copieMatrice = copieMatrice;
	}

}
