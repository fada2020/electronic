package jp.co.info.ais.ops.domain;

public class UserMaster {
	private String enable;//権限
	private String loginuser;//アカウント
	private String username;//ユーザー名称
	private String usernamekana;//ユーザーカナ
	public String getEnable() {
		return enable;
	}
	public void setEnable(String enable) {
		this.enable = enable;
	}
	public String getLoginuser() {
		return loginuser;
	}
	public void setLoginuser(String loginuser) {
		this.loginuser = loginuser;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getUsernamekana() {
		return usernamekana;
	}
	public void setUsernamekana(String usernamekana) {
		this.usernamekana = usernamekana;
	}
	@Override
	public String toString() {
		return "Master [enable=" + enable + ", loginuser=" + loginuser + ", username=" + username + ", usernamekana="
				+ usernamekana + "]";
	}

}
