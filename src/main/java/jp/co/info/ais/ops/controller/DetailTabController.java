package jp.co.info.ais.ops.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import jp.co.info.ais.ops.domain.Contact;
import jp.co.info.ais.ops.domain.DetailTab;
import jp.co.info.ais.ops.domain.DetailTabSample;
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
	private static final String ERROR_MSG = "＊登録済みのお客様番号です。 ご確認ください。";
	private static final String SYS_ERROR_MSG = "＊登録・編集処理中にエラーに障害が発生しました。";
	//開始判定条件・連絡コード
	private static final String SAMPLE_KIND_1 = "01";
	//終了判定条件・連絡コード
	private static final String SAMPLE_KIND_2 = "02";
	private static final String FILE_PATH = "/Users/kimjm/01.WORK_APP/audio";

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
				detailTab = detailTabService.selectInfo(customerno);
				model.addAttribute("viewFlag", UPDATE_FLAG);
				model.addAttribute("detailTab", detailTab);
			}else {
			    //新規
				detailTab.setUpoaid((String)session.getAttribute("id"));
				detailTab.setUpoaname((String)session.getAttribute("name"));
				detailTab.setJdgsw("0"); //0:使用しない

				//TAB-2のSample取得
				DetailTabSample startTabData = new DetailTabSample();
				startTabData = detailTabService.getDetailTabSamle(SAMPLE_KIND_1);
				detailTab.setStartvoicecycl(startTabData.getVoicecycl());
				detailTab.setStartvoicecnt(startTabData.getVoicecnt());
				detailTab.setStartsubject(startTabData.getSubject());
				detailTab.setStartmailtext(startTabData.getMailtext());
				//TAB-3のSample取得
				DetailTabSample endTabData = new DetailTabSample();
				endTabData = detailTabService.getDetailTabSamle(SAMPLE_KIND_2);
				detailTab.setEndvoicecycl(endTabData.getVoicecycl());
				detailTab.setEndvoicecnt(endTabData.getVoicecnt());
				detailTab.setEndsubject(endTabData.getSubject());
				detailTab.setEndmailtext(endTabData.getMailtext());

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

			//エラーメッセージ出力のため
			model.addAttribute("errMsg", null);
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

		boolean ref = true;
		try {
			String upUserId = request.getParameter("upUserId");
			String upUserName = request.getParameter("upUserName");

			DetailTab detailTab = new DetailTab();
			int result=0;

			//パラメータ取得
			//TAB-1
			String viewFlag = request.getParameter("viewFlag");
			String customerNo = request.getParameter("customerno");
			detailTab.setCustomerno(customerNo);
			detailTab.setSitename(request.getParameter("sitename"));
			String sitecd = request.getParameter("sitecd");
			if(!sitecd.isEmpty()) {
				detailTab.setSitecd(Integer.parseInt(sitecd));
			}
			detailTab.setCustomer(request.getParameter("customer"));
			detailTab.setAddress(request.getParameter("address"));
			detailTab.setJdgsw(request.getParameter("jdgsw"));

			String intermailaddr = request.getParameter("intermailaddr");
			String interusername = request.getParameter("interusername");
			String loginuser = request.getParameter("loginuser");
			detailTab.setIntermailaddr(intermailaddr);
			detailTab.setInterusername(interusername);
			detailTab.setOutermailaddr(request.getParameter("outermailaddr"));

			detailTab.setAdminuserid(request.getParameter("adminuserid"));
			detailTab.setAdminusername(request.getParameter("adminusername"));
			detailTab.setAdminmailaddress(request.getParameter("adminmailaddress"));
			detailTab.setAdminphoneno(request.getParameter("adminphoneno"));

			//TAB-2
			String startcontactcd = request.getParameter("startcontactcd");
			if(!startcontactcd.isEmpty()) {
				detailTab.setStartcontactcd(Integer.parseInt(startcontactcd));
			}

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
			String endcontactcd = request.getParameter("endcontactcd");
			if(!endcontactcd.isEmpty()) {
				detailTab.setEndcontactcd(Integer.parseInt(endcontactcd));
			}
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
			detailTab.setUpoaid(upUserId);
			detailTab.setUpoaname(upUserName);

			if(viewFlag.equals(INSERT_FLAG)) {
				logger.debug("詳細設定-登録 開始 ===========");
				if (detailTabService.checkCustomerNo(customerNo) < 1) {
					//自家補連絡設定情報を登録
					result = detailTabService.insertDetail(detailTab);
					//自自家補連絡展開ユーザーリストを登録
					if(!loginuser.isEmpty()) {
				        String[] luseer = loginuser.split(",");
				        for (String id : luseer) {
							Map<String, Object> paraMap = new HashMap<>();
							paraMap.put("customerno", customerNo);
							paraMap.put("loginuser", id);
				        	detailTabService.insertMailAddr(paraMap);
				        }
					}
					//エラーメッセージ出力のため
				    if(result < 1) {
				    	ref = false;
						model.addAttribute("errMsg", SYS_ERROR_MSG);
				    }else {
						model.addAttribute("errMsg", null);
				    }
				}else {
					ref = false;
					//エラーメッセージ出力のため
					model.addAttribute("errMsg", ERROR_MSG);
				}
			}else {
				logger.debug("詳細設定-編集 開始 ===========");
				//編集処理を呼ぶ
				result = detailTabService.updateDetail(detailTab);
				if(!intermailaddr.isEmpty()) {
					detailTabService.deleteMailList(customerNo);
					if(!loginuser.isEmpty()) {
				        String[] luseer = loginuser.split(",");
				        for (String id : luseer) {
							Map<String, Object> paraMap = new HashMap<>();
							paraMap.put("customerno", customerNo);
							paraMap.put("loginuser", id);
				        	detailTabService.insertMailAddr(paraMap);
				        }
					}
				}
			    if(result < 1) {
			    	ref = false;
					model.addAttribute("errMsg", SYS_ERROR_MSG);
			    }else {
					model.addAttribute("errMsg", null);
			    }
			}
			//エラー発生時、処理
			if(!ref) {
				model.addAttribute("detailTab", detailTab);
				//サイト選択リスト
				List<Site> siteList = new ArrayList<Site>();
				siteList = settingService.getSiteList();
				model.addAttribute("siteList", siteList);
				//ユーザー選択リスト
				List<UserMaster> userList = new ArrayList<UserMaster>();
				userList = userGrantService.getUserList();
				model.addAttribute("userList", userList);
				//連絡先選択リスト
				List<Contact> contactList = new ArrayList<Contact>();
				contactList = contactService.contactAllList();
				model.addAttribute("contList", contactList);
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		//戻る値
		if(ref) {
			return "redirect:/setting/";
		}else {
			return "detail_tab";
		}
	}

    /**
     * ファイルアップロード
     * @param uploadFile
     * @return
     */
    @PostMapping("/uploadwav")
   public ResponseEntity< byte[]> uploadWav(@RequestParam("startFileName") final MultipartFile uploadFile) {

	   logger.debug("詳細設定画面===FILE UPLOAD");

       if(uploadFile.isEmpty ())  {
           return ResponseEntity.of(Optional.empty());
       }
       final Path path = Paths.get (FILE_PATH, uploadFile.getOriginalFilename());
       final byte[] bytes ;
       try  {
           bytes = uploadFile.getBytes();
           Files.write(path, bytes);
       }catch(IOException e){
           return ResponseEntity.of(Optional.empty());
       }
       return ResponseEntity.ok(bytes);
   }

   /**
    * ファイルアップロード
    * @param uploadFile
    * @return
    */
   @PostMapping("/uploadendwav")
  public ResponseEntity< byte[]> uploadEndWav(@RequestParam("endFileName") final MultipartFile uploadFile) {

	  logger.debug("詳細設定画面===FILE END UPLOAD");

      if(uploadFile.isEmpty ())  {
          return ResponseEntity.of(Optional.empty());
      }
      final Path path = Paths.get (FILE_PATH, uploadFile.getOriginalFilename());
      final byte[] bytes ;
      try  {
          bytes = uploadFile.getBytes();
          Files.write(path, bytes);
      }catch(IOException e){
          return ResponseEntity.of(Optional.empty());
      }
      return ResponseEntity.ok(bytes);
  }

}
