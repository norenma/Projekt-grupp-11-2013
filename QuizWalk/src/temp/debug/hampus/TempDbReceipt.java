package temp.debug.hampus;




public class TempDbReceipt implements Response {

	private int _responseStatus;
	private String _responseMessage;
	
	public TempDbReceipt(int responseStatus, String responseMessage){
		_responseStatus = responseStatus;
		_responseMessage = responseMessage;
	}


	public String getResponseMessage() {
		return _responseMessage;
	}


	public int getResponseStatus() {
		return _responseStatus;
	}


	public Object getObject() {
		// TODO Auto-generated method stub
		return null;
	}
	

}
