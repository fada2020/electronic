-- TABLE: tbl_usergrant

DROP TABLE IF EXISTS tbl_usergrant;

CREATE TABLE tbl_usergrant
(
   loginuser character varying(15) NOT NULL,
   enable character varying(1) NOT NULL DEFAULT '0',
   CONSTRAINT tbl_usergrant_pkey PRIMARY KEY (loginuser)
)
TABLESPACE public
;

COMMENT ON TABLE tbl_usergrant
    IS 'ユーザー権限';

COMMENT ON COLUMN tbl_usergrant.loginuser
    IS 'ログインユーザー（アカウント）';

COMMENT ON COLUMN tbl_usergrant.enable
    IS '機能許可設定/0:機能禁止 1:参照のみ許可 3:参照・変更許可';



