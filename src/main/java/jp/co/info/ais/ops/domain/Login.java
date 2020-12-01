package jp.co.info.ais.ops.domain;

/**
 * ユーザーモデルクラス
 *
 * @author AIS-INFO
 */
public class Login {

	//UserID
	private String loginuser;

	//パスワード
	private String passwd;

	public String getLoginuser() {
		return loginuser;
	}
	public void setLoginuser(String loginuser) {
		this.loginuser = loginuser;
	}
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	@Override
	public String toString() {
		return "Login [loginuser=" + loginuser + ", passwd=" + passwd + "]";
	}

}