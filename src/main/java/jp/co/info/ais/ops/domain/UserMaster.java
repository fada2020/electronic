package jp.co.info.ais.ops.domain;

public class UserMaster {
	private String enable;//権限
	private String loginuser;//アカウント
	private String username;//ユーザー名称
	private String usernamekana;//ユーザーカナ
    //DetailTab追加
	private String mailaddr;   //MAILADDR
	private String pcmailaddr; //PCMAILADDR
	private String mobiletel;  //MOBILETEL
	private String mailgroupname;

	public String getMailgroupname() {
		return mailgroupname;
	}
	public void setMailgroupname(String mailgroupname) {
		this.mailgroupname = mailgroupname;
	}
	public String getEnable() {
		return enable;
	}
	public void setEnable(String enable) {
		this.enable = enable;
	}
	public String getLoginuser() {
		return loginuser;
	}
	public void setLoginuser(String loginuser) {
		this.loginuser = loginuser;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getUsernamekana() {
		return usernamekana;
	}
	public void setUsernamekana(String usernamekana) {
		this.usernamekana = usernamekana;
	}
	public String getMailaddr() {
		return mailaddr;
	}
	public void setMailaddr(String mailaddr) {
		this.mailaddr = mailaddr;
	}
	public String getPcmailaddr() {
		return pcmailaddr;
	}
	public void setPcmailaddr(String pcmailaddr) {
		this.pcmailaddr = pcmailaddr;
	}
	public String getMobiletel() {
		return mobiletel;
	}
	public void setMobiletel(String mobiletel) {
		this.mobiletel = mobiletel;
	}
	@Override
	public String toString() {
		return "UserMaster [enable=" + enable + ", loginuser=" + loginuser + ", username=" + username
				+ ", usernamekana=" + usernamekana + ", mailaddr=" + mailaddr + ", pcmailaddr=" + pcmailaddr
				+ ", mobiletel=" + mobiletel + ", mailgroupname=" + mailgroupname + "]";
	}



}
