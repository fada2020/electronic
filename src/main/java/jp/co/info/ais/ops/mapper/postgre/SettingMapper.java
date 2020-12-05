package jp.co.info.ais.ops.mapper.postgre;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import jp.co.info.ais.ops.domain.Setting;
@Repository
@Mapper
public interface SettingMapper {

	public List<Setting> settingList() throws Exception;

	public int deleteShisetsuno(String shisetsuno) throws Exception;

	public int updateStatus(Setting setting)throws Exception;
}
