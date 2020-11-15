package jp.co.info.ais.ops.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.info.ais.ops.domain.EventAndId;
import jp.co.info.ais.ops.domain.EventAndMail;
import jp.co.info.ais.ops.domain.EventList;
import jp.co.info.ais.ops.domain.MailList;
import jp.co.info.ais.ops.mapper.EventMapper;

@Service
public class EventServiceImpl implements EventService {

	@Autowired
	private EventMapper eventMapper;

	public int selectEventTotalCount(HttpServletRequest request, EventAndId param) throws Exception {
		return eventMapper.selectEventTotalCount();
	}

	public List<EventList> selectEventList(HttpServletRequest request, EventList param) throws Exception {
		return eventMapper.selectEventList();
	}
	public List<EventList> selectSearchData(Map<String, Object> paraMap) throws Exception {
		return eventMapper.selectSearchData(paraMap);
	}
	public List<MailList> selectMailList(HttpServletRequest request, MailList param) throws Exception {
		return eventMapper.selectMailList();
	}

	public List<EventAndMail> selectEventAndMail(HttpServletRequest request, EventAndMail param) throws Exception {
		return eventMapper.selectEventAndMail();

	}
	public List<EventAndId> selectEventAndId(HttpServletRequest request, EventAndId param) throws Exception {
		return eventMapper.selectEventAndId();
	}
}
