package jp.co.info.ais.ops.mapper.oracle;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import jp.co.info.ais.ops.domain.Login;

@Repository
@Mapper
public interface LoginMapper {

	//データベースからIDを持って来る
	int selectId(String id)throws Exception;
	Login selectLogin(String id, String pass)throws Exception;

}
