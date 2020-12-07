package jp.co.info.ais.ops.domain;

public class DetailTab {
	private String shisetsuno;//お客様番号
	private String customer;//施設名
	private String address; //住所
	private int sitecd;//サイトID
	private String adminuserid; //管理者ID
	private String adminusername; //管理者名
	private String adminmailaddress; //管理者メールアドレス
	private String outermailaddr; //社外展開アドレス
	private String intermailaddr; //社内展開アドレス
	private String sitename;//サイトname
	private String upoaid;//更新者ID
	private String upoaname;//更新者名前
	private String upoatime;//更新時間

	private String jdgsw;//有効
	private String status;
	private String endjdgsw;



	public String getShisetsuno() {
		return shisetsuno;
	}
	public void setShisetsuno(String shisetsuno) {
		this.shisetsuno = shisetsuno;
	}
	public String getCustomer() {
		return customer;
	}
	public void setCustomer(String customer) {
		this.customer = customer;
	}
	public int getSitecd() {
		return sitecd;
	}
	public void setSitecd(int sitecd) {
		this.sitecd = sitecd;
	}
	public String getJdgsw() {
		return jdgsw;
	}
	public void setJdgsw(String jdgsw) {
		this.jdgsw = jdgsw;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getEndjdgsw() {
		return endjdgsw;
	}
	public void setEndjdgsw(String endjdgsw) {
		this.endjdgsw = endjdgsw;
	}
	public String getAdminuserid() {
		return adminuserid;
	}
	public void setAdminuserid(String adminuserid) {
		this.adminuserid = adminuserid;
	}
	public String getAdminusername() {
		return adminusername;
	}
	public void setAdminusername(String adminusername) {
		this.adminusername = adminusername;
	}
	public String getAdminmailaddress() {
		return adminmailaddress;
	}
	public void setAdminmailaddress(String adminmailaddress) {
		this.adminmailaddress = adminmailaddress;
	}
	public String getOutermailaddr() {
		return outermailaddr;
	}
	public void setOutermailaddr(String outermailaddr) {
		this.outermailaddr = outermailaddr;
	}
	public String getIntermailaddr() {
		return intermailaddr;
	}
	public void setIntermailaddr(String intermailaddr) {
		this.intermailaddr = intermailaddr;
	}
	public String getSitename() {
		return sitename;
	}
	public void setSitename(String sitename) {
		this.sitename = sitename;
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
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}



//	@Override
//	public String toString() {
//		return "Setting [rownum=" + rownum + ", shisetsuno=" + shisetsuno + ", customer=" + customer + ", sitecd="
//				+ sitecd + ", jdgsw=" + jdgsw + ", status=" + status + ", endjdgsw=" + endjdgsw + ", starttime="
//				+ starttime + ", sitename=" + sitename + ", upoaid=" + upoaid + ", upoaname=" + upoaname + ", upoatime="
//				+ upoatime + ", startcontactcd=" + startcontactcd + ", endcontactcd=" + endcontactcd + "]";
//	}

}
