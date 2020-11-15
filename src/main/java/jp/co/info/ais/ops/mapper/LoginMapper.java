package jp.co.info.ais.ops.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LoginMapper {

	//データベースからIDを持って来る
	int selectId(String id);

	//Login selectLogin(String id, String pass);

}
