package jp.co.info.ais.ops.domain;

/**
 * ユーザーモデルクラス
 *
 * @author AIS-INFO
 */
public class Login {

	//UserID
	private String userid;

	//パスワード
	private String passwd;

	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

}