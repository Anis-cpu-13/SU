package controler;

import vue.Vue;

public class Controler {
	
	private Vue vue; 
	
	public static void main(String[] args) {

		new Controler(); 
		
	}
	
	
	public Controler() {
		
		vue = new Vue(this); 
		vue.setVisible(true);
	}

}
