package jp.co.info.ais.ops.mapper.postgre;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import jp.co.info.ais.ops.domain.Contact;

@Repository
@Mapper
public interface ContactMapper {

	/*リスト表示*/
	public List<Contact> contactList() throws Exception;

	/*contact編集*/
	public int updateContact(Contact contact)throws Exception;

	/*contact新規*/
	public int addContact(Contact contact)throws Exception;

	/*contact削除*/
	public int deleteContact(int contactcd)throws Exception;

}
