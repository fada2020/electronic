package jp.co.info.ais.ops.service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.info.ais.ops.domain.Setting;
import jp.co.info.ais.ops.domain.Site;
import jp.co.info.ais.ops.mapper.oracle.SettingOracleMapper;
import jp.co.info.ais.ops.mapper.postgre.SettingMapper;
@Service
public class SettingService {

	private static final Logger logger = LogManager.getLogger(SettingService.class);


	@Autowired
	SettingOracleMapper settingOracleMapper;

	@Autowired
	SettingMapper settingMapper;

	public List<Setting> settingList() throws Exception {
		logger.debug("SettingListService START");
			List<Setting>settingList = null;
			List<Setting> sitecdList = null;
			settingList = settingMapper.settingList();//postgreからのsettingList
			sitecdList = settingOracleMapper.settingList(settingList);//oracleからのsettingList
			if(!settingList.isEmpty()&&!sitecdList.isEmpty()) {
				for(Setting setting : settingList) {
					for(Setting site : sitecdList) {
						if(site.getSitecd()==setting.getSitecd()) {
							setting.setSitename(site.getSitename());//sitenameを設定
						}

					}

				}
			}
		return settingList;
	}
	public int deleteCustomerno(String customerno)throws Exception  {
		logger.debug("SettingdeleteService START");
		return settingMapper.deleteCustomerno(customerno);
	}

	public int updateStatus(Setting setting) throws Exception  {
		logger.debug("SettingUpdateStatusService START");
		return settingMapper.updateStatus(setting);
	}

	/**
	 * 詳細設定サイトリスト取得
	 *
	 * @return siteList
	 * @throws Exception
	 */
	public List<Site> getSiteList() throws Exception {
		List<Site> siteList = null;
		siteList = settingOracleMapper.getSiteList();
		return siteList;
	}



}
