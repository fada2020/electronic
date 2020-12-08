package jp.co.info.ais.ops.mapper.postgre;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import jp.co.info.ais.ops.domain.DetailTab;

@Repository
@Mapper
public interface DetailTabMapper {

	DetailTab selectInfo(String id) throws Exception;

	public int insertDetail(DetailTab detailTab)throws Exception;

	public int updateDetail(DetailTab detailTab)throws Exception;



}
