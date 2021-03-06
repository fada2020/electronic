package jp.co.info.ais.ops.controller;

import java.io.IOException;
import java.io.OutputStream;
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

import jp.co.info.ais.ops.domain.EventId;
import jp.co.info.ais.ops.domain.EventList;
import jp.co.info.ais.ops.service.EventService;

@Controller
@RequestMapping("/event")
public class EventController {

	//エラーを表すための宣言
	private static final Logger logger = LoggerFactory.getLogger(EventController.class);
	private static final String FIXED_EXCEL_FLAG = "excel";
	private static final String FIXED_SEARCH_FLAG = "search";
	private static final String FIXED_AUTO_FLAG = "auto";
	private static final String FIXED_EVENTKIND_MAIL = "1";
	private static final String FIXED_EVENTKIND_MAIL_TXT = "メール";
	private static final String FIXED_EVENTKIND_CALL = "2";
	private static final String FIXED_EVENTKIND_CALL_TXT = "電話";
	private static final String FIXED_EVENTKIND_DECIDE = "3";
	private static final String FIXED_EVENTKIND_DECIDE_TXT = "判定";
	private static final String FIXED_EVENTKIND_CONTROL = "4";
	private static final String FIXED_EVENTKIND_CONTROL_TXT = "操作";
	private static final String FIXED_STATUS_NON = "0";
	private static final String FIXED_STATUS_NON_TXT = "なし";
	private static final String FIXED_STATUS_OK = "1";
	private static final String FIXED_STATUS_OK_TXT = "正常終了";
	private static final String FIXED_STATUS_NG = "2";
	private static final String FIXED_STATUS_NG_TXT = "失敗";
	private static final int FIXED_EXCEL_RECORD_CNT = 1000;
	private static final String EXCEL_MSG_OK = "success";
	private static final String EXCEL_MSG_OVER = "over";

	@Autowired
	HttpSession session;

	@Autowired
	EventController eventController;

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
	@RequestMapping(value = "", method = { RequestMethod.POST })
	public String index(Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			List<String> statusList = new ArrayList<>();
			List<String> arrayKind = new ArrayList<>();

			//パラメータ格納
			String flag = request.getParameter("flag");
			String fromDate = request.getParameter("fromDateTime");
			String toDate = request.getParameter("toDateTime");
			String chkStatus0 = request.getParameter("chkStatus0");

			//パラメータ格納
			Map<String, Object> paraMap = new HashMap<>();

			if(flag == null) {
				//戻り値設定
				paraMap.put("fromDate", null);
				paraMap.put("toDate", null);
				paraMap.put("status", null);
				paraMap.put("kinds", null);
				paraMap.put("autoFlag", "N");

				//戻り値設定
				model.addAttribute("flag", FIXED_SEARCH_FLAG);
				model.addAttribute("fromDate", null);
				model.addAttribute("toDate", null);
				model.addAttribute("status1Check", true);
				model.addAttribute("status2Check", true);
				model.addAttribute("kind1Check", true);
				model.addAttribute("kind2Check", true);
				model.addAttribute("kind3Check", true);

			}else {
				if(!chkStatus0.isEmpty()) {
					statusList.add(chkStatus0);
				}
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
				if(!chkStatus0.isEmpty()) {
					statusList.add(chkStatus0);
				}

				paraMap.put("fromDate", fromDate);
				paraMap.put("toDate", toDate);
				if(statusList.size() > 0) {
					paraMap.put("status", statusList);
				}else {
					paraMap.put("status", null);
				}
				if(arrayKind.size() > 0) {
					paraMap.put("kinds", arrayKind);
				}else {
					paraMap.put("kinds", null);
				}
				if(flag.equals(FIXED_EXCEL_FLAG)) {
					//データ取得
					List<EventList> list = null;
					list = eventService.selectEventList(paraMap);
					boolean ref = ExcelDown(list, response);
					if(!ref) {
						model.addAttribute("excel_download_error", EXCEL_MSG_OVER);
					}else {
						model.addAttribute("excel_download_error", EXCEL_MSG_OK);
					}
				}

			}

		} catch (Exception e) {
			logger.error(e.getMessage());
		}

