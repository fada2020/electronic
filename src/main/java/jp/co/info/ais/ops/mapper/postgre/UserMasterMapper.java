package jp.co.info.ais.ops.mapper.postgre;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import jp.co.info.ais.ops.domain.UserMaster;

@Repository
@Mapper
public interface UserMasterMapper {

	public List<UserMaster> usermasterList()throws Exception;

}
