-- TABLE: tbl_jikahoeventid

DROP TABLE IF EXISTS tbl_jikahoeventid;

CREATE TABLE tbl_jikahoeventid
(
   eventid character varying(4) NOT NULL,
   eventkind character varying(2) NOT NULL,
   eventname character varying(50) NOT NULL,
   CONSTRAINT tbl_jikahoeventid_pkey PRIMARY KEY (eventid)
)
TABLESPACE public
;

COMMENT ON TABLE tbl_jikahoeventid
    IS '自家補連絡イベントID';

COMMENT ON COLUMN tbl_jikahoeventid.eventid
    IS 'イベントID';

COMMENT ON COLUMN tbl_jikahoeventid.eventkind
    IS 'イベント区分';

COMMENT ON COLUMN tbl_jikahoeventid.eventname
    IS 'イベント名称';



