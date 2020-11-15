package jp.co.info.ais.ops.controller;

import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jp.co.info.ais.ops.service.LoginService;

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

	/**
	 * Sessionの有無で画面移動
	 * @return String login画面, dashboard画面
	 */
	@RequestMapping("")
	public String index(Model model) {
		logger.info(model);
		logger.info(model.toString());
		model.addAttribute("loginErr", "g");
		logger.info("LOGIN PAGE START ===================");
		if (session.getAttribute("id") == null) {
			// sessionに値がなかったら
			return "login.html";
		} else {
			return "login.html";
			//sessionに値があったら
			//return "redirect:/dashboard";
		}

	}



	/**
	 * ログアウト処理を行う。
	 *
	 * @return　String インデックス
	 */
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String Logout() {
		logger.info("LOGOUT START ====");
		try {
			//Session終了
			session.invalidate();
		} catch (Exception e) {
			//エラーメッセージを送る
			logger.error("ログアウト処理エラーが発生 :" + e.toString());
		}
		return "redirect:/";
	}

}