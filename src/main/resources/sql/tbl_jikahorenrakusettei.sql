-- TABLE: tbl_jikahorenrakusettei

DROP TABLE IF EXISTS tbl_jikahorenrakusettei;

CREATE TABLE tbl_jikahorenrakusettei
(
   customercd character varying(10) NOT NULL,
   customer character varying(48) NOT NULL,
   sitecd character varying(5) NOT NULL,
   jdgsw character varying(1) NOT NULL DEFAULT '0',
   status character varying(1) NOT NULL DEFAULT '0',
   endjdgsw character varying(1) NOT NULL DEFAULT '0',
   starttime timestamp without time zone,
   upoaid character varying(15),
   upoaname character varying(15),
   upoatime timestamp without time zone,
   startcontactcd  character varying(3),
   startvoicepath character varying(255),
   startvoicecycl integer,
   startvoicecnt integer,
   startsubject text,
   startmailtext text,
   endcontactcd  character varying(3),
   endvoicepath character varying(255),
   endvoicecycl integer,
   endvoicecnt integer,
   endsubject text,
   endmailtext text,
   outermailaddr text,
   intermailaddr text,
   adminuserid character varying(15),
   adminusername character varying(15),
   adminmailaddress text,
   CONSTRAINT tbl_jikahorenrakusettei_pkey PRIMARY KEY (customerno)
)
TABLESPACE jikaho
;

ALTER TABLE tbl_jikahorenrakusettei
    OWNER to jikaho;

COMMENT ON TABLE tbl_jikahorenrakusettei
    IS '自家補連絡設定';

COMMENT ON COLUMN tbl_jikahorenrakusettei.customerno
    IS 'お客様番号/半角英数字';

COMMENT ON COLUMN tbl_jikahorenrakusettei.customer
    IS '施設名称';

COMMENT ON COLUMN tbl_jikahorenrakusettei.sitecd
    IS 'サイトCD';

COMMENT ON COLUMN tbl_jikahorenrakusettei.jdgsw
    IS 'システム使用判定/0:使用しない 1:使用する';

COMMENT ON COLUMN tbl_jikahorenrakusettei.status
    IS '現況/0:未使用 1:使用中';

COMMENT ON COLUMN tbl_jikahorenrakusettei.endjdgsw
    IS '終了判定/0:終了判定しない 1:終了判定する';

COMMENT ON COLUMN tbl_jikahorenrakusettei.starttime
    IS '自家補使用開始日時/YYYYMMDDHHMMSS';

COMMENT ON COLUMN tbl_jikahorenrakusettei.upoaid
    IS '最終更新者ID/ログインユーザー（アカウント）';

COMMENT ON COLUMN tbl_jikahorenrakusettei.upoaname
    IS '最終更新者名';

COMMENT ON COLUMN tbl_jikahorenrakusettei.upoatime
    IS '最終更新日時';

COMMENT ON COLUMN tbl_jikahorenrakusettei.startcontactcd
    IS '開始連絡先CD';

COMMENT ON COLUMN tbl_jikahorenrakusettei.startvoicepath
    IS '開始電話連絡音声ファイル/音声ファイルの保存パス(ファイル名)';

COMMENT ON COLUMN tbl_jikahorenrakusettei.startvoicecycl
    IS '開始電話連絡リトライ間隔';

COMMENT ON COLUMN tbl_jikahorenrakusettei.startvoicecnt
    IS '開始電話連絡リトライ回数';

COMMENT ON COLUMN tbl_jikahorenrakusettei.startsubject
    IS '開始メールタイトル';

COMMENT ON COLUMN tbl_jikahorenrakusettei.startmailtext
    IS '開始メール本文';

COMMENT ON COLUMN tbl_jikahorenrakusettei.endcontactcd
    IS '終了連絡先CD';

COMMENT ON COLUMN tbl_jikahorenrakusettei.endvoicepath
    IS '終了電話連絡音声ファイル/音声ファイルの保存パス(ファイル名)';

COMMENT ON COLUMN tbl_jikahorenrakusettei.endvoicecycl
    IS '終了電話連絡リトライ間隔';

COMMENT ON COLUMN tbl_jikahorenrakusettei.endvoicecnt
    IS '終了電話連絡リトライ回数';

COMMENT ON COLUMN tbl_jikahorenrakusettei.endsubject
    IS '終了メールタイトル';

COMMENT ON COLUMN tbl_jikahorenrakusettei.endmailtext
    IS '終了メール本文';

COMMENT ON COLUMN tbl_jikahorenrakusettei.outermailaddr
    IS '社外展開アドレス/セミコロン(;)区切り';

COMMENT ON COLUMN tbl_jikahorenrakusettei.intermailaddr
    IS '社内展開アドレス/セミコロン(;)区切り';

COMMENT ON COLUMN tbl_jikahorenrakusettei.adminuserid
    IS '管理者ID';

COMMENT ON COLUMN tbl_jikahorenrakusettei.adminusername
    IS '管理者名';

COMMENT ON COLUMN tbl_jikahorenrakusettei.adminmailaddress
    IS '管理者メールアドレス';



