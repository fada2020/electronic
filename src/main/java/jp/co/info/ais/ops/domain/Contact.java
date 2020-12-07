package jp.co.info.ais.ops.domain;

public class Contact {
	private int rownum;//No
	private int contactcd;//連絡先CD
	private String contactname;//連絡先名称
	private String contactphoneno;//連絡先電話番号
	private String contactmailaddress;//連絡先メールアドレス
	private int startcontactcd;//更新Start時点
	private int endcontactcd;//更新End時点
	private String upoaid;//更新者ID
	private String upoaname;//更新者名前
	private String upoatime;//更新時間
	public int getRownum() {
		return rownum;
	}
	public void setRownum(int rownum) {
		this.rownum = rownum;
	}
	public int getContactcd() {
		return contactcd;
	}
	public void setContactcd(int contactcd) {
		this.contactcd = contactcd;
	}
	public String getContactname() {
		return contactname;
	}
	public void setContactname(String contactname) {
		this.contactname = contactname;
	}
	public String getContactphoneno() {
		return contactphoneno;
	}
	public void setContactphoneno(String contactphoneno) {
		this.contactphoneno = contactphoneno;
	}
	public String getContactmailaddress() {
		return contactmailaddress;
	}
	public void setContactmailaddress(String contactmailaddress) {
		this.contactmailaddress = contactmailaddress;
	}
	public int getStartcontactcd() {
		return startcontactcd;
	}
	public void setStartcontactcd(int startcontactcd) {
		this.startcontactcd = startcontactcd;
	}
	public int getEndcontactcd() {
		return endcontactcd;
	}
	public void setEndcontactcd(int endcontactcd) {
		this.endcontactcd = endcontactcd;
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
		return "Contact [rownum=" + rownum + ", contactcd=" + contactcd + ", contactname=" + contactname
				+ ", contactphoneno=" + contactphoneno + ", contactmailaddress=" + contactmailaddress
				+ ", startcontactcd=" + startcontactcd + ", endcontactcd=" + endcontactcd + ", upoaid=" + upoaid
				+ ", upoaname=" + upoaname + ", upoatime=" + upoatime + "]";
	}




}
