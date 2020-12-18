package jp.co.info.ais.ops.mapper.postgre;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import jp.co.info.ais.ops.domain.Contact;

@Repository
@Mapper
public interface ContactMapper {

	/*連絡先リスト表示*/
	public List<Contact> contactList() throws Exception;

	/*連絡先リスト編集*/
	public int updateContact(Contact contact)throws Exception;

	/*連絡先リスト新規*/
	public int addContact(Contact contact)throws Exception;

	/*連絡先リスト削除*/
	public int deleteContact(int contactcd)throws Exception;

	/*連絡先リスト表示(詳細)*/
	public List<Contact> contactAllList() throws Exception;



}
