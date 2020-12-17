package jp.co.info.ais.ops.service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.info.ais.ops.domain.Setting;
import jp.co.info.ais.ops.domain.Site;
import jp.co.info.ais.ops.mapper.oracle.SettingOracleMapper;
import jp.co.info.ais.ops.mapper.postgre.EventMapper;
import jp.co.info.ais.ops.mapper.postgre.SettingMapper;
@Service
public class SettingService {

	private static final Logger logger = LogManager.getLogger(SettingService.class);

	private static final String EVENT_KIND = "4";
	private static final String COMMENTS = "操作ユーザー： ";
	private static final String FIXED_STATUS_NON = "0";
	@Autowired
	EventMapper eventMapper;

	@Autowired
	SettingOracleMapper settingOracleMapper;

	@Autowired
	SettingMapper settingMapper;

	public List<Setting> settingList() throws Exception {
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
		return settingMapper.deleteCustomerno(customerno);
	}

	public int updateStatus(Setting setting) throws Exception  {
		int result = 0;
		result = settingMapper.updateStatus(setting);

		if(result > 0) {
			setting.setEventkind(EVENT_KIND);
			eventUpdate(setting);
		}
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

	public int eventUpdate(Setting setting) throws Exception {
		Setting kindValue = eventMapper.eventKind(setting);
		String upoaname = setting.getUpoaname();
		setting.setComments(COMMENTS+upoaname);
		setting.setEventname(kindValue.getEventname());
		setting.setEventstatus(FIXED_STATUS_NON);
		return eventMapper.updateEvent(setting);
	}

}
