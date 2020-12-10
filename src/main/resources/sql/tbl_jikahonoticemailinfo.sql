-- TABLE: tbl_jikahonoticemailinfo

DROP TABLE IF EXISTS tbl_jikahonoticemailinfo;

CREATE TABLE tbl_jikahonoticemailinfo
(
   mailid character varying(10) NOT NULL,
   noticemailstatus character varying(1) NOT NULL,
   customerno character varying(10),
   customer character varying(48),
   begintimefrom timestamp without time zone,
   begintimeto timestamp without time zone,
   CONSTRAINT tbl_jikahonoticemailinfo_pkey PRIMARY KEY (mailid)
)
TABLESPACE public
;

COMMENT ON TABLE tbl_jikahonoticemailinfo
    IS '自家補お知らせメール情報';

COMMENT ON COLUMN tbl_jikahonoticemailinfo.mailid
    IS 'メールID';

COMMENT ON COLUMN tbl_jikahonoticemailinfo.noticemailstatus
    IS 'お知らせメール処理状態/0:未処理 1:処理済み 2:開始時刻超過';

COMMENT ON COLUMN tbl_jikahonoticemailinfo.customerno
    IS 'お客様番号';

COMMENT ON COLUMN tbl_jikahonoticemailinfo.customer
    IS '施設名称';

COMMENT ON COLUMN tbl_jikahonoticemailinfo.begintimefrom
    IS '自家補使用開始時刻(From)';

COMMENT ON COLUMN tbl_jikahonoticemailinfo.begintimeto
    IS '自家補使用開始時刻(To)';



