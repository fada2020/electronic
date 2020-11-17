package jp.co.info.ais.ops.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor.HSSFColorPredefined;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.JsonMappingException;

import jp.co.info.ais.ops.domain.EventList;
import jp.co.info.ais.ops.service.EventService;

@Controller
@RequestMapping("/event")
public class EventController {

	//エラーを表すための宣言
	private static final Logger logger = LoggerFactory.getLogger(EventController.class);
	private static final String SEARCH_FLAG = "search";
	private static final String EXCEL_FLAG = "excel";
	private static final String FIXED_STATUS_NON = "0";
	private static final String FIXED_STATUS_NON_TXT = "なし";
	private static final String FIXED_STATUS_OK = "1";
	private static final String FIXED_STATUS_OK_TXT = "正常終了";
	private static final String FIXED_STATUS_NG = "2";
	private static final String FIXED_STATUS_NG_TXT = "失敗";
	private static final String FIXED_AUTO_RENEW_NO = "0";
	private static final String FIXED_AUTO_RENEW_YES = "1";

	@Autowired
	HttpSession session;

	@Autowired
	private EventService eventService;

	/**
	 * イベント一覧画面を出力(初期画面)
	 *
	 * @param param
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "", method = { RequestMethod.GET })
	public String index(Model model, HttpServletRequest request) throws Exception  {

		logger.debug("イベント一覧画面===開始");

		try {
			//パラメータ設定
			String fromDate = null;
			String toDate = null;
			String chkStatus = null;
			String chkKind = null;

			//パラメータ格納
			Map<String, Object> paraMap = new HashMap<>();
			paraMap.put("fromDate", fromDate);
			paraMap.put("toDate", toDate);
			paraMap.put("status", chkStatus);
			paraMap.put("kind", chkKind);

			//一覧データ取得（全データ）
			List<EventList> list = null;
			list = eventService.selectEventList(paraMap);

			//戻る値設定
			model.addAttribute("fromDate", fromDate);
			model.addAttribute("toDate", toDate);
			model.addAttribute("list", list);
			model.addAttribute("status1Check", true);
			model.addAttribute("status2Check", true);
			model.addAttribute("kind1Check", true);
			model.addAttribute("kind2Check", true);
			model.addAttribute("kind3Check", true);

		}catch (Exception e) {
			logger.error(e.getMessage());
		}

		//戻り値
		return "event_list";

	}

	/**
	 * イベント一覧画面を出力(検索結果)
	 *
	 * @param param
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "", method = { RequestMethod.POST })
	public String search(Model model, HttpServletRequest request, HttpServletResponse response) throws Exception  {

		logger.debug("イベント一覧画面===開始");
		try {
			List<String> statusList = new ArrayList<>();
			List<String> arrayKind = new ArrayList<>();

			//パラメータ取得
			String flag = request.getParameter("flag");
			String fromDate = request.getParameter("fromDateTime");
			String toDate = request.getParameter("toDateTime");
			String chkStatus1 = request.getParameter("chkStatus1");
			if(!chkStatus1.isEmpty()) {
				model.addAttribute("status1Check", true);
				statusList.add(chkStatus1);
			}else {
				model.addAttribute("status1Check", false);
			}
			String chkStatus2 = request.getParameter("chkStatus2");
			if(!chkStatus2.isEmpty()) {
				model.addAttribute("status2Check", true);
				statusList.add(chkStatus2);
			}else {
				model.addAttribute("status2Check", false);
			}
			String chkKind1 = request.getParameter("chkKind1");
			if(!chkKind1.isEmpty()) {
				model.addAttribute("kind1Check", true);
				arrayKind.add(chkKind1);
			}else {
				model.addAttribute("kind1Check", false);
			}
			String chkKind2 = request.getParameter("chkKind2");
			if(!chkKind2.isEmpty()) {
				model.addAttribute("kind2Check", true);
				arrayKind.add(chkKind2);
			}else {
				model.addAttribute("kind2Check", false);
			}
			String chkKind3 = request.getParameter("chkKind3");
			if(!chkKind3.isEmpty()) {
				model.addAttribute("kind3Check", true);
				arrayKind.add(chkKind3);
			}else {
				model.addAttribute("kind3Check", false);
			}

			//パラメータ格納
			Map<String, Object> paraMap = new HashMap<>();
			paraMap.put("fromDate", fromDate);
			paraMap.put("toDate", toDate);
			paraMap.put("status", statusList);
			paraMap.put("kinds", arrayKind);

			//データ取得
			List<EventList> list = null;
			list = eventService.selectEventList(paraMap);

			//Excelファイル出力
			if(flag.equals(EXCEL_FLAG)) {
				ExcelDown(list, response);
			}

			//戻り値
			model.addAttribute("flag", flag);
			model.addAttribute("fromDate", fromDate);
			model.addAttribute("toDate", toDate);
			model.addAttribute("list", list);

		}catch (Exception e) {
			logger.error(e.getMessage());
		}
		//戻り値
		return "event_list";
	}


	/**
	 * Excelファイル出力
	 *
	 * @param list
	 * @param response
	 * @throws Exception
	 */
	private void ExcelDown(List<EventList> list, HttpServletResponse response) throws Exception  {
		try {

			// ワークブック生成
		    Workbook wb = new HSSFWorkbook();
		    org.apache.poi.ss.usermodel.Sheet sheet = wb.createSheet("メールリスト");
		    Row row = null;
		    Cell cell = null;
		    int rowNo = 0;

		    // テブルヘッダースタイル
		    CellStyle headStyle = wb.createCellStyle();

		    //セールの幅
		    sheet.setColumnWidth(0, 5000);
		    sheet.setColumnWidth(1, 7500);
		    sheet.setColumnWidth(2, 7000);
		    sheet.setColumnWidth(3, 2000);
		    sheet.setColumnWidth(4, 2500);
		    sheet.setColumnWidth(5, 3500);

		    // 細いセール指定
		    headStyle.setBorderTop(BorderStyle.THIN);
		    headStyle.setBorderBottom(BorderStyle.THIN);
		    headStyle.setBorderLeft(BorderStyle.THIN);
		    headStyle.setBorderRight(BorderStyle.THIN);

		    // 背景色
		    headStyle.setFillForegroundColor(HSSFColorPredefined.YELLOW.getIndex());
		    headStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

		    // データセンター移動
		    headStyle.setAlignment(HorizontalAlignment.CENTER);

		    // データ用セールスタイルの枠線指定
		    CellStyle bodyStyle = wb.createCellStyle();
		    bodyStyle.setBorderTop(BorderStyle.THIN);
		    bodyStyle.setBorderBottom(BorderStyle.THIN);
		    bodyStyle.setBorderLeft(BorderStyle.THIN);
		    bodyStyle.setBorderRight(BorderStyle.THIN);

		    // ヘッダー生成
		    row = sheet.createRow(rowNo++);
		    cell = row.createCell(0);
		    cell.setCellStyle(headStyle);
		    cell.setCellValue("発生時刻");
		    cell = row.createCell(1);
		    cell.setCellStyle(headStyle);
		    cell.setCellValue("施設名");
		    cell = row.createCell(2);
		    cell.setCellStyle(headStyle);
		    cell.setCellValue("イベント名称");
		    cell = row.createCell(3);
		    cell.setCellStyle(headStyle);
		    cell.setCellValue("区分");
		    cell = row.createCell(4);
		    cell.setCellStyle(headStyle);
		    cell.setCellValue("状態");
		    cell = row.createCell(5);
		    cell.setCellStyle(headStyle);
		    cell.setCellValue("補足");

		    // データ生成
		    for(EventList eventlist : list) {
		        row = ((org.apache.poi.ss.usermodel.Sheet) sheet).createRow(rowNo++);
		        cell = row.createCell(0);
		        cell.setCellStyle(bodyStyle);
		        cell.setCellValue(eventlist.getOccurtime());
		        cell = row.createCell(1);
		        cell.setCellStyle(bodyStyle);
		        cell.setCellValue(eventlist.getCustomer());
		        cell = row.createCell(2);
		        cell.setCellStyle(bodyStyle);
		        cell.setCellValue(eventlist.getMailid());
		        cell = row.createCell(3);
		        cell.setCellStyle(bodyStyle);
		        cell.setCellValue(eventlist.getUpoaid());
		        cell = row.createCell(4);
		        cell.setCellStyle(bodyStyle);
		        if(eventlist.getStatus().equals(FIXED_STATUS_NON)) {
			        cell.setCellValue(FIXED_STATUS_NON_TXT);
		        }else if(eventlist.getStatus().equals(FIXED_STATUS_OK)) {
			        cell.setCellValue(FIXED_STATUS_OK_TXT);
		        }else if(eventlist.getStatus().equals(FIXED_STATUS_NG)) {
			        cell.setCellValue(FIXED_STATUS_NG_TXT);
		        }else {
			        cell.setCellValue('-');
		        }
		        cell = row.createCell(5);
		        cell.setCellStyle(bodyStyle);
		        cell.setCellValue(eventlist.getUpoaname());
		        cell = row.createCell(6);
		    }

		    // コンテンツタイプとファイル名指定
		    SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmm");
		    Date nowdate = new Date();
		    String dateString = formatter.format(nowdate);

		    response.setContentType("ms-vnd/excel");
		    response.setHeader("Content-Disposition", "attachment;filename=mail_list_"+dateString+".xls");

		    // excel 出力
		    wb.write(response.getOutputStream());
		    wb.close();


		}catch (Exception e) {
			logger.debug(e.getMessage());
		}
	}

