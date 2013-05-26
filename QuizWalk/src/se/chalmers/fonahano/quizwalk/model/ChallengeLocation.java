package se.chalmers.fonahano.quizwalk.model;

import static com.google.common.base.Preconditions.checkNotNull;
import se.chalmers.fonahano.quizwalk.interfaces.Image;
import se.chalmers.fonahano.quizwalk.presentation.StateSingleton;

import com.google.common.base.Optional;

/**
 * A Coordinate with some optional description and optional Image. These
 * locations can populate the {@link StateSingleton} and constitute
 * {@link Challenge}s
 */

public class ChallengeLocation extends Coordinates {
	private static final long serialVersionUID = 1L;

	private final String description;

	private final Optional<Image> image;

	/**
	 * Create a new ChallengeLocation.
	 * 
	 * @param latitude
	 * @param longitude
	 * @param description
	 *            is optional.
	 * @param image
	 *            is optional.
	 */
	public ChallengeLocation(double latitude, double longitude,
			String description, Optional<Image> image) {
		super(latitude, longitude);

		this.description = checkNotNull(description);

		this.image = checkNotNull(image);
	}

	/**
	 * @return the description of this ChallengeLocation. May be empty.
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
		builder.append("ChallengeLocation [getDescription()=");
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((image == null) ? 0 : image.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		ChallengeLocation other = (ChallengeLocation) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (image == null) {
			if (other.image != null)
				return false;
		} else if (!image.equals(other.image))
			return false;
		return true;
	}

}
