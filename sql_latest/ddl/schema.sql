CREATE TABLE [TEST].lettnentrprsmber(
entrprs_mber_id character varying(60) DEFAULT '' NOT NULL,
entrprs_se_code character(15),
bizrno character varying(30),
jurirno character varying(39),
cmpny_nm character varying(180) NOT NULL,
cxfc character varying(150),
zip character varying(18),
adres character varying(300),
entrprs_middle_telno character varying(12),
fxnum character varying(60),
induty_code character(15),
applcnt_nm character varying(150) NOT NULL,
applcnt_ihidnum character varying(39),
sbscrb_de datetime,
entrprs_mber_sttus character varying(45),
entrprs_mber_password character varying(600) NOT NULL,
entrprs_mber_password_hint character varying(300) NOT NULL,
entrprs_mber_password_cnsr character varying(300) NOT NULL,
group_id character(20),
detail_adres character varying(300),
entrprs_end_telno character varying(12),
area_no character varying(12),
applcnt_email_adres character varying(150),
esntl_id character(20) NOT NULL
) COLLATE utf8_bin  REUSE_OID ;

CREATE TABLE [TEST].lettnschdulinfo(
schdul_id character(20) NOT NULL,
schdul_se character(1),
schdul_dept_id character varying(60),
schdul_knd_code character varying(60),
schdul_beginde character(40),
schdul_endde character(40),
schdul_nm character varying(765),
schdul_cn character varying(7500),
schdul_place character varying(765),
schdul_ipcr_code character(1),
schdul_charger_id character varying(60),
atch_file_id character(20),
frst_regist_pnttm datetime,
frst_register_id character varying(60),
last_updt_pnttm datetime,
last_updusr_id character varying(60),
reptit_se_code character(3)
) COLLATE utf8_bin  REUSE_OID ;

CREATE TABLE [TEST].lettnroleinfo(
role_code character varying(150) DEFAULT '' NOT NULL,
role_nm character varying(180) NOT NULL,
role_pttrn character varying(900),
role_dc character varying(600),
role_ty character varying(240),
role_sort character varying(30),
role_creat_de character(60) NOT NULL
) COLLATE utf8_bin  REUSE_OID ;

CREATE TABLE [TEST].lettnbbsmasteroptn(
bbs_id character(20) DEFAULT '                    ' NOT NULL,
answer_at character(1) DEFAULT ' ' NOT NULL,
stsfdg_at character(1) DEFAULT ' ' NOT NULL,
frst_regist_pnttm datetime DEFAULT null NOT NULL,
last_updt_pnttm datetime,
frst_register_id character varying(60) DEFAULT '' NOT NULL,
last_updusr_id character varying(60)
) COLLATE utf8_bin  REUSE_OID ;

CREATE TABLE [TEST].lettccmmndetailcode(
code_id character varying(18) NOT NULL,
code character varying(45) NOT NULL,
code_nm character varying(180),
code_dc character varying(600),
use_at character(1),
frst_regist_pnttm datetime,
frst_register_id character varying(60),
last_updt_pnttm datetime,
last_updusr_id character varying(60)
) COLLATE utf8_bin  REUSE_OID ;

CREATE TABLE [TEST].lettnorgnztinfo(
orgnzt_id character(20) DEFAULT '                    ' NOT NULL,
orgnzt_nm character varying(60) NOT NULL,
orgnzt_dc character varying(300)
) COLLATE utf8_bin  REUSE_OID ;

