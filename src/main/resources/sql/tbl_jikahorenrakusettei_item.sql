-- TABLE: tbl_jikahorenrakusettei_item

DROP TABLE IF EXISTS tbl_jikahorenrakusettei_item;

CREATE TABLE tbl_jikahorenrakusettei_item
(
   customerno character varying(10) NOT NULL,
   type character varying(1) NOT NULL,
   position character varying(1) NOT NULL,
   sort integer,
   enzansicd character varying(1),
   rightparenthesis character varying(1),
   customercd character varying(5),
   tagNo character varying(10),
   tagstatus character varying(1),
   leftparenthesis character varying(1),
   value real,
   CONSTRAINT tbl_jikahorenrakusettei_item_pkey PRIMARY KEY (customerno,type,position)
)
TABLESPACE public
;

COMMENT ON TABLE tbl_jikahorenrakusettei_item
    IS '自家補連絡設定（開始・終了条件）';

COMMENT ON COLUMN tbl_jikahorenrakusettei_item.customerno
    IS 'お客様番号/半角英数字';

COMMENT ON COLUMN tbl_jikahorenrakusettei_item.type
    IS 'タグ種別/1:開始 2:終了(接点) 3:終了(アナログ)';

COMMENT ON COLUMN tbl_jikahorenrakusettei_item.position
    IS 'タグ位置/A～J,Z';

COMMENT ON COLUMN tbl_jikahorenrakusettei_item.sort
    IS 'タグ順';

COMMENT ON COLUMN tbl_jikahorenrakusettei_item.enzansicd
    IS '演算子コード/0:なし 1:AND 2:OR';

COMMENT ON COLUMN tbl_jikahorenrakusettei_item.rightparenthesis
    IS '右括弧';

COMMENT ON COLUMN tbl_jikahorenrakusettei_item.customercd
    IS '端末コード/顧客(端末)コード';

COMMENT ON COLUMN tbl_jikahorenrakusettei_item.tagNo
    IS 'タグNo/タグ（接点(DiXX) or アナログ(Aixx)）';

COMMENT ON COLUMN tbl_jikahorenrakusettei_item.tagstatus
    IS 'タグ状態/0:OFF 1:ON';

COMMENT ON COLUMN tbl_jikahorenrakusettei_item.leftparenthesis
    IS '左括弧';

COMMENT ON COLUMN tbl_jikahorenrakusettei_item.value
    IS '閾値/type=3の時のみ使用';



