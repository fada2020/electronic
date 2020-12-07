package jp.co.info.ais.ops.mapper.postgre;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface UserGrantMapper {

	//
	String selectGrantInfo(String id)throws Exception;


}
