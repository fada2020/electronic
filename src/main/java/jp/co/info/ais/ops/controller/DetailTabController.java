package jp.co.info.ais.ops.controller;

import java.util.ArrayList;
import java.util.List;

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

import jp.co.info.ais.ops.domain.DetailTab;
import jp.co.info.ais.ops.domain.Site;
import jp.co.info.ais.ops.domain.UserMaster;
import jp.co.info.ais.ops.service.DetailTabService;
import jp.co.info.ais.ops.service.SettingService;
import jp.co.info.ais.ops.service.UserGrantService;

@Controller
@RequestMapping("/detail")
public class DetailTabController {

	@Autowired
	HttpSession session;

	//エラーを表すための宣言
	private static final Logger logger = LogManager.getLogger(DetailTabController.class);
	private static final String INSERT_FLAG = "add";
	private static final String UPDATE_FLAG = "edit";

	@Autowired
	DetailTabController detailTabController;

	@Autowired
	DetailTabService detailTabService;
	@Autowired
	SettingService settingService;
	@Autowired
	UserGrantService userGrantService;

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
			//サイト選択リスト
			List<Site> siteList = new ArrayList<Site>();
			siteList = settingService.getSiteList();
			model.addAttribute("siteList", siteList);

			//ユーザー選択リスト
			List<UserMaster> userList = new ArrayList<UserMaster>();
			userList = userGrantService.getUserList();
			model.addAttribute("userList", userList);


		}catch (Exception e) {
			logger.error(e.getMessage());
		}
		//戻り値
		return "detail_tab";

	}

	/**
	 * 詳細設定：登録/編集の処理
	 */
	@RequestMapping(value = "/insert", method = { RequestMethod.POST })
	public String insert(Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {

		try {
			//パラメータ取得
			String viewFlag = request.getParameter("viewFlag");
			String shisetsunou = request.getParameter("shisetsunou");
			String customer = request.getParameter("customer");
			String sitename = request.getParameter("sitename");
			String sitecd = request.getParameter("sitecd");
			String address = request.getParameter("address");
			String outermailaddr = request.getParameter("outermailaddr");
			String intermailaddr = request.getParameter("intermailaddr");
			String adminuserid = request.getParameter("adminuserid");
			String adminusername = request.getParameter("adminusername");
			String adminmailaddress = request.getParameter("adminmailaddress");
			logger.debug("顧客番号："+shisetsunou);

			if(viewFlag.equals(INSERT_FLAG)) {
				//logger.debug("詳細設定-登録 開始 ===========");
				System.out.println("詳細設定-登録 開始 ===========");

			}else {
				//logger.debug("詳細設定-編集 開始 ===========");
				System.out.println("詳細設定-編集 開始 ===========");

			}

		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		//戻る値
		//return "setting";
		return "redirect:/setting/";
	}

}
