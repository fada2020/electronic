package jp.co.info.ais.ops.service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.info.ais.ops.domain.UserMaster;
import jp.co.info.ais.ops.mapper.postgre.UserMasterMapper;


@Service
public class UserMasterService {
	private static final Logger logger = LogManager.getLogger(EventService.class);

	@Autowired
	private UserMasterMapper usermasterMapper;

	public List<UserMaster> usermasterList()  throws Exception {
		logger.debug("MasterListService START");
		return usermasterMapper.usermasterList();
	}

}
