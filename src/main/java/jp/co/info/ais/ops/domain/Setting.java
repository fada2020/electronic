package jp.co.info.ais.ops.domain;

public class Setting {
	private int rownum;//No
	private String shisetsuno;//お客様番号
	private String shisetsuname;//施設名
	private int sitecd;//サイトID
	private int jdgsw;//有効
	private int jdgstatus;//終了判定
	private int status;//現況
	private String starttime;//使用開始日時
	private String sitename;

	public int getRownum() {
		return rownum;
	}
	public void setRownum(int rownum) {
		this.rownum = rownum;
	}
	public String getShisetsuno() {
		return shisetsuno;
	}
	public void setShisetsuno(String shisetsuno) {
		this.shisetsuno = shisetsuno;
	}
	public String getShisetsuname() {
		return shisetsuname;
	}
	public void setShisetsuname(String shisetsuname) {
		this.shisetsuname = shisetsuname;
	}
	public int getSitecd() {
		return sitecd;
	}
	public void setSitecd(int sitecd) {
		this.sitecd = sitecd;
	}
	public int getJdgsw() {
		return jdgsw;
	}
	public void setJdgsw(int jdgsw) {
		this.jdgsw = jdgsw;
	}
	public int getJdgstatus() {
		return jdgstatus;
	}
	public void setJdgstatus(int jdgstatus) {
		this.jdgstatus = jdgstatus;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getStarttime() {
		return starttime;
	}
	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}
	public String getSitename() {
		return sitename;
	}
	public void setSitename(String sitename) {
		this.sitename = sitename;
	}

	@Override
	public String toString() {
		return "Settei [shisetsuno=" + shisetsuno + ", shisetsuname=" + shisetsuname + ", sitecd=" + sitecd + ", jdgsw="
				+ jdgsw + ", jdgstatus=" + jdgstatus + ", status=" + status + ", starttime=" + starttime + ", sitename="
				+ sitename + "]";
	}

}
