-- TABLE: tbl_jikahorenrakusettei_sample

DROP TABLE IF EXISTS tbl_jikahorenrakusettei_sample;

CREATE TABLE tbl_jikahorenrakusettei_sample
(
   samplekind character varying(2) NOT NULL,
   voicecycl number NOT NULL,
   voicecnt number,
   subject text,
   mailtext text,
   CONSTRAINT tbl_jikahorenrakusettei_sample_pkey PRIMARY KEY (samplekind)
)
TABLESPACE jikaho
;

ALTER TABLE tbl_jikahorenrakusettei_sample
    OWNER to jikaho;

COMMENT ON TABLE tbl_jikahorenrakusettei_sample
    IS '自家補連絡設定(サンプル)';

COMMENT ON COLUMN tbl_jikahorenrakusettei_sample.samplekind
    IS 'サンプル区分';

COMMENT ON COLUMN tbl_jikahorenrakusettei_sample.voicecycl
    IS '電話連絡リトライ間隔/0:未処理 1:処理済み 2:開始時刻超過';

COMMENT ON COLUMN tbl_jikahorenrakusettei_sample.voicecnt
    IS '電話連絡リトライ回数';

COMMENT ON COLUMN tbl_jikahorenrakusettei_sample.subject
    IS 'メールタイトル';

COMMENT ON COLUMN tbl_jikahorenrakusettei_sample.mailtext
    IS 'メール本文';



