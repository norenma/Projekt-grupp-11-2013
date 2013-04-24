package se.chalmers.it12.tda367.vt13.grp11.quizwalk.model.map;

import static com.google.common.base.Preconditions.checkNotNull;
import se.chalmers.it12.tda367.vt13.grp11.quizwalk.model.Challenge;
import se.chalmers.it12.tda367.vt13.grp11.quizwalk.model.Image;

import com.google.common.base.Optional;

/**
 * A Coordinate with some optional description and optional Image. These locations
 * can populate the {@link GameMap} and constitute {@link Challenge}s
 */

//TODO: Class name might(read will) interfere with com.android.location
public class Location extends Coordinates {


	private final String description;
	
	private final Optional<Image> image;


	/**
	 * Create a new Location.
	 * 
	 * @param latitude
	 * @param longitude
	 * @param description is optional.
	 * @param image is optional.
	 */
	public Location(double latitude, double longitude, String description,
			Optional<Image> image) {
		super(latitude, longitude);
		
		this.description = checkNotNull(description);
		this.image = checkNotNull(image);
	}


	/**
	 * @return the description of this Location. May be empty.
	 */
	public String getDescription() {
		return description;
	}


	/**
	 * @return the image
	 */
	public Optional<Image> getImage() {
		return image;
	}



	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Location [getDescription()=");
		builder.append(getDescription());
		builder.append(", getImage()=");
		builder.append(getImage());
		builder.append(", getLatitude()=");
		builder.append(getLatitude());
		builder.append(", getLongitude()=");
		builder.append(getLongitude());
		builder.append("]");
		return builder.toString();
	}

}
