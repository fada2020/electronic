package jp.co.info.ais.ops.domain;

public class Contact {
	private int contactcd;
	private String contactname;
	private String contactphoneno;
	private String contactmailaddress;
	public int getcontactcd() {
		return contactcd;
	}
	public void setcontactcd(int contactcd) {
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
	@Override
	public String toString() {
		return "Contact [contactcd=" + contactcd + ", contactname=" + contactname + ", contactphoneno=" + contactphoneno
				+ ", contactmailaddress=" + contactmailaddress + "]";
	}

}
