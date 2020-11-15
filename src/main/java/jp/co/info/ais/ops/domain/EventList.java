package jp.co.info.ais.ops.domain;

public class EventList {

	private String occurtime;
	private String eventname;
	private String eventid;
	private String eventkind;
	private String customerno;
	private String customer;
	private String status;
	private String mailid;
	private String upoaid;
	private String upoaname;
	private String upoatime;
	private String mailtext;

	public String getOccurtime() {
		return occurtime;
	}
	public void setOccurtime(String occurtime) {
		this.occurtime = occurtime.replaceAll("-", "/");
	}
	public String getEventid() {
		return eventid;
	}
	public void setEventid(String eventid) {
		this.eventid = eventid;
	}
	public String getCustomerno() {
		return customerno;
	}
	public void setCustomerno(String customerno) {
		this.customerno = customerno;
	}
	public String getCustomer() {
		return customer;
	}
	public void setCustomer(String customer) {
		this.customer = customer;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getMailid() {
		return mailid;
	}
	@Override
	public String toString() {
		return "EventList [occurtime=" + occurtime
				+ ", eventname=" + eventname + ", eventid=" + eventid + ", eventkind=" + eventkind
				+ ", customerno=" + customerno
				+ ", customer=" + customer + ", status=" + status + ", mailid=" + mailid + ", upoaid=" + upoaid
				+ ", upoaname=" + upoaname + ", upoatime=" + upoatime + ", mailtext=\" + mailtext + \"]";
	}

	public void setMailid(String mailid) {
		this.mailid = mailid;
	}
	public String getUpoaid() {
		return upoaid;
	}
	public void setUpoaid(String upoaid) {
		this.upoaid = upoaid;
	}
	public String getUpoaname() {
		return upoaname;
	}
	public void setUpoaname(String upoaname) {
		this.upoaname = upoaname;
	}
	public String getUpoatime() {
		return upoatime;
	}
	public void setUpoatime(String upoatime) {
		this.upoatime = upoatime;
	}
	public String getMailtext() {
		return mailtext;
	}
	public void setMailtext(String mailtext) {
		this.mailtext = mailtext;
	}
	public String getEventname() {
		return eventname;
	}
	public void setEventname(String eventname) {
		this.eventname = eventname;
	}
	public String getEventkind() {
		return eventkind;
	}
	public void setEventkind(String eventkind) {
		this.eventkind = eventkind;
	}

}
