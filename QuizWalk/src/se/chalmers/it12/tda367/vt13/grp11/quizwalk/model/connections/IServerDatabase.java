package se.chalmers.it12.tda367.vt13.grp11.quizwalk.model.connections;


/**
 * Represents the clients communication with the QuizWalk Server.
 *
 */
public interface IServerDatabase {


	public <T> Response<T> query(Query query);

}
