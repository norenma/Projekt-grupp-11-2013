package temp.debug.tortal;

import se.chalmers.it12.tda367.vt13.grp11.quizwalk.model.Image;
import se.chalmers.it12.tda367.vt13.grp11.quizwalk.model.Reward;
import se.chalmers.it12.tda367.vt13.grp11.quizwalk.model.StringAnswer;

import com.google.common.base.Optional;

public class Tortal {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		System.out.println(Thread.State.class.getEnclosingClass());

		new Thread(new Runnable() {

			@Override
			public void run() {
				System.out.println(Thread.State.class.getEnclosingClass());
			}
		}).start();

		// StringAnswer answer = new StringAnswer("");
		StringAnswer answer2 = new StringAnswer("1");
		StringAnswer answer3 = new StringAnswer("1");
		StringAnswer answer4 = new StringAnswer("2");
		
		Reward r = new Reward(0, "Challenge coolio", Optional.<Image>absent());
		

		System.out.println(r.getImage());
		

		Optional<Integer> s = null;
		
	}

}