package temp.debug.tortal;

import java.util.Map;

import com.google.common.base.Optional;

public class Tortal {
	
	public static class Tester{
		
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
		 * @param map the map to set
		 */
		public void setMap(Optional<Map> map) {
			this.map = Optional.absent();
		}
		
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		Tester t = new Tester(Optional.<Map>absent());
		
		t.setMap(Optional.<Map>absent());
		
		Optional<String> o = Optional.of("Hello");
		
		System.out.println(o.toString());
	}

}