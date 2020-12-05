package jp.co.info.ais.ops.service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.info.ais.ops.domain.Setting;
import jp.co.info.ais.ops.mapper.postgre.SettingMapper;



@Service
public class SettingService {
	private static final Logger logger = LogManager.getLogger(EventService.class);
	@Autowired
	private SettingMapper settingMapper;
	public List<Setting> settingList() throws Exception {
		logger.debug("SettingListService START");
		return settingMapper.settingList();
	}
	public int deleteShisetsuno(String shisetsuno)throws Exception  {
		logger.debug("SettingdeleteService START");
		return settingMapper.deleteShisetsuno(shisetsuno);
	}
	public int updateStatus(Setting setting) throws Exception  {
		logger.debug("SettingUpdateStatusService START");
		return settingMapper.updateStatus(setting);
	}

}
