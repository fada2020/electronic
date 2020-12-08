package jp.co.info.ais.ops.domain;

/*
 * Oracle用TBL＿SITE
 */
public class Site {

	private int sitecd;
	private String sitename;
	private String sitenamekana;

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
	public String getSitenamekana() {
		return sitenamekana;
	}
	public void setSitenamekana(String sitenamekana) {
		this.sitenamekana = sitenamekana;
	}
	@Override
	public String toString() {
		return "Site [sitecd=" + sitecd + ", sitename=" + sitename + ", sitenamekana=" + sitenamekana + "]";
	}


}
