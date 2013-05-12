package temp.debug.tortal;

import java.util.Map;

import android.telephony.gsm.GsmCellLocation;

import com.google.common.base.Optional;
import com.google.gson.Gson;

public class Tortal {

	public static class Tester {

		private Optional<Map> map;

		public Tester(Optional<Map> map) {
			super();
			this.map = map;
		}

		/**
		 * @return the map
		 */
		public Optional<Map> getMap() {
			return map;
		}

		/**
		 * @param map
		 *            the map to set
		 */
		public void setMap(Optional<Map> map) {
			this.map = Optional.absent();
		}

	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		System.out.println(new Gson().toJson(DebugFactory.getRandomTortalChalmersQuizWalkGame1()));
	}

}