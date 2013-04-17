package se.chalmers.it12.tda367.vt13.grp11.quizwalk.model;

/**
 * Represents a media type. Examples of media types are human readable strings
 * and images.
 */
public interface Media {

	/**
	 * @return the MediaTypeID as defined in
	 *         {@link se.chalmers.it12.tda367.vt13.grp11.quizwalk.model.utils.Constants.MediaID}
	 */
	public String getMediaID();

	/**
	 * @return this Media.
	 */
	public Object getMedia();

}
