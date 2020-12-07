package jp.co.info.ais.ops.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.info.ais.ops.mapper.postgre.UserGrantMapper;

@Service
public class UserGrantService {
	private static final Logger logger = LogManager.getLogger(UserGrantService.class);

	@Autowired
	UserGrantMapper userGrantMapper;

	//ユーザー権限取得
	public String selectGrantInfo(String userid) throws Exception {
		return userGrantMapper.selectGrantInfo(userid);
	}

}