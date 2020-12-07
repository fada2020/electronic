package jp.co.info.ais.ops.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.info.ais.ops.domain.DetailTab;
import jp.co.info.ais.ops.mapper.postgre.DetailTabMapper;

@Service
public class DetailTabService {
	private static final Logger logger = LogManager.getLogger(DetailTabService.class);

    @Autowired
    DetailTabMapper detailTabMapper;

	public DetailTab selectInfo(String userid) throws Exception {

		return detailTabMapper.selectInfo(userid);
	}



}
