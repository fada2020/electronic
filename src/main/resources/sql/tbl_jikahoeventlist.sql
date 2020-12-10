-- TABLE: tbl_jikahoeventlist

DROP TABLE IF EXISTS tbl_jikahoeventlist;

CREATE TABLE tbl_jikahoeventlist
(
   occurtime timestamp without time zone NOT NULL,
   eventid character varying(4) NOT NULL,
   customerno character varying(10) NOT NULL,
   customer character varying(48),
   status character varying(2),
   mailid character varying(10),
   comments character varying(30),
   upoaid character varying(15),
   upoaname character varying(15),
   upoatime timestamp without time zone,
   CONSTRAINT tbl_jikahoeventlist_pkey PRIMARY KEY (occurtime)
)
TABLESPACE jikaho
;

ALTER TABLE tbl_jikahoeventlist
    OWNER to jikaho;

COMMENT ON TABLE tbl_jikahoeventlist
    IS '自家補連絡イベントリスト';

COMMENT ON COLUMN tbl_jikahoeventlist.occurtime
    IS '発生日時';

COMMENT ON COLUMN tbl_jikahoeventlist.eventid
    IS 'イベントID';

COMMENT ON COLUMN tbl_jikahoeventlist.customerno
    IS 'お客様番号';

COMMENT ON COLUMN tbl_jikahoeventlist.customer
    IS '施設名称';

COMMENT ON COLUMN tbl_jikahoeventlist.status
    IS '状態';

COMMENT ON COLUMN tbl_jikahoeventlist.mailid
    IS 'メールID';

COMMENT ON COLUMN tbl_jikahoeventlist.comments
    IS 'コメント（補足）';

COMMENT ON COLUMN tbl_jikahoeventlist.upoaid
    IS '最終更新者ID';

COMMENT ON COLUMN tbl_jikahoeventlist.upoaname
    IS '最終更新者名';

COMMENT ON COLUMN tbl_jikahoeventlist.upoatime
    IS '最終更新日時';



