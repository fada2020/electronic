package jp.co.info.ais.ops.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import jp.co.info.ais.ops.domain.EventAndId;
import jp.co.info.ais.ops.domain.EventAndMail;
import jp.co.info.ais.ops.domain.EventList;
import jp.co.info.ais.ops.domain.MailList;

public interface EventService {

	public List<EventList> selectEventList(HttpServletRequest request, EventList params) throws Exception;

	public int selectEventTotalCount(HttpServletRequest request, EventAndId param) throws Exception;

	public List<EventList> selectSearchData(Map<String, Object> paraMap) throws Exception;

	public List<MailList> selectMailList(HttpServletRequest request, MailList param) throws Exception;

	public List<EventAndMail> selectEventAndMail(HttpServletRequest request, EventAndMail params) throws Exception;

	public List<EventAndId> selectEventAndId(HttpServletRequest request, EventAndId params) throws Exception;
}
