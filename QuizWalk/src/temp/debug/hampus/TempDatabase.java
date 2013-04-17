/**
 * 
 */
package temp.debug.hampus;
import java.util.Map;


import se.chalmers.it12.tda367.vt13.grp11.quizwalk.model.connections.IServerDatabase;
import se.chalmers.it12.tda367.vt13.grp11.quizwalk.model.connections.Response;
import static com.google.common.base.Preconditions.checkNotNull;

/**
 * @author Hampus Forsvall, forzvall@gmail.com
 * @param <E>
 * @param <K>
 *
 */
/*public class TempDatabase<K, E> implements IServerDatabase<K, E> { 
	
	private Map<K,E> quizWalkDB;
	private static TempDatabase tD;
	
	public TempDatabase(){}
	
	public static TempDatabase getInstance(){
		if(tD == null) tD = new TempDatabase();
		return tD;
	}
	
	@Override
	public Response addEntry(K key, E entry) {
		quizWalkDB.put(key, entry);
		return new TempDbReceipt(1, "Completed");
	}

	@Override
	public E getEntry(K key) {
		return checkNotNull(quizWalkDB.get(key));
	}

}*/
