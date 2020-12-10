-- TABLE: tbl_jikahomaillist

DROP TABLE IF EXISTS tbl_jikahomaillist;

CREATE TABLE tbl_jikahomaillist
(
   mailid character varying(10) NOT NULL,
   mailkind character varying(1) NOT NULL,
   maildatetime timestamp without time zone,
   mailsubject text,
   mailfrom text,
   mailto text,
   mailcc text,
   mailtext text,
   CONSTRAINT tbl_jikahomaillist_pkey PRIMARY KEY (mailid,mailkind)
)
TABLESPACE jikaho
;

ALTER TABLE tbl_jikahomaillist
    OWNER to jikaho;

COMMENT ON TABLE tbl_jikahomaillist
    IS '自家補メールリスト';

COMMENT ON COLUMN tbl_jikahomaillist.mailid
    IS 'メールID';

COMMENT ON COLUMN tbl_jikahomaillist.mailkind
    IS 'メール区分/1:受信 2:送信';

COMMENT ON COLUMN tbl_jikahomaillist.maildatetime
    IS 'メール送受信日時';

COMMENT ON COLUMN tbl_jikahomaillist.mailsubject
    IS 'メール件名';

COMMENT ON COLUMN tbl_jikahomaillist.mailfrom
    IS 'メール送信者';

COMMENT ON COLUMN tbl_jikahomaillist.mailto
    IS 'メール宛先(To)';

COMMENT ON COLUMN tbl_jikahomaillist.mailcc
    IS 'メール宛先(CC)';

COMMENT ON COLUMN tbl_jikahomaillist.mailtext
    IS 'メール本文';



