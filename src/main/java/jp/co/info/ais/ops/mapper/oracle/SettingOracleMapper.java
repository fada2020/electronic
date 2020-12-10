package jp.co.info.ais.ops.mapper.oracle;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import jp.co.info.ais.ops.domain.Setting;
import jp.co.info.ais.ops.domain.Site;

@Repository
@Mapper
public interface SettingOracleMapper {

	public List<Setting> settingList(@Param("settings") List<Setting> settingList) throws Exception;

	public int deleteCustomer(String customerno) throws Exception;

	public int updateStatus(Setting setting)throws Exception;

	/*
	 * 詳細設定サイトリスト取得
	 */
	public List<Site> getSiteList() throws Exception;



}
