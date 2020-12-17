package jp.co.info.ais.ops.mapper.oracle;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import jp.co.info.ais.ops.domain.UserMaster;

@Repository
@Mapper
public interface UserMasterOracleMapper {

	public List<UserMaster> usermasterList()throws Exception;


	/**
	 * 詳細設定ユーザー選択
	 *
	 * @return
	 * @throws Exception
	 */
	public List<UserMaster> getUserList()throws Exception;

}
