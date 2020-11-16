package jp.co.info.ais.ops.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import jp.co.info.ais.ops.domain.EventList;

@Repository
@Mapper
public interface EventMapper {

	/**
	 * Event一覧を取得を行う
	 * @param paraMap
	 * @return
	 * @throws Exception
	 */
	List<EventList> selectEventList(Map<String, Object> paraMap) throws Exception;

}
