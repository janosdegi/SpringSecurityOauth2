

-------------------------------------------------
-- Create Table public.verificationtoken
--------------------------------------------------
Create  table public.verificationtoken (
    id                          INTEGER         NOT NULL  ,
    token                       VARCHAR(100)         NOT NULL  ,
    username                     VARCHAR(100)                NOT NULL ,
    expiritydate                TIMESTAMP )
with(oids=false);

--------------------------------------------------
-- Create Primary Key verificationtoken_pkey
--------------------------------------------------
alter table public.verificationtoken
	add constraint verificationtoken_pkey
	Primary Key (id);
