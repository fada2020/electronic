package jp.co.info.ais.ops.mapper.postgre;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import jp.co.info.ais.ops.domain.EventList;
import jp.co.info.ais.ops.domain.Setting;

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

	int updateEvent(Setting setting) throws Exception;

	Setting eventKind(Setting setting) throws Exception;

}
