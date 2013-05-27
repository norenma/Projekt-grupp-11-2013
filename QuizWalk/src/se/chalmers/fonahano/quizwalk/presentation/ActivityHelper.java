package se.chalmers.fonahano.quizwalk.presentation;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import se.chalmers.fonahano.quizwalk.R;
import se.chalmers.fonahano.quizwalk.database.GameDatabaseManager;
import se.chalmers.fonahano.quizwalk.interfaces.LatitudeLongitude;
import se.chalmers.fonahano.quizwalk.model.Challenge;
import se.chalmers.fonahano.quizwalk.model.ChallengeLocation;
import se.chalmers.fonahano.quizwalk.model.Coordinates;
import se.chalmers.fonahano.quizwalk.model.QuizWalkGame;
import se.chalmers.fonahano.quizwalk.model.Utilities;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Location;
import android.provider.Settings;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.common.base.Optional;

/**
 * Every GUI or activity related convenience methods should be here!
 * 
 */

public abstract class ActivityHelper {

	/**
	 * Calculates the distance between multiple positions
	 */
	private static void distance(LatitudeLongitude firstPos,
			LatitudeLongitude secondPos, float[] result) {
		Location.distanceBetween(firstPos.getLatitude(),
				firstPos.getLongitude(), secondPos.getLatitude(),
				secondPos.getLongitude(), result);
	}

	/**
	 * Calculates distance between 2 positions
	 * 
	 * @param firstPos
	 * @param secondPos
	 * @return
	 */
	public static float distance(LatitudeLongitude firstPos,
			LatitudeLongitude secondPos) {
		float[] floatArray = new float[1];
		distance(firstPos, secondPos, floatArray);
		return floatArray[0];
	}

	public static List<Float> distance(QuizWalkGame q) {
		List<Float> l = new ArrayList<Float>();
		Iterator<Challenge> it = q.getChallenges().iterator();
		LatitudeLongitude f, s;
		Challenge itNext = it.hasNext() ? it.next() : null;

		while (true) {
			if (it.hasNext()) {
				f = itNext.getLocation();
			} else {
				break;
			}

			if (it.hasNext()) {
				itNext = it.next();
				s = itNext.getLocation();
			} else {
				break;
			}

			l.add(distance(f, s));
		}

		return l;
	}

	/**
	 * 
	 * @param q
	 * @return The total distance between all questions in a quizwalk
	 */
	public static float totalQuizWalkDistance(QuizWalkGame q) {
		Iterator<Float> dist = distance(q).iterator();
		float distance = 0;

		while (dist.hasNext()) {
			distance += dist.next();
		}
		return distance;
	}

	private static Marker populateMap(GoogleMap map, Coordinates coordinates,
			String title) {
		Marker marker = map.addMarker(new MarkerOptions().position(Utilities
				.coordinatesToLatLng(coordinates)));
		marker.setTitle(title);

		return marker;
	}