CREATE TABLE [TEST].lettnemplyrinfo(
emplyr_id character varying(60) NOT NULL,
orgnzt_id character(20),
user_nm character varying(180) NOT NULL,
[password] character varying(600) NOT NULL,
empl_no character varying(60),
ihidnum character varying(39),
sexdstn_code character(1),
brthdy character(20),
fxnum character varying(60),
house_adres character varying(300),
password_hint character varying(300) NOT NULL,
password_cnsr character varying(300) NOT NULL,
house_end_telno character varying(12),
area_no character varying(12),
detail_adres character varying(300),
zip character varying(18),
offm_telno character varying(60),
mbtlnum character varying(60),
email_adres character varying(150),
ofcps_nm character varying(180),
house_middle_telno character varying(12),
group_id character(20),
pstinst_code character(8),
emplyr_sttus_code character varying(45) DEFAULT 'P' NOT NULL,
esntl_id character(20) NOT NULL,
crtfc_dn_value character varying(60),
sbscrb_de datetime,
[role] character varying(100) DEFAULT 'ROLE_USER'
) COLLATE utf8_bin  REUSE_OID ;

CREATE TABLE [TEST].ids(
table_name character varying(60) NOT NULL,
next_id numeric(30,0) DEFAULT 0 NOT NULL
) COLLATE utf8_bin  REUSE_OID ;

CREATE TABLE [TEST].lettnauthorrolerelate(
author_code character varying(90) NOT NULL,
role_code character varying(150) NOT NULL,
creat_dt datetime
) COLLATE utf8_bin  REUSE_OID ;

CREATE TABLE [TEST].lettnbbs(
ntt_id numeric(20,0) NOT NULL,
bbs_id character(20) NOT NULL,
ntt_no numeric(20,0),
ntt_sj character varying(6000),
ntt_cn character varying(1073741823),
answer_at character(1),
parntsctt_no numeric(10,0),
answer_lc numeric(11,0),
sort_ordr numeric(8,0),
rdcnt numeric(10,0),
use_at character(1) NOT NULL,
ntce_bgnde character(20),
ntce_endde character(20),
ntcr_id character varying(60),
ntcr_nm character varying(60),
[password] character varying(600),
atch_file_id character(20),
frst_regist_pnttm datetime,
frst_register_id character varying(60),
last_updt_pnttm datetime,
last_updusr_id character varying(60)
) COLLATE utf8_bin  REUSE_OID ;

CREATE TABLE [TEST].replies(
id bigint AUTO_INCREMENT(1,1) NOT NULL,
parent_id bigint,
post_id bigint NOT NULL,
author character varying(100) NOT NULL,
content character varying(255),
created_at timestamp DEFAULT current_timestamp,
updated_at timestamp DEFAULT null
) COLLATE utf8_bin  REUSE_OID ;

ALTER SERIAL [TEST.replies_ai_id] START WITH 27;
CREATE TABLE [TEST].lettngnrlmber(
mber_id character varying(60) DEFAULT '' NOT NULL,
[password] character varying(600) NOT NULL,
password_hint character varying(300) NOT NULL,
password_cnsr character varying(300) NOT NULL,
ihidnum character varying(39),
mber_nm character varying(150) NOT NULL,
zip character varying(18),
adres character varying(300),
area_no character varying(12),
mber_sttus character varying(45),
detail_adres character varying(300),
end_telno character varying(12),
mbtlnum character varying(60),
group_id character(20),
mber_fxnum character varying(60),
mber_email_adres character varying(150),
middle_telno character varying(12),
sbscrb_de datetime,
sexdstn_code character(1),
esntl_id character(20) NOT NULL
) COLLATE utf8_bin  REUSE_OID ;

CREATE TABLE [TEST].lettnfiledetail(
atch_file_id character(20) NOT NULL,
file_sn numeric(10,0) NOT NULL,
file_stre_cours character varying(6000) NOT NULL,
stre_file_nm character varying(765) NOT NULL,
orignl_file_nm character varying(765),
file_extsn character varying(60) NOT NULL,
file_cn character varying(1073741823),
file_size numeric(8,0)
) COLLATE utf8_bin  REUSE_OID ;