		//戻り値
		return "event_list";

	}


	/**
	 * イベントリスト初期表示
	 * @param EventId eventid
	 * @return List<EventList> list
	 */
	@ResponseBody
	@PostMapping("/getListAuto")
	public List<EventList> ajaxGetList(@RequestBody EventId eventid) throws JsonMappingException, IOException {
		List<EventList> list = new ArrayList<EventList>();

		try {
			List<String> statusList = new ArrayList<>();
			List<String> arrayKind = new ArrayList<>();

			//パラメータ設定
			String flag = eventid.getFlag();
			String fromDate = eventid.getFromDateTime();
			String toDate = eventid.getToDateTime();
			String chkStatus0 = eventid.getChkStatus0();
			String chkStatus1 = eventid.getChkStatus1();
			String chkStatus2 = eventid.getChkStatus2();
			String chkKind1 = eventid.getChkKind1();
			String chkKind2 = eventid.getChkKind2();
			String chkKind3 = eventid.getChkKind3();

			if (!chkStatus0.isEmpty()) {
				statusList.add(chkStatus0);
			}
			if (!chkStatus1.isEmpty()) {
				statusList.add(chkStatus1);
			}
			if (!chkStatus2.isEmpty()) {
				statusList.add(chkStatus2);
			}
			if (!chkKind1.isEmpty()) {
				arrayKind.add(chkKind1);
			}
			if (!chkKind2.isEmpty()) {
				arrayKind.add(chkKind2);
			}
			if (!chkKind3.isEmpty()) {
				arrayKind.add(chkKind3);
			}

			//パラメータ格納
			Map<String, Object> paraMap = new HashMap<>();
			//自動更新フラグチェック
			if(flag.equals(FIXED_AUTO_FLAG)) {
				paraMap.put("autoFlag", 'Y');
			}else {
				paraMap.put("autoFlag", 'N');
			}
			paraMap.put("fromDate", fromDate);
			paraMap.put("toDate", toDate);
			if (statusList.size() > 0) {
				paraMap.put("status", statusList);
			} else {
				paraMap.put("status", null);
			}
			if (arrayKind.size() > 0) {
				paraMap.put("kinds", arrayKind);
			} else {
				paraMap.put("kinds", null);
			}

			//一覧データ取得（全データ）
			list = eventService.selectEventList(paraMap);

			//一覧の施設名とイベント名称の文字数制限
			for (EventList lengthcheck : list) {
				if (lengthcheck.getCustomer().length() > 29) {
					lengthcheck.setCustomer(lengthcheck.getCustomer().substring(0, 29) + "...");
				}
				if (lengthcheck.getEventname().length() > 20) {
					lengthcheck.setEventname(lengthcheck.getEventname().substring(0, 20) + "...");
				}
			}

		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		//戻り値
		return list;
	}

	/**
	 * Excelファイル出力
	 *
	 * @param list
	 * @param response
	 * @throws Exception
	 */
	private boolean ExcelDown(List<EventList> list, HttpServletResponse response) throws Exception {

		try {
			int listSize = list.size();
			if (listSize < 1 || listSize > FIXED_EXCEL_RECORD_CNT) {
				return false;
			} else {
				//ワークブック生成
				Workbook wb = new HSSFWorkbook();
				org.apache.poi.ss.usermodel.Sheet sheet = wb.createSheet("メールリスト");
				Row row = null;
				Cell cell = null;
				int rowNo = 0;
				//ヘッダースタイル
				CellStyle headStyle = wb.createCellStyle();

				//セルの幅
				sheet.setColumnWidth(0, 5000);
				sheet.setColumnWidth(1, 7500);
				sheet.setColumnWidth(2, 9000);
				sheet.setColumnWidth(3, 2000);
				sheet.setColumnWidth(4, 2500);
				sheet.setColumnWidth(5, 6500);
				//細いセル指定
				headStyle.setBorderTop(BorderStyle.THIN);
				headStyle.setBorderBottom(BorderStyle.THIN);
				headStyle.setBorderLeft(BorderStyle.THIN);
				headStyle.setBorderRight(BorderStyle.THIN);
				//背景色
				headStyle.setFillForegroundColor(HSSFColorPredefined.YELLOW.getIndex());
				headStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
				//データ位置をセンター
				headStyle.setAlignment(HorizontalAlignment.CENTER);

				//データ用セルスタイルの枠線指定
				CellStyle bodyStyle = wb.createCellStyle();
				bodyStyle.setBorderTop(BorderStyle.THIN);
				bodyStyle.setBorderBottom(BorderStyle.THIN);
				bodyStyle.setBorderLeft(BorderStyle.THIN);
				bodyStyle.setBorderRight(BorderStyle.THIN);

				//ヘッダー生成
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

				//データ生成
				for (EventList eventlist : list) {
					row = ((org.apache.poi.ss.usermodel.Sheet) sheet).createRow(rowNo++);
					cell = row.createCell(0);
					cell.setCellStyle(bodyStyle);
					cell.setCellValue(eventlist.getOccurtime());
					cell = row.createCell(1);
					cell.setCellStyle(bodyStyle);
					cell.setCellValue(eventlist.getCustomer());
					cell = row.createCell(2);
					cell.setCellStyle(bodyStyle);
					cell.setCellValue(eventlist.getEventname());
					cell = row.createCell(3);
					cell.setCellStyle(bodyStyle);
					if (eventlist.getEventkind().equals(FIXED_EVENTKIND_MAIL)) {
						cell.setCellValue(FIXED_EVENTKIND_MAIL_TXT);
					} else if (eventlist.getEventkind().equals(FIXED_EVENTKIND_CALL)) {
						cell.setCellValue(FIXED_EVENTKIND_CALL_TXT);
					} else if (eventlist.getEventkind().equals(FIXED_EVENTKIND_DECIDE)) {
						cell.setCellValue(FIXED_EVENTKIND_DECIDE_TXT);
					} else if (eventlist.getEventkind().equals(FIXED_EVENTKIND_CONTROL)) {
						cell.setCellValue(FIXED_EVENTKIND_CONTROL_TXT);
					} else {
						cell.setCellValue('-');
					}
					cell = row.createCell(4);
					cell.setCellStyle(bodyStyle);
					if (eventlist.getStatus().equals(FIXED_STATUS_NON)) {
						cell.setCellValue(FIXED_STATUS_NON_TXT);
					} else if (eventlist.getStatus().equals(FIXED_STATUS_OK)) {
						cell.setCellValue(FIXED_STATUS_OK_TXT);
					} else if (eventlist.getStatus().equals(FIXED_STATUS_NG)) {
						cell.setCellValue(FIXED_STATUS_NG_TXT);
					} else {
						cell.setCellValue('-');
					}
					//補足:comments
					cell = row.createCell(5);
					cell.setCellStyle(bodyStyle);
					cell.setCellValue(eventlist.getComments());
				}

				//コンテンツタイプとファイル名指定
				SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmm");
				Date nowdate = new Date();
				String dateString = formatter.format(nowdate);
				OutputStream os = null;
				os = response.getOutputStream();
				response.setContentType("ms-vnd/excel");
				response.setHeader("Content-Disposition", "attachment;filename=mail_list_" + dateString + ".xls");
				//excel 出力
				wb.write(os);
				wb.close();
				os.close();
				return true;
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			return false;
		}
	}

}
