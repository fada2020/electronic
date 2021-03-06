package jp.co.info.ais.ops.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor.HSSFColorPredefined;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
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

import jp.co.info.ais.ops.domain.Setting;
import jp.co.info.ais.ops.service.SettingService;

@Controller
@RequestMapping("/setting")
public class SettingController {

	@Autowired
	HttpSession session;

	@Autowired
	SettingService settingService;
	//エラーを表すための宣言
	private static final Logger logger = LogManager.getLogger(SettingController.class);

	private static final String EXCEL_FLAG = "excel";
	private static final String SIYO = "1";
	private static final String SIYO_TXT = "使用中";
	private static final String MISIYO = "0";
	private static final String MISIYO_TXT = "未使用";
	private static final String HANTEI = "1";
	private static final String HANTEI_TXT = "判定中";
	private static final String MIHANTEI = "0";
	private static final String MIHANTEI_TXT = "停止中";
	private static final String YUKO = "1";
	private static final String YUKO_TXT = "✔";
	private static final String MUKO = "0";
	private static final String MUKO_TXT = "-";
	private static final String NULL_TXT = "-";
	private static final String CALENDAR_TXT = "-";


	/**
	 * 設定一覧画面出力
	 *
	 * @param model
	 * @return String 画面名
	 */
	@RequestMapping(value = "", method = RequestMethod.POST)
	public String excelExport(Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm");
		    Date nowdate = new Date();
		    String dateString = formatter.format(nowdate);
		    model.addAttribute("date",dateString+" 現在");
			String flag = request.getParameter("flag");
			//パラメータ格納
			List<Setting> list = null;
			//Excel出力
			if(flag.equals(EXCEL_FLAG)) {
				list = settingService.settingList();
				excelDown(list, response);
			}
		} catch (Exception e) {
			logger.debug(e.getMessage());
		}
		//戻り値
		return "setting";
	}

	/**
	 * 設定一覧初期表示
	 * @param todo Model model
	 * @return SONArray jArray
	 */
	@ResponseBody
	@PostMapping("/getListAuto")
	public JSONArray ajaxGetList(Model model) throws JsonMappingException, IOException {
		/*sessionのenable取得*/
		String enable = (String)session.getAttribute("enable");
		/*settingリスト生成*/
		List<Setting> list = new ArrayList<Setting>();
		/*jsonオブジェクト生成*/
		JSONObject obj = new JSONObject();
		/*jsonオブジェクトを格納する配列リストを生成*/
		JSONArray jArray = new JSONArray();
		try {
			list = settingService.settingList();
			for (int i = 0; i < list.size(); i++) {
				JSONObject sObject = new JSONObject();
				sObject.put("rownum", list.get(i).getRownum());
				sObject.put("customerno", list.get(i).getCustomerno());
				sObject.put("customer", list.get(i).getCustomer());
				sObject.put("enable", enable);
				sObject.put("sitecd", list.get(i).getSitecd());
				sObject.put("jdgsw", list.get(i).getJdgsw());
				sObject.put("endjdgsw", list.get(i).getEndjdgsw());
				sObject.put("status", list.get(i).getStatus());
				sObject.put("starttime", list.get(i).getStarttime());
				sObject.put("sitename", list.get(i).getSitename());
				jArray.add(sObject);
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		//戻り値
		return jArray;
	}

	/**
	 * 設定一覧削除
	 * @param String customerno
	 * @return int result
	 */
	@ResponseBody
	@PostMapping("/delete")
	public int delete(@RequestBody String customerno) throws Exception {
		int result = 0;
		try {
			result = settingService.deleteCustomerno(customerno);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		//戻り値
		return result;
	}

	/**
	 * 設定一覧編集
	 * @param String customerno, Model model
	 * @return String 画面名
	 */
	@ResponseBody
	@PostMapping("/update")
	public String update(@RequestBody String customerno, Model model) throws Exception {
		try {
			model.addAttribute("customerno", customerno);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		//戻り値
		return "detail";
	}

	/**
	 * 現況変更・終了判定切替
	 * @param Setting setting
	 * @return int result
	 */
	@ResponseBody
	@PostMapping("/updateStatus")
	public int updateStatus(@RequestBody Setting setting) throws Exception {
		int result = 0;
		String upoaname = "";
		String upoaid = "";
		String upoatime = "";
		SimpleDateFormat format1 = new SimpleDateFormat ( "yyyy-MM-dd HH:mm:ss");
		Date time = new Date();

		try {
			upoatime = format1.format(time);
			upoaid = (String) session.getAttribute("id");
			upoaname = (String) session.getAttribute("name");
			setting.setUpoaid(upoaid);
			setting.setUpoaname(upoaname);
			setting.setUpoatime(upoatime);
			result = settingService.updateStatus(setting);

		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		//戻り値
		return result;
	}

	/**
	 * Excelファイル出力
	 *
	 * @param list
	 * @param response
	 * @throws Exception
	 */
	private void excelDown(List<Setting> list,HttpServletResponse response) throws Exception  {

		try {

			//ワークブック生成
		    Workbook wb = new HSSFWorkbook();
		    org.apache.poi.ss.usermodel.Sheet sheet = wb.createSheet("自家補連絡設定一覧");
		    Row row = null;
		    Cell cell = null;
		    int rowNo = 0;
		    String time = "";

		    //ヘッダースタイル
		    CellStyle headStyle = wb.createCellStyle();

		    //セルの幅
		    sheet.setColumnWidth(0, 2000);//No
		    sheet.setColumnWidth(1, 2500);//現況
		    sheet.setColumnWidth(2, 2500);//終了判定
		    sheet.setColumnWidth(3, 8000);//使用開始日時
		    sheet.setColumnWidth(4, 2500);//有効
		    sheet.setColumnWidth(5, 8000);//お客様番号
		    sheet.setColumnWidth(6, 8000);//施設名
		    sheet.setColumnWidth(7, 8000);//サイトID
		    sheet.setColumnWidth(8, 10000);//サイト名

		    //細いセル指定
		    headStyle.setBorderTop(BorderStyle.THIN);
		    headStyle.setBorderBottom(BorderStyle.THIN);
		    headStyle.setBorderLeft(BorderStyle.THIN);
		    headStyle.setBorderRight(BorderStyle.THIN);

		    //背景色
		    headStyle.setFillForegroundColor(HSSFColorPredefined.YELLOW.getIndex());
		    headStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

		    //データをセンター
		    headStyle.setAlignment(HorizontalAlignment.CENTER);

		    //データ用セールスタイルの枠線指定
		    CellStyle bodyStyle = wb.createCellStyle();
		    bodyStyle.setBorderTop(BorderStyle.THIN);
		    bodyStyle.setBorderBottom(BorderStyle.THIN);
		    bodyStyle.setBorderLeft(BorderStyle.THIN);
		    bodyStyle.setBorderRight(BorderStyle.THIN);

		    //ヘッダー生成
		    row = sheet.createRow(rowNo++);
		    cell = row.createCell(0);
		    cell.setCellStyle(headStyle);
		    cell.setCellValue("No");
		    cell = row.createCell(1);
		    cell.setCellStyle(headStyle);
		    cell.setCellValue("現況");
		    cell = row.createCell(2);
		    cell.setCellStyle(headStyle);
		    cell.setCellValue("終了判定");
		    cell = row.createCell(3);
		    cell.setCellStyle(headStyle);
		    cell.setCellValue("使用開始日時");
		    cell = row.createCell(4);
		    cell.setCellStyle(headStyle);
		    cell.setCellValue("有効");
		    cell = row.createCell(5);
		    cell.setCellStyle(headStyle);
		    cell.setCellValue("お客様番号");
		    cell = row.createCell(6);
		    cell.setCellStyle(headStyle);
		    cell.setCellValue("施設名");
		    cell = row.createCell(7);
		    cell.setCellStyle(headStyle);
		    cell.setCellValue("サイトID");
		    cell = row.createCell(8);
		    cell.setCellStyle(headStyle);
		    cell.setCellValue("サイト名");

		    //データ生成
		    for(Setting setting : list) {
		        row = ((org.apache.poi.ss.usermodel.Sheet) sheet).createRow(rowNo++);

		        cell = row.createCell(0);//No
		        cell.setCellStyle(bodyStyle);
		        cell.setCellValue(setting.getRownum());

		        cell = row.createCell(1);//現況
		        cell.setCellStyle(bodyStyle);
		        if(setting.getStatus()!=null&&setting.getStatus().equals(SIYO)) {
		        	cell.setCellValue(SIYO_TXT);
		        }else if(setting.getStatus().equals(MISIYO)) {
		        	cell.setCellValue(MISIYO_TXT);
		        }

		        cell = row.createCell(2);//終了判定
		        cell.setCellStyle(bodyStyle);
		        if(setting.getEndjdgsw()!=null&&setting.getEndjdgsw().equals(HANTEI)) {
		        	cell.setCellValue(HANTEI_TXT);
		        }else if(setting.getEndjdgsw().equals(MIHANTEI)) {
		        	cell.setCellValue(MIHANTEI_TXT);
		        }

		        cell = row.createCell(3);//使用開始日時
		        cell.setCellStyle(bodyStyle);
		        if(setting.getStarttime()!=null&&setting.getStatus().equals(SIYO)){
		        	String str= setting.getStarttime();
		        	time = str.replaceAll(NULL_TXT, CALENDAR_TXT);
		        	cell.setCellValue(time);
		        }else {
		        	cell.setCellValue(NULL_TXT);
		        }

		        cell = row.createCell(4);//有効
		        cell.setCellStyle(bodyStyle);
		        if(setting.getJdgsw()!=null&&setting.getJdgsw().equals(YUKO)) {
		        	cell.setCellValue(YUKO_TXT);
		        }else if(setting.getJdgsw().equals(MUKO)) {
		        	cell.setCellValue(MUKO_TXT);
		        }

		        cell = row.createCell(5);//お客様番号
		        cell.setCellStyle(bodyStyle);
		        if(setting.getCustomerno()!=null) {
		        cell.setCellValue(setting.getCustomerno());
		        }else {
		        	cell.setCellValue(NULL_TXT);
		        }

		        cell = row.createCell(6);//施設名
		        cell.setCellStyle(bodyStyle);
		        if(setting.getCustomer()!=null) {
		        cell.setCellValue(setting.getCustomer());
		        }else {
		        	cell.setCellValue(NULL_TXT);
		        }

		        cell = row.createCell(7);//サイトID
		        cell.setCellStyle(bodyStyle);
		        if(setting.getSitecd()!=0) {
		        cell.setCellValue(setting.getSitecd());
		        }else {
		        	cell.setCellValue(NULL_TXT);
		        }

		        cell = row.createCell(8);//サイト名
		        cell.setCellStyle(bodyStyle);
		        if(setting.getSitename()!=null) {
		        cell.setCellValue(setting.getSitename());
		        }else {
		        	cell.setCellValue(NULL_TXT);
		        }
		    }

		    //コンテンツタイプとファイル名指定
		    SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmm");
		    Date nowdate = new Date();
		    String dateString = formatter.format(nowdate);

		    response.setContentType("ms-vnd/excel");
		    response.setHeader("Content-Disposition", "attachment;filename=contact_list_"+dateString+".xls");
		    // excel 出力
		    wb.write(response.getOutputStream());
		    wb.close();
		    response.getOutputStream().close();
		}catch (Exception e) {
			logger.error(e.getMessage());

		}
	}
}