package jp.co.info.ais.ops.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/detail")
public class DetailTabController {

	//エラーを表すための宣言
	private static final Logger logger = LogManager.getLogger(DetailTabController.class);

	@Autowired
	HttpSession session;

	/**
	 *
	 */
	@RequestMapping(value = "", method = { RequestMethod.POST })
	public String select(Model model, HttpServletRequest request, HttpServletResponse response) throws Exception  {

		logger.debug("詳細設定画面===開始");

		try {
			//パラメータ取得
			String shisetsunou = request.getParameter("shisetsunou");
			shisetsunou = "dceoef";
			logger.debug("顧客番号："+shisetsunou);

			if(!shisetsunou.isEmpty()) {
				//編集
				model.addAttribute("viewFlag", "add");

			}else {
			    //新規
				model.addAttribute("viewFlag", "edit");

			}




		}catch (Exception e) {
			logger.debug(e.getMessage());
		}
		//戻り値
		return "detail_tab";

	}

}
