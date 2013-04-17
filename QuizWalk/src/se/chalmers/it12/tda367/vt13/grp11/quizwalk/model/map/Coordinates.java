package se.chalmers.it12.tda367.vt13.grp11.quizwalk.model.map;

//TODO: Coordinates must better represent a true GPS location.
/**
 * Coordinates.
 */
public class Coordinates {

	private final int latitude;
	private final int longitude;

	public Coordinates(int latitude, int longitude) {

		this.latitude = latitude;
		this.longitude = longitude;
	}

	public int getLatitude() {
		return latitude;
	}

	public int getLongitude() {
		return longitude;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + latitude;
		result = prime * result + longitude;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Coordinates other = (Coordinates) obj;
		if (latitude != other.latitude)
			return false;
		if (longitude != other.longitude)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "latitude: " + latitude + ". longitude: " + longitude;
	}
}
