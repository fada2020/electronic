package jp.co.info.ais.ops.domain;

/**
 * ユーザーモデルクラス
 *
 * @author AIS-INFO
 */
public class Login {

	//UserID
	private String loginuser;

	//UserName
	private String username;

	//パスワード
	private String passwd;

	//権限
	private String enable;

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
	public String getEnable() {
		return enable;
	}
	public void setEnable(String enable) {
		this.enable = enable;
	}

	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public String toString() {
		return "Login [loginuser=" + loginuser + ", usernaem=" + username + ", passwd=" + passwd + ", enable=" + enable + "]";
	}

}