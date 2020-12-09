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

	/*contactリス表示*/
	public List<Contact> contactList() throws Exception {
		logger.debug("ContactListService START");
		return contactMapper.contactList();
	}

	/*contactオブジェクト編集*/
	public int updateContact(Contact contact) throws Exception{
		logger.debug("ContactupdateService START");
		return contactMapper.updateContact(contact);
	}

	/*contactオブジェクト新規*/
	public int addContact(Contact contact) throws Exception{
		logger.debug("ContactaddService START");
		return contactMapper.addContact(contact);
	}

	/*contactオブジェクト削除*/
	public int deleteContact(int contactcd) throws Exception{
		logger.debug("ContactdeleteService START");
		return contactMapper.deleteContact(contactcd);
	}

	/*contactリス表示(詳細設定)*/
	public List<Contact> contactAllList() throws Exception {
		logger.debug("ContactListService START");
		return contactMapper.contactAllList();
	}


}
