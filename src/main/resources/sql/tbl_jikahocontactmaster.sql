-- TABLE: tbl_jikahocontactmaster

DROP TABLE IF EXISTS tbl_jikahocontactmaster;

CREATE TABLE tbl_jikahocontactmaster
(
   contactcd character varying(3) NOT NULL,
   contactName character varying(50),
   contactphoneno character varying(20),
   contactmailaddress text,
   CONSTRAINT tbl_jikahocontactmaster_pkey PRIMARY KEY (contactcd)
)
TABLESPACE jikaho
;

ALTER TABLE tbl_jikahocontactmaster
    OWNER to jikaho;

COMMENT ON TABLE tbl_jikahocontactmaster
    IS '自家補連絡先マスター';

COMMENT ON COLUMN tbl_jikahocontactmaster.contactcd
    IS '連絡先CD';

COMMENT ON COLUMN tbl_jikahocontactmaster.contactName
    IS '連絡先名称';

COMMENT ON COLUMN tbl_jikahocontactmaster.contactphoneno
    IS '連絡先電話番号';

COMMENT ON COLUMN tbl_jikahocontactmaster.contactmailaddress
    IS '連絡先メールアドレス';



