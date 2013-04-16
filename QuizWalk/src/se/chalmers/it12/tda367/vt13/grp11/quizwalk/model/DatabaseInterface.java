package se.chalmers.it12.tda367.vt13.grp11.quizwalk.model;



public interface DatabaseInterface <K,E> {
	
	public Receipt addEntry(K key, E entry);
	public E getEntry(K key);
	
}
