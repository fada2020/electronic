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
@RequestMapping("/detail")
public class DetailTabController {

	//エラーを表すための宣言
	private static final Logger logger = LogManager.getLogger(DetailTabController.class);

	@Autowired
	HttpSession session;

	/**
	 *
	 */
	@RequestMapping(value = "", method = { RequestMethod.GET, RequestMethod.POST })
	public String EventList(Model model) {
		try {

		}catch (Exception e) {
			logger.debug(e.getMessage());
		}
		//戻り値
		return "detail_tab";

	}

}
