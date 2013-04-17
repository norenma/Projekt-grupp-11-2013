package temp.debug.hampus;

import se.chalmers.it12.tda367.vt13.grp11.quizwalk.model.connections.Response;



public class TempDbReceipt implements Response {

	private int _responseStatus;
	private String _responseMessage;
	
	public TempDbReceipt(int responseStatus, String responseMessage){
		_responseStatus = responseStatus;
		_responseMessage = responseMessage;
	}


	@Override
	public String getResponseMessage() {
		return _responseMessage;
	}


	@Override
	public int getResponseStatus() {
		return _responseStatus;
	}


	@Override
	public Object getObject() {
		// TODO Auto-generated method stub
		return null;
	}
	

}