CREATE TABLE [TEST].lettccmmnclcode(
cl_code character(3) NOT NULL,
cl_code_nm character varying(180),
cl_code_dc character varying(600),
use_at character(1),
frst_regist_pnttm datetime,
frst_register_id character varying(60),
last_updt_pnttm datetime,
last_updusr_id character varying(60)
) COLLATE utf8_bin  REUSE_OID ;

CREATE TABLE [TEST].reply(
reply_id numeric(19,0) AUTO_INCREMENT(1,1) NOT NULL,
created_id character varying(100) NOT NULL,
content character varying(100),
ntt_id numeric(19,0) NOT NULL
) COLLATE utf8_bin  REUSE_OID ;

ALTER SERIAL [TEST.reply_ai_reply_id] START WITH 23;
CREATE TABLE [TEST].lettnauthorgroupinfo(
group_id character(20) DEFAULT '                    ' NOT NULL,
group_nm character varying(180) NOT NULL,
group_creat_de character(60) NOT NULL,
group_dc character varying(300)
) COLLATE utf8_bin  REUSE_OID ;

CREATE TABLE [TEST].roleinfo(
role_pttrn character varying(100),
author_code character varying(100)
) COLLATE utf8_bin  REUSE_OID ;

CREATE TABLE [TEST].lettnbbsmaster(
bbs_id character(20) NOT NULL,
bbs_nm character varying(765) NOT NULL,
bbs_intrcn character varying(7200),
bbs_ty_code character(6),
bbs_attrb_code character(6),
reply_posbl_at character(1),
file_atch_posbl_at character(1) NOT NULL,
atch_posbl_file_number numeric(2,0) NOT NULL,
atch_posbl_file_size numeric(8,0),
use_at character(1) NOT NULL,
tmplat_id character(20),
frst_register_id character varying(60) NOT NULL,
frst_regist_pnttm datetime NOT NULL,
last_updusr_id character varying(60),
last_updt_pnttm datetime
) COLLATE utf8_bin  REUSE_OID ;

CREATE TABLE [TEST].lettnfile(
atch_file_id character(20) NOT NULL,
creat_dt datetime NOT NULL,
use_at character(1)
) COLLATE utf8_bin  REUSE_OID ;

CREATE TABLE [TEST].lettccmmncode(
code_id character varying(18) NOT NULL,
code_id_nm character varying(180),
code_id_dc character varying(600),
use_at character(1),
cl_code character(3),
frst_regist_pnttm datetime,
frst_register_id character varying(60),
last_updt_pnttm datetime,
last_updusr_id character varying(60)
) COLLATE utf8_bin  REUSE_OID ;

CREATE TABLE [TEST].lettntmplatinfo(
tmplat_id character(20) DEFAULT '                    ' NOT NULL,
tmplat_nm character varying(765),
tmplat_cours character varying(6000),
use_at character(1),
tmplat_se_code character(6),
frst_register_id character varying(60),
frst_regist_pnttm datetime,
last_updusr_id character varying(60),
last_updt_pnttm datetime
) COLLATE utf8_bin  REUSE_OID ;

CREATE TABLE [TEST].lettnbbsuse(
bbs_id character(20) NOT NULL,
trget_id character(20) DEFAULT '                    ' NOT NULL,
use_at character(1) NOT NULL,
regist_se_code character(6),
frst_regist_pnttm datetime,
frst_register_id character varying(60) NOT NULL,
last_updt_pnttm datetime,
last_updusr_id character varying(60)
) COLLATE utf8_bin  REUSE_OID ;

CREATE TABLE [TEST].posts(
board_id bigint AUTO_INCREMENT(1,1) NOT NULL,
author character varying(100) NOT NULL,
created_at timestamp DEFAULT current_timestamp,
title character varying(255) NOT NULL,
content character varying(255) NOT NULL,
is_reply character(1) DEFAULT 'N',
parent_id bigint,
atch_file_id character varying(255)
) COLLATE utf8_bin  REUSE_OID ;

ALTER SERIAL [TEST.posts_ai_board_id] START WITH 24;
