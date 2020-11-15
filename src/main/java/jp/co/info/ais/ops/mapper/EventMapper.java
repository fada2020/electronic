package jp.co.info.ais.ops.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import jp.co.info.ais.ops.domain.EventAndId;
import jp.co.info.ais.ops.domain.EventAndMail;
import jp.co.info.ais.ops.domain.EventList;
import jp.co.info.ais.ops.domain.MailList;

@Repository
@Mapper
public interface EventMapper {

	List<EventList> selectEventList() throws Exception;

	int selectEventTotalCount() throws Exception;

	List<EventList> selectSearchData(Map<String, Object> paraMap) throws Exception;

	List<MailList> selectMailList() throws Exception;

	List<EventAndMail> selectEventAndMail()  throws Exception;

	List<EventAndId> selectEventAndId()  throws Exception;

}
