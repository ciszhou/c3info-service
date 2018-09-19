package hello;

public class Card {
	String id;
	String appName;
	String arrivalStatus;
	String arrivalTimestamp;
	String leaveStatus;
	String leaveTimestamp;
	String error;
	
	public Card(String id, String appName, String arrivalStatus, String arrivalTimestamp, String leaveStatus,
			String leaveTimestamp) {
		super();
		this.id = id;
		this.appName = appName;
		this.arrivalStatus = arrivalStatus;
		this.arrivalTimestamp = arrivalTimestamp;
		this.leaveStatus = leaveStatus;
		this.leaveTimestamp = leaveTimestamp;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getAppName() {
		return appName;
	}
	public void setAppName(String appName) {
		this.appName = appName;
	}
	public String getArrivalStatus() {
		return arrivalStatus;
	}
	public void setArrivalStatus(String arrivalStatus) {
		this.arrivalStatus = arrivalStatus;
	}
	public String getArrivalTimestamp() {
		return arrivalTimestamp;
	}
	public void setArrivalTimestamp(String arrivalTimestamp) {
		this.arrivalTimestamp = arrivalTimestamp;
	}
	public String getLeaveStatus() {
		return leaveStatus;
	}
	public void setLeaveStatus(String leaveStatus) {
		this.leaveStatus = leaveStatus;
	}
	public String getLeaveTimestamp() {
		return leaveTimestamp;
	}
	public void setLeaveTimestamp(String leaveTimestamp) {
		this.leaveTimestamp = leaveTimestamp;
	}
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}

}
