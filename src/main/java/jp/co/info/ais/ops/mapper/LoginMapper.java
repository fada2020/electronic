package jp.co.info.ais.ops.mapper;

import org.apache.ibatis.annotations.Mapper;

import jp.co.info.ais.ops.domain.Login;

@Mapper
public interface LoginMapper {

	//データベースからIDを持って来る
	int selectId(String id)throws Exception;

	Login selectLogin(String id, String pass)throws Exception;

}
