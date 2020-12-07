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
import org.springframework.web.bind.annotation.ResponseBody;

import jp.co.info.ais.ops.domain.DetailTab;
import jp.co.info.ais.ops.service.DetailTabService;

@Controller
@RequestMapping("/detail")
public class DetailTabController {

	//エラーを表すための宣言
	private static final Logger logger = LogManager.getLogger(DetailTabController.class);

	@Autowired
	HttpSession session;

	@Autowired
	DetailTabController detailTabController;

	@Autowired
	DetailTabService detailTabService;

	/**
	 * 詳細設定初期画面
	 */
	@RequestMapping(value = "", method = { RequestMethod.POST })
	public String select(Model model, HttpServletRequest request, HttpServletResponse response) throws Exception  {

		logger.debug("詳細設定画面===開始");

		try {
			//パラメータ取得
			String userid = request.getParameter("shisetsunou");
			logger.debug("顧客番号："+userid);
			System.out.println("顧客番号："+userid);

			DetailTab detailTab = new DetailTab();

			if(!userid.isEmpty()) {
				//編集
				detailTab = detailTabService.selectInfo(userid);
				model.addAttribute("viewFlag", "edit");
				model.addAttribute("detailTab", detailTab);
			}else {
			    //新規
				model.addAttribute("viewFlag", "add");
				model.addAttribute("detailTab", detailTab);
			}

		}catch (Exception e) {
			logger.error(e.getMessage());
		}
		//戻り値
		return "detail_tab";

	}


	/**
	 * 詳細設定編集処理
	 */
	@ResponseBody
	@RequestMapping(value = "/update", method = { RequestMethod.POST })
	public String update(Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		logger.debug("詳細設定編集 開始 ===========");
		try {
			//パラメータ取得
			String shisetsunou = request.getParameter("shisetsunou");
			shisetsunou = "dceoef";
			logger.debug("顧客番号："+shisetsunou);

			//model.addAttribute("shisetsuno", shisetsuno);

		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		//戻る値
		return "setting";
	}


	/**
	 * 詳細設定登録処理
	 */
	@ResponseBody
	@RequestMapping(value = "/insert", method = { RequestMethod.POST })
	public String insert(Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		logger.debug("詳細設定登録 開始 ===========");
		try {
			//パラメータ取得
			String shisetsunou = request.getParameter("shisetsunou");
			shisetsunou = "dceoef";
			logger.debug("顧客番号："+shisetsunou);



			//model.addAttribute("shisetsuno", shisetsuno);

		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		//戻る値
		return "setting";
	}

}
