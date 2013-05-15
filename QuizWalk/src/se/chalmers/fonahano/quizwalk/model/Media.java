package se.chalmers.fonahano.quizwalk.model;

import java.io.Serializable;

import se.chalmers.fonahano.quizwalk.utils.C.MediaType;

/**
 * Represents a Media. Examples of media types are human readable strings (e.g
 * "Hello!" ) and an image (e.g. a picture of a horse).
 */
public interface Media extends Serializable {

	/**
	 * @return a MediaTypeID, as defined in {@link MediaType}
	 */
	public MediaType getMediaType();

	/**
	 * @return this Media object.
	 */
	public Object getMedia();

}
