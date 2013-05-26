package se.chalmers.fonahano.quizwalk.model;

import java.io.Serializable;

import se.chalmers.fonahano.quizwalk.interfaces.LatitudeLongitude;

/**
 * Coordinates.
 */
public class Coordinates implements Serializable, LatitudeLongitude {
	private static final long serialVersionUID = 1L;

	private final double latitude;
	private final double longitude;

	public Coordinates(double latitude, double longitude) {
		this.latitude = latitude;
		this.longitude = longitude;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see se.chalmers.fonahano.quizwalk.model.LatitudeLongitude#getLatitude()
	 */
	@Override
	public double getLatitude() {
		return latitude;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see se.chalmers.fonahano.quizwalk.model.LatitudeLongitude#getLongitude()
	 */
	@Override
	public double getLongitude() {
		return longitude;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Coordinates other = (Coordinates) obj;
		if (Double.doubleToLongBits(latitude) != Double
				.doubleToLongBits(other.latitude))
			return false;
		if (Double.doubleToLongBits(longitude) != Double
				.doubleToLongBits(other.longitude))
			return false;
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(latitude);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(longitude);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Coordinates [getLatitude()=");
		builder.append(getLatitude());
		builder.append(", getLongitude()=");
		builder.append(getLongitude());
		builder.append("]");
		return builder.toString();
	}

}
