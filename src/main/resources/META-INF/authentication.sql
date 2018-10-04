
drop TABLE IF EXISTS public.user_roles;
drop TABLE IF EXISTS public.users;

--------------------------------------------------
-- Create Table public.user_roles
--------------------------------------------------
Create  table public.user_roles (
    user_role_id                   INT4                NOT NULL  ,
    username                       VARCHAR(45)         NOT NULL  ,
    role                           VARCHAR(45)         NOT NULL  ) 
with(oids=false);

--------------------------------------------------
-- Create Primary Key user_roles_pkey
--------------------------------------------------
alter table public.user_roles 
	add constraint user_roles_pkey 
	Primary Key (user_role_id);

--------------------------------------------------
-- Create Table public.users
--------------------------------------------------
Create  table public.users (
    username                       VARCHAR(45)         NOT NULL  ,
    password                       VARCHAR(100)         NOT NULL  ,
    enabled                        BOOL                NOT NULL  Default true) 
with(oids=false);

--------------------------------------------------
-- Create Primary Key users_pkey
--------------------------------------------------
alter table public.users 
	add constraint users_pkey 
	Primary Key (username);

Insert into public.users values('lianna', 'MTIzNA==', '1');
Insert into public.users values('john', '$2a$08$HeT.Jhn4X.SNgpL/cVJCTujBg6kJhR3zECopEqeDQ5okISzW1IdZG', '1');
Insert into public.user_roles values(1,'lianna', 'ROLE_USER');

