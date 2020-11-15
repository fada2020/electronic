package jp.co.info.ais.ops.controller;

import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/master")
public class UserMasterController {

	//エラーを表すための宣言
	private static final Logger logger = LogManager.getLogger(UserMasterController.class);

	@Autowired
	HttpSession session;

	/**
	 *
	 */
	@RequestMapping(value = "", method = { RequestMethod.GET, RequestMethod.POST })
	public String EventList(Model model) {
		try {
			//TODO:DBデータ取得及び加工処理追加
			logger.debug("権限設定画面＝＝＝開始");

		}catch (Exception e) {
			logger.debug(e.getMessage());
		}
		//戻り値
		return "user_master";

	}

}
