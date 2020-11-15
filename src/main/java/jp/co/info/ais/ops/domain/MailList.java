package jp.co.info.ais.ops.domain;

public class MailList {

	private String mailid;
	private String mailkind;
	private String maildatetime;
	private String mailsubject;
	private String mailfrom;
	public String getMailid() {
		return mailid;
	}
	public void setMailid(String mailid) {
		this.mailid = mailid;
	}
	public String getMailkind() {
		return mailkind;
	}
	public void setMailkind(String mailkind) {
		this.mailkind = mailkind;
	}
	public String getMaildatetime() {
		return maildatetime;
	}
	@Override
	public String toString() {
		return "MailList [mailid=" + mailid + ", mailkind=" + mailkind + ", maildatetime=" + maildatetime
				+ ", mailsubject=" + mailsubject + ", mailfrom=" + mailfrom + ", mailto=" + mailto + ", mailcc="
				+ mailcc + ", mailtext=" + mailtext + "]";
	}
	public void setMaildatetime(String maildatetime) {
		this.maildatetime = maildatetime;
	}
	public String getMailsubject() {
		return mailsubject;
	}
	public void setMailsubject(String mailsubject) {
		this.mailsubject = mailsubject;
	}
	public String getMailfrom() {
		return mailfrom;
	}
	public void setMailfrom(String mailfrom) {
		this.mailfrom = mailfrom;
	}
	public String getMailto() {
		return mailto;
	}
	public void setMailto(String mailto) {
		this.mailto = mailto;
	}
	public String getMailcc() {
		return mailcc;
	}
	public void setMailcc(String mailcc) {
		this.mailcc = mailcc;
	}
	public String getMailtext() {
		return mailtext;
	}
	public void setMailtext(String mailtext) {
		this.mailtext = mailtext;
	}
	private String mailto;
	private String mailcc;
	private String mailtext;

}
