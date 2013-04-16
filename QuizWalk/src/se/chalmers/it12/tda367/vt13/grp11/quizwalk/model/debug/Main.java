package se.chalmers.it12.tda367.vt13.grp11.quizwalk.model.debug;

import se.chalmers.it12.tda367.vt13.grp11.quizwalk.model.Image;
import se.chalmers.it12.tda367.vt13.grp11.quizwalk.model.Location;



public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		Location l = new Location(1, 2, "Hej", new Image());
		
		System.out.println(l.getDescription());
		
		
	}

}
