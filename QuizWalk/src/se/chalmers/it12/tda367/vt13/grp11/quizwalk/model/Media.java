package se.chalmers.it12.tda367.vt13.grp11.quizwalk.model;

import se.chalmers.it12.tda367.vt13.grp11.quizwalk.model.utils.Constants.MediaType;

/**
 * Represents a Media. Examples of media types are human readable strings (e.g
 * "Hello!" ) and an image (e.g. a picture of a horse).
 */
public interface Media {

	/**
	 * @return a MediaTypeID, as defined in {@link MediaType}
	 */
	public MediaType getMediaType();

	/**
	 * @return this Media object.
	 */
	public Object getMedia();

}