	/**
	 * Populates a map with {@link Challenge} {@link ChallengeLocation} based on
	 * a {@link QuizWalkGame}w
	 * 
	 * @param m
	 *            Map to be populated
	 * @param q
	 *            QuizWalkGame of which ChallengeLocations will be presented
	 * @return boolean for debugg purposes
	 */
	public static List<Marker> populateMap(GoogleMap map, QuizWalkGame q,
			Context cxt) {
		checkNotNull(q);
		checkNotNull(map);

		List<Challenge> challenges = q.getChallenges();
		List<Marker> markers = new ArrayList<Marker>();

		Iterator<Challenge> itChallenge = challenges.iterator();
		Challenge currentIt;
		// extracts the locations of all the challenges
		int i = 1;
		while (itChallenge.hasNext()) {
			currentIt = itChallenge.next();

			Marker temp = populateMap(map, currentIt.getLocation(),
					currentIt.getChallengeDescription());
			// --- PLEASE READ! -- //
			// Even though it's a terrible solution, there is no updated and
			// working solution to this kind of resource handling in Android

			switch (i) {
			case 1:
				temp.setIcon(BitmapDescriptorFactory
						.fromResource(R.drawable.number_1));
				break;
			case 2:
				temp.setIcon(BitmapDescriptorFactory
						.fromResource(R.drawable.number_2));
				break;
			case 3:
				temp.setIcon(BitmapDescriptorFactory
						.fromResource(R.drawable.number_3));
				break;
			case 4:
				temp.setIcon(BitmapDescriptorFactory
						.fromResource(R.drawable.number_4));
				break;
			case 5:
				temp.setIcon(BitmapDescriptorFactory
						.fromResource(R.drawable.number_5));
				break;
			case 6:
				temp.setIcon(BitmapDescriptorFactory
						.fromResource(R.drawable.number_6));
				break;
			case 7:
				temp.setIcon(BitmapDescriptorFactory
						.fromResource(R.drawable.number_7));
				break;
			case 8:
				temp.setIcon(BitmapDescriptorFactory
						.fromResource(R.drawable.number_8));
				break;
			case 9:
				temp.setIcon(BitmapDescriptorFactory
						.fromResource(R.drawable.number_9));
				break;
			case 10:
				temp.setIcon(BitmapDescriptorFactory
						.fromResource(R.drawable.number_10));
				break;
			case 11:
				temp.setIcon(BitmapDescriptorFactory
						.fromResource(R.drawable.number_11));
				break;
			case 12:
				temp.setIcon(BitmapDescriptorFactory
						.fromResource(R.drawable.number_12));
				break;
			case 13:
				temp.setIcon(BitmapDescriptorFactory
						.fromResource(R.drawable.number_13));
				break;
			case 14:
				temp.setIcon(BitmapDescriptorFactory
						.fromResource(R.drawable.number_14));
				break;
			case 15:
				temp.setIcon(BitmapDescriptorFactory
						.fromResource(R.drawable.number_15));
				break;
			case 16:
				temp.setIcon(BitmapDescriptorFactory
						.fromResource(R.drawable.number_16));
				break;
			case 17:
				temp.setIcon(BitmapDescriptorFactory
						.fromResource(R.drawable.number_17));
				break;
			case 18:
				temp.setIcon(BitmapDescriptorFactory
						.fromResource(R.drawable.number_18));
				break;
			case 19:
				temp.setIcon(BitmapDescriptorFactory
						.fromResource(R.drawable.number_19));
				break;
			case 20:
				temp.setIcon(BitmapDescriptorFactory
						.fromResource(R.drawable.number_20));
				break;
			case 21:
				temp.setIcon(BitmapDescriptorFactory
						.fromResource(R.drawable.number_21));
				break;
			case 22:
				temp.setIcon(BitmapDescriptorFactory
						.fromResource(R.drawable.number_22));
				break;
			case 23:
				temp.setIcon(BitmapDescriptorFactory
						.fromResource(R.drawable.number_23));
				break;
			case 24:
				temp.setIcon(BitmapDescriptorFactory
						.fromResource(R.drawable.number_24));
				break;
			case 25:
				temp.setIcon(BitmapDescriptorFactory
						.fromResource(R.drawable.number_25));
				break;
			case 26:
				temp.setIcon(BitmapDescriptorFactory
						.fromResource(R.drawable.number_26));
				break;
			case 27:
				temp.setIcon(BitmapDescriptorFactory
						.fromResource(R.drawable.number_27));
				break;
			case 28:
				temp.setIcon(BitmapDescriptorFactory
						.fromResource(R.drawable.number_28));
				break;
			case 29:
				temp.setIcon(BitmapDescriptorFactory
						.fromResource(R.drawable.number_29));
				break;
			default:
				temp.setIcon(BitmapDescriptorFactory
						.fromResource(R.drawable.number_0));
				break;
			}

			i++;
			markers.add(temp);

		}

		return markers;
	}

	/**
	 * Populates map with quizWalkLocations
	 * 
	 * @param googleMap
	 * @param quizWalkGames
	 * @return
	 */
	public static List<Marker> populateMap(GoogleMap googleMap,
			List<QuizWalkGame> quizWalkGames, Context cxt) {
		checkNotNull(googleMap);
		Utilities.checkNotNullOrEmpty(quizWalkGames,
				R.string.list_of_quizwalks_empty);
		List<Marker> markers = new ArrayList<Marker>();

		Iterator<QuizWalkGame> qIt = quizWalkGames.iterator();

		while (qIt.hasNext()) {
			QuizWalkGame qItNext = qIt.next();
			Challenge c = checkNotNull(qItNext.getChallenges().get(0));

			Marker temp = populateMap(googleMap, c.getLocation(),
					qItNext.getName());
			temp.setIcon(BitmapDescriptorFactory
					.fromResource(R.drawable.letter_q));
			markers.add(temp);

		}

		return markers;

	}

	public static Optional<QuizWalkGame> getQuizWalkGame(String title) {
		for (QuizWalkGame quiz : GameDatabaseManager.getInstance()
				.getAllQuizWalkGame()) {
			if (quiz.getName().equalsIgnoreCase(title)) {
				return Optional.of(quiz);
			}
		}
		return Optional.<QuizWalkGame> absent();
	}

	/**
	 * Displays a popup prompting the user to enable the gps and then, if the
	 * user choses to, takes the user to the android settings interface
	 */
	public static void showEnableGPSDialog(final Context cxt) {

		AlertDialog.Builder ab = new AlertDialog.Builder(cxt);
		String[] choice = { "Enable GPS", "Exit" };
		ab.setItems(choice, new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface d, int choice) {
				if (choice == 0) {
					Intent intent = new Intent(
							Settings.ACTION_LOCATION_SOURCE_SETTINGS);
					cxt.startActivity(intent);
				}

			}
		});

		ab.setTitle("Your GPS is disabled, it must be enabled to play to game");

		ab.show();
	}

}
