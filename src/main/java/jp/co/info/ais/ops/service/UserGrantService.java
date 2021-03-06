package jp.co.info.ais.ops.service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.info.ais.ops.domain.UserMaster;
import jp.co.info.ais.ops.mapper.oracle.UserMasterOracleMapper;
import jp.co.info.ais.ops.mapper.postgre.UserGrantMapper;

@Service
public class UserGrantService {
	private static final Logger logger = LogManager.getLogger(UserGrantService.class);

	@Autowired
	UserMasterOracleMapper usermasteroracleMapper;

	@Autowired
	UserGrantMapper userGrantMapper;

	//ユーザー権限取得
	public String selectGrantInfo(String userid) throws Exception {
		return userGrantMapper.selectGrantInfo(userid);
	}

	public List<UserMaster> usermasterList()  throws Exception {
		List<UserMaster>userMasterList = null;
		List<UserMaster> enableList = null;
		/*ユーザーのリスト*/
		userMasterList = usermasteroracleMapper.usermasterList();
		/*ユーザーの権限リスト*/
		enableList = userGrantMapper.usermasterList(userMasterList);
		/*ユーザーのリストに権限情報を設定*/
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

	/**
	 * 詳細設定ユーザー選択
	 *
	 * @return List<UserMaster>
	 * @throws Exception
	 */
	public List<UserMaster> getUserList()  throws Exception {
		List<UserMaster>userMasterList = null;
		/*ユーザーのリスト*/
		userMasterList = usermasteroracleMapper.getUserList();
		return userMasterList;
	}

	public int saveEnable(UserMaster master)throws Exception {
		return userGrantMapper.saveEnable(master);

	}

	public List<String> enableList() throws Exception {
		return userGrantMapper.enableList();
	}

	public int updateEnable(UserMaster master)throws Exception {
		return userGrantMapper.updateEnable(master);
	}

	public int allSaveEnable(List<UserMaster> list) throws Exception {
		return userGrantMapper.allSaveEnable(list);
	}
}