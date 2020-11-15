package jp.co.info.ais.ops.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/history")
public class HistoryController {

	//エラーを表すための宣言
	private static final Logger logger = LogManager.getLogger(HistoryController.class);

	/**
	 * 設定一覧画面出力
	 *
	 * @param model
	 * @return String　画面名
	 */
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String SettingList(Model model) {
		try {
			//TODO:DBデータ取得及び加工処理追加
			logger.debug("操作履歴一覧画面＝＝＝開始");

		}catch (Exception e) {
			logger.debug(e.getMessage());
		}
		//戻り値
		return "action_history";
	}


}
