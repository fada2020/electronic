package jp.co.info.ais.ops.domain;

public class EventAndId {

	private String eventid;
	private String eventkind;
	private String eventname;
	private String occurtime;
	private String customerno;
	private String customer;
	private String status;
	private String mailid;
	private String upoaid;
	private String upoaname;
	private String upoatime;
	public String getEventid() {
		return eventid;
	}
	public void setEventid(String eventid) {
		this.eventid = eventid;
	}
	public String getEventkind() {
		return eventkind;
	}
	public void setEventkind(String eventkind) {
		this.eventkind = eventkind;
	}
	public String getEventname() {
		return eventname;
	}
	public void setEventname(String eventname) {
		this.eventname = eventname;
	}
	public String getOccurtime() {
		return occurtime;
	}
	public void setOccurtime(String occurtime) {
		this.occurtime = occurtime.replaceAll("-", "/");
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

	@Override
	public String toString() {
		return "EventAndId [eventid=" + eventid + ", eventkind=" + eventkind + ", eventname=" + eventname
				+ ", occurtime=" + occurtime + ", customerno=" + customerno + ", customer=" + customer + ", status="
				+ status + ", mailid=" + mailid + ", upoaid=" + upoaid + ", upoaname=" + upoaname + ", upoatime="
				+ upoatime + "]";
	}

}
