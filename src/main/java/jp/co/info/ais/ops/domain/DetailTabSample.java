package jp.co.info.ais.ops.domain;

/**
 * 自家補連絡設定サンプルテーブル
 * @author ais-info
 */
public class DetailTabSample {

	private String samplekind; //サンプル区分
	private int voicecycl;     //電話連絡リトライ間隔/0:未処理 1:処理済み 2:開始時刻超過
	private int voicecnt;      //電話連絡リトライ回数
	private String subject;    //メールタイトル
	private String mailtext;   //メール本文

	public String getSamplekind() {
		return samplekind;
	}
	public void setSamplekind(String samplekind) {
		this.samplekind = samplekind;
	}
	public int getVoicecycl() {
		return voicecycl;
	}
	public void setVoicecycl(int voicecycl) {
		this.voicecycl = voicecycl;
	}
	public int getVoicecnt() {
		return voicecnt;
	}
	public void setVoicecnt(int voicecnt) {
		this.voicecnt = voicecnt;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getMailtext() {
		return mailtext;
	}
	public void setMailtext(String mailtext) {
		this.mailtext = mailtext;
	}

	@Override
	public String toString() {
		return "Setting [samplekind=" + samplekind +
				", voicecycl=" + voicecycl +
				", voicecnt="+ voicecnt +
				", subject="+ subject +
				", mailtext="+ mailtext +
				"]";
	}

}