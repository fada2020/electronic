package jp.co.info.ais.ops.domain;

public class EventId {
	private String fromDateTime;
	private String toDateTime;
	private String chkStatus0;
	private String chkStatus1;
	private String chkStatus2;
	private String chkKind1;
	private String chkKind2;
	private String chkKind3;
	private String flag;
	public String getFromDateTime() {
		return fromDateTime;
	}
	public void setFromDateTime(String fromDateTime) {
		this.fromDateTime = fromDateTime;
	}
	public String getToDateTime() {
		return toDateTime;
	}
	public void setToDateTime(String toDateTime) {
		this.toDateTime = toDateTime;
	}
	public String getChkStatus0() {
		return chkStatus0;
	}
	public void setChkStatus0(String chkStatus0) {
		this.chkStatus0 = chkStatus0;
	}
	public String getChkStatus1() {
		return chkStatus1;
	}
	public void setChkStatus1(String chkStatus1) {
		this.chkStatus1 = chkStatus1;
	}
	public String getChkStatus2() {
		return chkStatus2;
	}
	public void setChkStatus2(String chkStatus2) {
		this.chkStatus2 = chkStatus2;
	}
	public String getChkKind1() {
		return chkKind1;
	}
	public void setChkKind1(String chkKind1) {
		this.chkKind1 = chkKind1;
	}
	public String getChkKind2() {
		return chkKind2;
	}
	public void setChkKind2(String chkKind2) {
		this.chkKind2 = chkKind2;
	}
	public String getChkKind3() {
		return chkKind3;
	}
	public void setChkKind3(String chkKind3) {
		this.chkKind3 = chkKind3;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	@Override
	public String toString() {
		return "EventId [fromDateTime=" + fromDateTime + ", toDateTime=" + toDateTime + ", chkStatus0=" + chkStatus0
				+ ", chkStatus1=" + chkStatus1 + ", chkStatus2=" + chkStatus2 + ", chkKind1=" + chkKind1 + ", chkKind2="
				+ chkKind2 + ", chkKind3=" + chkKind3 + ", flag=" + flag + "]";
	}


}
