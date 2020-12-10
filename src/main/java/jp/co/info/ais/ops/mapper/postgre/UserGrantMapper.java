package jp.co.info.ais.ops.mapper.postgre;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import jp.co.info.ais.ops.domain.UserMaster;

@Repository
@Mapper
public interface UserGrantMapper {

	//ユーザー権限取得
	String selectGrantInfo(String id)throws Exception;

	//ユーザーリスト権限取得
	public List<UserMaster> usermasterList(@Param("users")List<UserMaster> userMasterList)throws Exception;

	int saveEnable(@Param("users")List<UserMaster> userMasterList)throws Exception;

	void deleteUserMaster(@Param("users")List<UserMaster> list)throws Exception;
}
