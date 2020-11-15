package jp.co.info.ais.ops.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jp.co.info.ais.ops.domain.EventAndId;
import jp.co.info.ais.ops.domain.EventList;
import jp.co.info.ais.ops.domain.MailList;
import jp.co.info.ais.ops.service.EventService;


@Controller
@RequestMapping("/event")
public class EventController {

	//エラーを表すための宣言
	private static final Logger logger = LogManager.getLogger(EventController.class);

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
	public String index(EventAndId param, Model model, HttpServletRequest request) throws Exception  {

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

			List<EventList> list = null;
			list = eventService.selectSearchData(paraMap);

			model.addAttribute("fromDate", fromDate);
			model.addAttribute("toDate", toDate);
			model.addAttribute("list", list);

		}catch (Exception e) {
			System.out.println(e.getMessage());
			//logger.debug(e.getMessage());
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
	public String search(EventAndId param, Model model, HttpServletRequest request) throws Exception  {

		logger.debug("イベント一覧画面===開始");
		try {
			//パラメータ取得
			String fromDate = request.getParameter("fromDateTime");
			String toDate = request.getParameter("toDateTime");
			String chkStatus = request.getParameter("chkStatus");
			String chkKind = request.getParameter("chkKind");

			//パラメータ格納
			Map<String, Object> paraMap = new HashMap<>();
			paraMap.put("fromDate", fromDate);
			paraMap.put("toDate", toDate);

			paraMap.put("statusOK", 'Y');
			paraMap.put("statusNG", 'N');
			paraMap.put("kindMail", 'Y');
			paraMap.put("kindTel", 'N');
			paraMap.put("kindChk", 'N');
			paraMap.put("kindErr", 'N');


//			List<String> statusList = new ArrayList<String>();
//		    String[] statusArr = chkStatus.split(",", 0);
//		    for (int i = 0 ; i < statusArr.length ; i++){
//				statusList.add(statusArr[i]);
//		    }
//			paraMap.put("status", statusList);

//			List<String> kindList = new ArrayList<String>();
//		    String[] kindArr = chkKind.split(",", 0);
//		    for (int i = 0 ; i < kindArr.length ; i++){
//		    	kindList.add(kindArr[i]);
//		    }
//			paraMap.put("kinds", kindList);

			List<EventList> list = null;
			list = eventService.selectSearchData(paraMap);
			model.addAttribute("fromDate", fromDate);
			model.addAttribute("toDate", toDate);
			model.addAttribute("list", list);

		}catch (Exception e) {
			System.out.println(e.getMessage());
			//logger.debug(e.getMessage());
		}
		//戻り値
		return "event_list";
	}



	@RequestMapping(value = "excelDown",method = { RequestMethod.GET, RequestMethod.POST })
	public void ExcelDown(HttpServletRequest request,EventList params, HttpServletResponse response) throws Exception  {
		try {
			List<EventList> list = eventService.selectEventList(request, params);
			System.out.println(list);

			// ワークブック生成
		    Workbook wb = new HSSFWorkbook();
		    Sheet sheet = wb.createSheet("メールリスト");
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
		        row = sheet.createRow(rowNo++);
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
		        cell.setCellValue(eventlist.getStatus());
		        cell = row.createCell(5);
		        cell.setCellStyle(bodyStyle);
		        cell.setCellValue(eventlist.getUpoaname());
		        cell = row.createCell(6);
		    }

		    // コンテンツタイプとファイル名指定
		    response.setContentType("ms-vnd/excel");
		    response.setHeader("Content-Disposition", "attachment;filename=mail_list.xls");


		    // excel 出力
		    wb.write(response.getOutputStream());
		    wb.close();


		}catch (Exception e) {
			logger.debug(e.getMessage());
		}


	}


	@RequestMapping(value = "mailcheck",method = { RequestMethod.GET, RequestMethod.POST })
	public void MailBoardCheck(HttpServletRequest request,MailList param, HttpServletResponse response,Model model) throws Exception  {
		try {
			String mailid = request.getParameter("mailid");

			List<MailList> list = eventService.selectMailList(request, param);

			model.addAttribute("list", list);
			model.addAttribute("mailid", mailid);

			System.out.println(list);
			System.out.println(mailid);
			response.setContentType(mailid);

		}catch (Exception e) {
			logger.debug(e.getMessage());
		}


	}

}



