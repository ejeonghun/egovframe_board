CREATE OR REPLACE VIEW TEST.comvnusermaster(
esntl_id character(20),
user_id character varying(60) default '',
[password] character varying(600),
user_nm character varying(180),
user_zip character varying(18),
user_adres character varying(300),
user_email character varying(150),
group_id character varying(1073741823),
user_se character varying(3),
orgnzt_id character varying(1073741823))
    AS 
select [test.LETTNGNRLMBER].[ESNTL_ID], [test.LETTNGNRLMBER].[MBER_ID], [test.LETTNGNRLMBER].[PASSWORD],  cast( cast([test.LETTNGNRLMBER].[MBER_NM] as varchar(180)) as varchar(180)), [test.LETTNGNRLMBER].[ZIP], [test.LETTNGNRLMBER].[ADRES], [test.LETTNGNRLMBER].[MBER_EMAIL_ADRES], _utf8' ' collate utf8_bin,  cast(_utf8'GNR' collate utf8_bin as varchar(3)), _utf8' ' collate utf8_bin from [test.LETTNGNRLMBER] [test.LETTNGNRLMBER];

