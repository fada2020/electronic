package jp.co.info.ais.ops.service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.info.ais.ops.domain.Contact;
import jp.co.info.ais.ops.mapper.postgre.ContactMapper;

@Service
public class ContactService {
	private static final Logger logger = LogManager.getLogger(ContactService.class);

	@Autowired
	ContactMapper contactMapper;

	/*連絡先リスト表示*/
	public List<Contact> contactList() throws Exception {
		return contactMapper.contactList();
	}

	/*連絡先リストオブジェクト編集*/
	public int updateContact(Contact contact) throws Exception{
		return contactMapper.updateContact(contact);
	}

	/*連絡先リストオブジェクト新規*/
	public int addContact(Contact contact) throws Exception{
		return contactMapper.addContact(contact);
	}

	/*連絡先リストオブジェクト削除*/
	public int deleteContact(int contactcd) throws Exception{
		return contactMapper.deleteContact(contactcd);
	}

	/*連絡先リスト表示(詳細設定)*/
	public List<Contact> contactAllList() throws Exception {
		return contactMapper.contactAllList();
	}


}
