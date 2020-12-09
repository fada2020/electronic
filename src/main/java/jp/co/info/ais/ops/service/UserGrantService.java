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
		logger.debug("MasterListService START");
		List<UserMaster>userMasterList = null;
		List<UserMaster> enableList = null;
		/*ユーザーのリスト*/
		userMasterList = usermasteroracleMapper.usermasterList();
		System.out.println(userMasterList);
		/*ユーザーの権限リスト*/
		enableList = userGrantMapper.usermasterList(userMasterList);
		System.out.println(enableList);
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
		System.out.println(userMasterList);
		return userMasterList;
	}


	/**
	 * 詳細設定ユーザー選択
	 *
	 * @return
	 * @throws Exception
	 */
	public List<UserMaster> getUserList()  throws Exception {
		List<UserMaster>userMasterList = null;
		/*ユーザーのリスト*/
		userMasterList = usermasteroracleMapper.getUserList();
		return userMasterList;
	}

	public int saveEnable(List<UserMaster> list)throws Exception {
		List<String> userList = null;
		userList = userGrantMapper.countList(list);



		return userGrantMapper.saveEnable(list);
	}


}