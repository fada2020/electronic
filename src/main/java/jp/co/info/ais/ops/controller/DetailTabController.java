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

import jp.co.info.ais.ops.domain.Contact;
import jp.co.info.ais.ops.domain.DetailTab;
import jp.co.info.ais.ops.domain.Site;
import jp.co.info.ais.ops.domain.UserMaster;
import jp.co.info.ais.ops.service.ContactService;
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
	@Autowired
	ContactService contactService;

	/**
	 * 詳細設定初期画面
	 */
	@RequestMapping(value = "", method = { RequestMethod.POST })
	public String select(Model model, HttpServletRequest request, HttpServletResponse response) throws Exception  {

		logger.debug("詳細設定画面===開始");

		try {
			//パラメータ取得
			String customerno = request.getParameter("customernou");
			logger.debug("顧客番号："+customerno);

			DetailTab detailTab = new DetailTab();
			if(!customerno.isEmpty()) {
				//編集
				System.out.println("Update START");
				detailTab = detailTabService.selectInfo(customerno);
				model.addAttribute("viewFlag", UPDATE_FLAG);
				model.addAttribute("detailTab", detailTab);
			}else {
			    //新規
				System.out.println("Insert START");
				model.addAttribute("viewFlag", INSERT_FLAG);
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

			List<Contact> contactList = new ArrayList<Contact>();
			contactList = contactService.contactAllList();
			model.addAttribute("contList", contactList);

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
			DetailTab detailTab = new DetailTab();
			int result=0;

			//パラメータ取得
			//TAB-1
			String viewFlag = request.getParameter("viewFlag");
			detailTab.setCustomerno(request.getParameter("customerno"));
			detailTab.setSitename(request.getParameter("sitename"));
			String sitecd = request.getParameter("sitecd");
			if(!sitecd.isEmpty()) {
				detailTab.setSitecd(Integer.parseInt(sitecd));
			}
			detailTab.setCustomer(request.getParameter("customer"));
			detailTab.setAddress(request.getParameter("address"));
			//TODO:変換処理(セミコロン(;)区切り)
			detailTab.setOutermailaddr(request.getParameter("outermailaddr").replaceAll("\r\n", ";"));
			detailTab.setIntermailaddr(request.getParameter("intermailaddr").replaceAll("\r\n", ";"));
			//TODO:END
			detailTab.setAdminuserid(request.getParameter("adminuserid"));
			detailTab.setAdminusername(request.getParameter("adminusername"));
			detailTab.setAdminmailaddress(request.getParameter("adminmailaddress"));

			//TAB-2
			detailTab.setStartcontactno(request.getParameter("startcontactno"));
			detailTab.setStartvoicepath(request.getParameter("startvoicepath"));
			String startvoicecycl = request.getParameter("startvoicecycl");
			if(!startvoicecycl.isEmpty()) {
				detailTab.setStartvoicecycl(Integer.parseInt(startvoicecycl));
			}
			String startvoicecnt = request.getParameter("startvoicecnt");
			if(!startvoicecnt.isEmpty()) {
				detailTab.setStartvoicecnt(Integer.parseInt(startvoicecnt));
			}
			detailTab.setStartsubject(request.getParameter("startsubject"));
			detailTab.setStartmailtext(request.getParameter("startmailtext"));

			//TAB-3
			detailTab.setEndcontactno(request.getParameter("endcontactno"));
			detailTab.setEndvoicepath(request.getParameter("endvoicepath"));
			String endvoicecycl = request.getParameter("endvoicecycl");
			if(!endvoicecycl.isEmpty()) {
				detailTab.setEndvoicecycl(Integer.parseInt(endvoicecycl));
			}
			String endvoicecnt = request.getParameter("endvoicecnt");
			if(!endvoicecnt.isEmpty()) {
				detailTab.setEndvoicecnt(Integer.parseInt(endvoicecnt));
			}
			detailTab.setEndsubject(request.getParameter("endsubject"));
			detailTab.setEndmailtext(request.getParameter("endmailtext"));

			if(viewFlag.equals(INSERT_FLAG)) {
				logger.debug("詳細設定-登録 開始 ===========");
				//TODO：お客様番号の重複チェックが必要か？
				//登録処理を呼ぶ
				result = detailTabService.insertDetail(detailTab);
			}else {
				logger.debug("詳細設定-編集 開始 ===========");
				//編集処理を呼ぶ
				result = detailTabService.updateDetail(detailTab);
			}

		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		//戻る値
		return "redirect:/setting/";
	}

}
