package se.chalmers.it12.tda367.vt13.grp11.quizwalk.model.map;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;
import se.chalmers.it12.tda367.vt13.grp11.quizwalk.model.Challenge;
import se.chalmers.it12.tda367.vt13.grp11.quizwalk.model.Image;

/**
 * A Coordinate with some description and optionally an Image. These locations
 * can populate {@link GameMap}s and linked to {@link Challenge}s
 */
public class Location extends Coordinates {

	private final String description;
	private final Image image;

	public Location(int latitude, int longitude, String description, Image image) {
		super(latitude, longitude);

		this.description = checkNotNull(description);
		checkArgument(!description.isEmpty(), "Description can't be empty");

		this.image = image;
	}

	/**
	 * @return the description of this Location.
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @return the image representing this location. <TT>null</TT> if no such
	 *         image is present.
	 */
	public Image getImage() {
		return image;
	}

}
