package jp.co.info.ais.ops.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.JsonMappingException;

import jp.co.info.ais.ops.domain.UserMaster;
import jp.co.info.ais.ops.service.UserMasterService;

@Controller
@RequestMapping("/master")
public class UserMasterController {

	//エラーを表すための宣言
	private static final Logger logger = LogManager.getLogger(UserMasterController.class);

	@Autowired
	HttpSession session;

	@Autowired
	UserMasterService usermasterService;

	/**
	 * 設定一覧画面出力
	 * @param model
	 * @return String　画面名
	 */
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String userMasterList(Model model) {
		try {
			logger.debug("権限設定画面＝＝＝開始");
		}catch (Exception e) {
			logger.debug(e.getMessage());
		}
		//戻り値
		return "user_master";
	}


	/**
	 * Masterをリストに表示する.
	 * @param なし
	 * @return JSONArray　jArray
	 */
	@ResponseBody
	@PostMapping("/getUserMasterListAuto")
	public JSONArray ajaxGetList() throws JsonMappingException, IOException {
		logger.debug("ajaxGetList Start===========");
		/*settingリスト生成*/
		List<UserMaster> list = new ArrayList<UserMaster>();
		/*jsonオブジェクト生成*/
		JSONObject obj = new JSONObject();
		/*jsonオブジェクトを格納する配列リストを生成*/
		JSONArray jArray = new JSONArray();
		try {
			list = usermasterService.usermasterList();

			for (int i = 0; i < list.size(); i++) {
				JSONObject sObject = new JSONObject();
				sObject.put("enable", list.get(i).getEnable());
				sObject.put("loginuser", list.get(i).getLoginuser());
				sObject.put("username", list.get(i).getUsername());
				sObject.put("usernamekana", list.get(i).getUsernamekana());
				jArray.add(sObject);
			}

		} catch (Exception e) {

			logger.error(e.getMessage());
		}

		//戻る値
		return jArray;
	}

}
