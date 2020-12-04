package jp.co.info.ais.ops.service;

import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.info.ais.ops.domain.EventList;
import jp.co.info.ais.ops.mapper.postgre.EventMapper;

@Service
public class EventService {
	private static final Logger logger = LogManager.getLogger(EventService.class);

    @Autowired
	EventMapper eventMapper;

	/**
	 *　Eventリスト取得を依頼
	 * @param HaspMap
	 * @return　List
	 * @throws Exception
	 */
	public List<EventList> selectEventList(Map<String, Object> params) throws Exception {
		logger.debug("EventService START");
		return eventMapper.selectEventList(params);
	}

}
