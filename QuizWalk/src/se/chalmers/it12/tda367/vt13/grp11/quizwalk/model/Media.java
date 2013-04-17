package se.chalmers.it12.tda367.vt13.grp11.quizwalk.model;

/**
 * Represents a media type. Examples of media types are human readable strings
 * (e.g "What's up?" ) or an image (e.g. a picture of a horse). and images.
 */
public interface Media {

	/**
	 * @return a MediaTypeID, as defined in
	 *         {@link se.chalmers.it12.tda367.vt13.grp11.quizwalk.model.utils.Constants.MediaID}
	 */
	public String getMediaID();

	/**
	 * @return this Media object.
	 */
	public Object getMedia();

}
