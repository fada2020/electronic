package jp.co.info.ais.ops.domain;

public class Setting {
	public static final String STOP = "0";
	public static final String START = "1";
	private int rownum;				//No
	private String customerno;		//お客様番号
	private String customer;		//施設名
	private int sitecd;				//サイトID
	private String jdgsw;			//有効
	private String status;			//現況
	private String endjdgsw;		//終了判定
	private String starttime;		//使用開始日時
	private String sitename;		//サイトname
	private String upoaid;			//更新者ID
	private String upoaname;		//更新者名前
	private String upoatime;		//更新時間
	private int startcontactcd;		//更新Start時点
	private int endcontactcd;		//更新End時点
	private String eventid;			//イベントID
	private String eventkind;		//イベント区分
	private String eventname;		//イベント名称
	private String comments;		//イベントコメント
	private String eventstatus;		//event現況

	public int getRownum() {
		return rownum;
	}

	public void setRownum(int rownum) {
		this.rownum = rownum;
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

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getEventstatus() {
		return eventstatus;
	}

	public void setEventstatus(String eventstatus) {
		this.eventstatus = eventstatus;
	}

	@Override
	public String toString() {
		return "Setting [rownum=" + rownum + ", customerno=" + customerno + ", customer=" + customer + ", sitecd="
				+ sitecd + ", jdgsw=" + jdgsw + ", status=" + status + ", endjdgsw=" + endjdgsw + ", starttime="
				+ starttime + ", sitename=" + sitename + ", upoaid=" + upoaid + ", upoaname=" + upoaname + ", upoatime="
				+ upoatime + ", startcontactcd=" + startcontactcd + ", endcontactcd=" + endcontactcd + ", eventid="
				+ eventid + ", eventkind=" + eventkind + ", eventname=" + eventname + ", comments=" + comments
				+ ", eventstatus=" + eventstatus + "]";
	}
}
