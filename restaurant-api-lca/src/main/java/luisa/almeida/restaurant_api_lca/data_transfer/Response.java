package luisa.almeida.restaurant_api_lca.data_transfer;

public abstract class Response {

	private String status;
	private String sentOn;
	private String statusCode;
	private String transactionID;
	private String msg;


	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getSentOn() {
		return sentOn;
	}
	public void setSentOn(String sentOn) {
		this.sentOn = sentOn;
	}
	public String getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}
	public String getTransactionID() {
		return transactionID;
	}
	public void setTransactionID(String transactionID) {
		this.transactionID = transactionID;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}

	


}
