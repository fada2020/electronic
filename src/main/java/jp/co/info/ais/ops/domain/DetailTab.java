package jp.co.info.ais.ops.domain;

/**
 * 自家補連絡設定テーブル
 * @author ais-info
 */
public class DetailTab {
	private String customerno;       //お客様番号(半角英数字)
	private String customer;         //施設名称
	private String address;          //住所
	private int sitecd;              //サイトCD
	private String sitename;         //サイトname
	private String jdgsw;            //有効(システム使用判定/0:使用しない 1:使用する)
	private String status;           //現況/0:未使用 1:使用中
	private String endjgdsw;         //終了判定/0:終了判定しない 1:終了判定する
	private String starttime;        //自家補使用開始日時/YYYYMMDDHHMMSS
	private String upoaid;           //最終更新者ID/ログインユーザー（アカウント）
	private String upoaname;         //最終更新者名
	private String upoatime;         //最終更新日時
	private String startcontactno;   //開始連絡先CD
	private String startvoicepath;   //開始電話連絡音声ファイル/音声ファイルの保存パス(ファイル名)
	private int startvoicecycl;      //開始電話連絡リトライ間隔
	private int startvoicecnt;       //開始電話連絡リトライ回数
	private String startsubject;     //開始メールタイトル
	private String startmailtext;    //開始メール本文
	private String endcontactno;     //終了連絡先CD
	private String endvoicepath;     //終了電話連絡音声ファイル/音声ファイルの保存パス(ファイル名)
	private int endvoicecycl;        //終了電話連絡リトライ間隔
	private int endvoicecnt;         //終了電話連絡リトライ回数
	private String endsubject;       //終了メールタイトル
	private String endmailtext;      //終了メール本文
	private String outermailaddr;    //社外展開アドレス/セミコロン(;)区切り
	private String intermailaddr;    //社内展開アドレス/セミコロン(;)区切り
	private String adminuserid;      //管理者ID
	private String adminusername;    //管理者名
	private String adminmailaddress; //管理者メールアドレス
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
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public int getSitecd() {
		return sitecd;
	}
	public void setSitecd(int sitecd) {
		this.sitecd = sitecd;
	}
	public String getSitename() {
		return sitename;
	}
	public void setSitename(String sitename) {
		this.sitename = sitename;
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
	public String getEndjgdsw() {
		return endjgdsw;
	}
	public void setEndjgdsw(String endjgdsw) {
		this.endjgdsw = endjgdsw;
	}
	public String getStarttime() {
		return starttime;
	}
	public void setStarttime(String starttime) {
		this.starttime = starttime;
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
	public String getStartcontactno() {
		return startcontactno;
	}
	public void setStartcontactno(String startcontactno) {
		this.startcontactno = startcontactno;
	}
	public String getStartvoicepath() {
		return startvoicepath;
	}
	public void setStartvoicepath(String startvoicepath) {
		this.startvoicepath = startvoicepath;
	}
	public int getStartvoicecycl() {
		return startvoicecycl;
	}
	public void setStartvoicecycl(int startvoicecycl) {
		this.startvoicecycl = startvoicecycl;
	}
	public int getStartvoicecnt() {
		return startvoicecnt;
	}
	public void setStartvoicecnt(int startvoicecnt) {
		this.startvoicecnt = startvoicecnt;
	}
	public String getStartsubject() {
		return startsubject;
	}
	public void setStartsubject(String startsubject) {
		this.startsubject = startsubject;
	}
	public String getStartmailtext() {
		return startmailtext;
	}
	public void setStartmailtext(String startmailtext) {
		this.startmailtext = startmailtext;
	}
	public String getEndcontactno() {
		return endcontactno;
	}
	public void setEndcontactno(String endcontactno) {
		this.endcontactno = endcontactno;
	}
	public String getEndvoicepath() {
		return endvoicepath;
	}
	public void setEndvoicepath(String endvoicepath) {
		this.endvoicepath = endvoicepath;
	}
	public int getEndvoicecycl() {
		return endvoicecycl;
	}
	public void setEndvoicecycl(int endvoicecycl) {
		this.endvoicecycl = endvoicecycl;
	}
	public int getEndvoicecnt() {
		return endvoicecnt;
	}
	public void setEndvoicecnt(int endvoicecnt) {
		this.endvoicecnt = endvoicecnt;
	}
	public String getEndsubject() {
		return endsubject;
	}
	public void setEndsubject(String endsubject) {
		this.endsubject = endsubject;
	}
	public String getEndmailtext() {
		return endmailtext;
	}
	public void setEndmailtext(String endmailtext) {
		this.endmailtext = endmailtext;
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

	@Override
	public String toString() {
		return "Setting [customerno=" + customerno +
				", customer=" + customer +
				", sitecd="+ sitecd +
				", jdgsw="+ jdgsw +
				", status="+ status +
				", endjgdsw="+ endjgdsw +
				", starttime="+ starttime +
				", upoaid="+ upoaid +
				", upoaname="+ upoaname +
				", upoatime="+ upoatime +
				", startcontactno="+ startcontactno +
				", startvoicepath="+ startvoicepath +
				", startvoicecycl="+ startvoicecycl +
				", startvoicecnt="+ startvoicecnt +
				", startsubject=" + startsubject +
				", startmailtext=" + startmailtext +
				", endcontactno=" + endcontactno +
				", endvoicepath=" + endvoicepath +
				", endvoicecycl=" + endvoicecycl +
				", endvoicecnt=" + endvoicecnt +
				", endsubject=" + endsubject +
				", endmailtext=" + endmailtext +
				", outermailaddr="+ outermailaddr +
				", intermailaddr="+ intermailaddr +
				", adminuserid="+ adminuserid +
				", adminusername="+ adminusername +
				", adminmailaddress="+ adminmailaddress +
				", address=" + address +
				"]";
	}

}