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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.JsonMappingException;

import jp.co.info.ais.ops.domain.Contact;
import jp.co.info.ais.ops.service.ContactService;

@Controller
@RequestMapping("/contact")
public class ContactController {

	@Autowired
	HttpSession session;

	@Autowired
	ContactService contactService;

	//エラーを表すための宣言
	private static final Logger logger = LogManager.getLogger(ContactController.class);

	/**
	 * 設定一覧画面出力
	 * @param model
	 * @return String　画面名
	 */
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String ContactList(Model model) {
		try {
			logger.debug("contact List Start");
		}catch (Exception e) {
			logger.debug(e.getMessage());
		}
		//戻り値
		return "contact_list";
	}

    /**
     * Contactをリストに表示する.
     * @param なし
     * @return JSONArray　jArray
     */
    @ResponseBody
    @PostMapping("/getContactListAuto")
    public JSONArray ajaxGetList() throws JsonMappingException, IOException  {
    	logger.debug("ajaxGetList Start===========");
    	/*sessionのenable取得*/
		String enable = (String)session.getAttribute("enable");
    	/*settingリスト生成*/
    	List<Contact> list = new ArrayList<Contact>();
		/*jsonオブジェクト生成*/
    	JSONObject obj = new JSONObject();
    	/*jsonオブジェクトを格納する配列リストを生成*/
		JSONArray jArray = new JSONArray();
		try {

			list = contactService.contactList();

			 for (int i = 0; i < list.size(); i++)
			 {
			 JSONObject sObject = new JSONObject();
			 sObject.put("rownum", list.get(i).getRownum());
			 sObject.put("contactcd", list.get(i).getContactcd());
			 sObject.put("contactname", list.get(i).getContactname());
			 sObject.put("enable", enable);
			 sObject.put("startcontactcd", list.get(i).getStartcontactcd());
			 sObject.put("endcontactcd", list.get(i).getEndcontactcd());
			 sObject.put("contactphoneno", list.get(i).getContactphoneno());
			 sObject.put("contactmailaddress", list.get(i).getContactmailaddress());
			 jArray.add(sObject);
			 }

		}catch (Exception e) {

			logger.error(e.getMessage());
		}

    	//戻る値
    	return jArray;
    }

    /**
     * Contactを編集する.
     * @param Contact contact
     * @return int result
     */
    @ResponseBody
    @PostMapping("/updateContact")
    public int updateContact(@RequestBody Contact contact) throws Exception   {

    	logger.debug("Contact update Start===========");
    	/*settingリスト生成*/
    	int result=0;
		try {
			result = contactService.updateContact(contact);
		}catch (Exception e) {
			logger.error(e.getMessage());
		}
    	//戻る値
    	return result;
    }

    /**
     * Contactを新規で作成する.
     * @param Contact contact
     * @return int result
     */
    @ResponseBody
    @PostMapping("/addContact")
    public int addContact(@RequestBody Contact contact) throws Exception   {
    	logger.debug("Contact add Start===========");
    	/*settingリスト生成*/
    	int result=0;
		try {
			result = contactService.addContact(contact);
		}catch (Exception e) {
			logger.error(e.getMessage());
		}
    	//戻る値
    	return result;
    }

    /**
     * Contactを削除する.
     * @param int contactcd
     * @return int result
     */
    @ResponseBody
    @PostMapping("/deleteContact")
    public int deleteContact(@RequestBody int contactcd) throws Exception   {
    	logger.debug("Contact delete Start===========");
    	/*settingリスト生成*/
    	int result=0;
		try {
			result = contactService.deleteContact(contactcd);
		}catch (Exception e) {
			logger.error(e.getMessage());
		}
    	//戻る値
    	return result;
    }
}
