package temp.debug;

import se.chalmers.it12.tda367.vt13.grp11.quizwalk.model.StringAnswer;

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

		System.out.println(answer2.equals(answer4));

	}

}
