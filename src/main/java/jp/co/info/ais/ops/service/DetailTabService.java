package jp.co.info.ais.ops.service;

import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.info.ais.ops.domain.DetailTab;
import jp.co.info.ais.ops.domain.DetailTabSample;
import jp.co.info.ais.ops.mapper.oracle.SettingOracleMapper;
import jp.co.info.ais.ops.mapper.postgre.DetailTabMapper;
import jp.co.info.ais.ops.mapper.postgre.DetailTabSampleMapper;

@Service
public class DetailTabService {
	private static final Logger logger = LogManager.getLogger(DetailTabService.class);

	@Autowired
	SettingOracleMapper settingOracleMapper;

	@Autowired
    DetailTabMapper detailTabMapper;

	@Autowired
	DetailTabSampleMapper detailTabSampleMapper;


	public DetailTab selectInfo(String userid) throws Exception {
		logger.debug("DetailTabService selectInfo START");
		return detailTabMapper.selectInfo(userid);
	}


	public int insertDetail(DetailTab detailTab) throws Exception{
		logger.debug("DetailTabService insertDetail START");
		return detailTabMapper.insertDetail(detailTab);
	}

	public int updateDetail(DetailTab detailTab) throws Exception{
		logger.debug("DetailTabService updateDetail START");
		return detailTabMapper.updateDetail(detailTab);
	}

	public int insertMailAddr(Map<String, Object> params) throws Exception{
		logger.debug("DetailTabService insertMailAddr START");
		return detailTabMapper.insertMailAddr(params);
	}

	public int deleteMailList(String id) throws Exception{
		logger.debug("DetailTabService START");
		return detailTabMapper.deleteMailList(id);
	}

	/**
	 * 新規の場合、サンプルデータ取得
	 *
	 * @param kind
	 * @return
	 * @throws Exception
	 */
	public DetailTabSample getDetailTabSamle(String kind) throws Exception {
		logger.debug("ContactaddService selectInfo START");
		return detailTabSampleMapper.getDetailTabSamle(kind);
	}


	/**
	 * CustomerNo存在チェック
	 * @param id
	 * @return int id
	 * @throws Exception
	 */
	public int checkCustomerNo(String id) throws Exception {
		return detailTabMapper.checkCustomerNo(id);
	}






}