    /**
     * 新規のTODOを追加する.
     * @param todo 新規投稿TODO
     * @return 更新の反映されたTODO
     */
    @ResponseBody
    @PostMapping("/getListAuto")
    public List<EventList> ajaxGetList(@RequestBody String json) throws JsonMappingException, IOException  {

    	logger.debug("ajaxGetList Start===========");
		List<EventList> list = new ArrayList<EventList>();

		try {

			JSONParser pJson = new JSONParser();
			JSONObject obj = (JSONObject)pJson.parse(json);
			List<String> statusList = new ArrayList<>();
			List<String> arrayKind = new ArrayList<>();

			//パラメータ設定
			String fromDate = (String) obj.get("fromDateTime");
			String toDate = (String) obj.get("toDateTime");
			String chkStatus1 = (String) obj.get("chkStatus1");
			String chkStatus2 = (String) obj.get("chkStatus2");
			String chkKind1 = (String) obj.get("chkKind1");
			String chkKind2 = (String) obj.get("chkKind2");
			String chkKind3 = (String) obj.get("chkKind3");

			if(!chkStatus1.isEmpty()) {
				statusList.add(chkStatus1);
			}
			if(!chkStatus2.isEmpty()) {
				statusList.add(chkStatus2);
			}
			if(!chkKind1.isEmpty()) {
				arrayKind.add(chkKind1);
			}
			if(!chkKind2.isEmpty()) {
				arrayKind.add(chkKind2);
			}
			if(!chkKind3.isEmpty()) {
				arrayKind.add(chkKind3);
			}

			//パラメータ格納
			Map<String, Object> paraMap = new HashMap<>();
			paraMap.put("fromDate", fromDate);
			paraMap.put("toDate", toDate);
			paraMap.put("status", statusList);
			paraMap.put("kinds", arrayKind);

			//一覧データ取得（全データ）
			list = eventService.selectEventList(paraMap);

		}catch (Exception e) {
			logger.error(e.getMessage());
		}
    	//戻る値
    	return list;
    }

}



