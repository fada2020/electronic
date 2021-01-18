package jp.co.info.ais.ops.controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import jp.co.info.ais.ops.domain.Login;
import jp.co.info.ais.ops.helper.DateHelper;
import jp.co.info.ais.ops.service.LoginService;
import jp.co.info.ais.ops.service.UserGrantService;

@Controller
@RequestMapping("/")
public class LoginController {

	private static final Logger logger = LogManager.getLogger(LoginController.class);

	@Autowired
	LoginController loginController;

	@Autowired
	HttpSession session;

	@Autowired
	private LoginService loginService;
	@Autowired
	private UserGrantService userGrantservice;

	/**
	 * Sessionの有無で画面移動
	 * @return String login画面
	 */
	@RequestMapping("")
	public String index(Model model) {
		logger.info(model);
		logger.info(model.toString());
		model.addAttribute("loginErr", "g");
		logger.info("LOGIN PAGE START ===================");
		//Session終了
		session.invalidate();
		return "login.html";
	}

	/**
	 * アカウントとパスワード認証処理
	 * @param loginuser
	 * @param Passwd
	 * @param model
	 * @return int login画面
	 * @throws Throwable
	 */
    @PostMapping("/loginprocess")
	@ResponseBody
	public int loginProcess(@RequestBody Login login, Model model) throws Throwable {
		int result = 0;
		logger.info("LOGIN PROCESS START : " + login.getLoginuser());
		try {
			//アカウント存在チェック
			if (loginService.selectLoginId(login.getLoginuser()) < 1) {
				result = 1;
			} else {
				Login user = loginService.selectLogin(login.getLoginuser(), login.getPasswd());

				//パスワード有効性チェック
				if (user == null) {
					result = 2;
				} else {
					//障害・不具合 #1950:パスワード有効期限チェック
					SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
					//現在の日付を取得
					String currentDate = DateHelper.getNowDate();
					//パスワード最終修正日取得
					String syuuseiDate = DateHelper.getDateFormatYMD(user.getSyuuseitime());
					//パスワード有効期限取得
					int maxAge = user.getPwdmaxage();
					Date cDate = df.parse(currentDate);
					Date sDate = df.parse(DateHelper.getDateFormatYMD(syuuseiDate));
					Calendar cCalendar = Calendar.getInstance();
					Calendar sCalendar = Calendar.getInstance();
					cCalendar.setTime(cDate);
					sCalendar.setTime(sDate);
					sCalendar.add(Calendar.DATE, maxAge);

					if(cCalendar.after(sCalendar)) {
						//パスワード有効期限超過
						result = 3;
					}else {
						result = 9;
						String enable = (String) userGrantservice.selectGrantInfo(user.getLoginuser());
						session.setAttribute("id", user.getLoginuser());
						session.setAttribute("name", user.getUsername());
						session.setAttribute("enable", enable);
					}
				}
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return result;
	}


	/**
	 * ログアウト処理を行う
	 *
	 * @return String インデックス
	 */
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String Logout() {
		logger.info("LOGOUT START ====");
		try {
			//Session終了
			session.invalidate();
		} catch (Exception e) {
			logger.error("ログアウト処理エラーが発生 :" + e.toString());
		}
		return "login.html";
	}

}