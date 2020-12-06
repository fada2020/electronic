package jp.co.info.ais.ops.service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.info.ais.ops.domain.UserMaster;
import jp.co.info.ais.ops.mapper.oracle.UserMasterOracleMapper;
import jp.co.info.ais.ops.mapper.postgre.UserMasterMapper;

@Service
public class UserMasterService {
	private static final Logger logger = LogManager.getLogger(EventService.class);

	@Autowired
	UserMasterOracleMapper usermasteroracleMapper;

	@Autowired
	UserMasterMapper usermasterMapper;

	public List<UserMaster> usermasterList()  throws Exception {
		logger.debug("MasterListService START");
		List<UserMaster>userMasterList = null;
		List<UserMaster> enableList = null;
		userMasterList = usermasteroracleMapper.usermasterList();
		enableList = usermasterMapper.usermasterList(userMasterList);
		if(!userMasterList.isEmpty()&&!enableList.isEmpty()) {
			for(UserMaster user : userMasterList) {
				for(UserMaster enable : enableList) {
					if(user.getLoginuser().equals(enable.getLoginuser())) {
						user.setEnable(enable.getEnable());
					}

				}

			}
		}
		return userMasterList;
	}

}
