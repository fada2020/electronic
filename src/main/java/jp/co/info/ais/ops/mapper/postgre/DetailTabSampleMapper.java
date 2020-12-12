package jp.co.info.ais.ops.mapper.postgre;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import jp.co.info.ais.ops.domain.DetailTabSample;

@Repository
@Mapper
public interface DetailTabSampleMapper {

	DetailTabSample getDetailTabSamle(String id) throws Exception;

}
