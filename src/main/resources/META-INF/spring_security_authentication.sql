
drop TABLE IF EXISTS public.user_role;
drop TABLE IF EXISTS public.role_privilege;
drop TABLE IF EXISTS public.user;
drop TABLE IF EXISTS public.role;
drop TABLE IF EXISTS public.privilege;

--------------------------------------------------
-- Create Table public.privilege
--------------------------------------------------
create table public.privilege (
    id          INT4            NOT NULL  ,
    name        VARCHAR(45)     NOT NULL
);

alter table public.privilege
    add constraint privilege_pkey
Primary Key (id);

--------------------------------------------------
-- Create Table public.user
--------------------------------------------------
-- create table public.user (
--     id              INT4            NOT NULL  ,
--     email           VARCHAR(45)     NOT NULL  ,
--     password        VARCHAR(100)    NOT NULL  ,
--     enabled         BOOL            NOT NULL  Default true
-- )
-- with(oids=false);

create table public.user (
    username           VARCHAR(45)     NOT NULL  ,
    password        VARCHAR(100)    NOT NULL  ,
    enabled         BOOL            NOT NULL  Default true
)
with(oids=false);

alter table public.user
    add constraint user_pkey
Primary Key (username);

--------------------------------------------------
-- Create Table public.role
--------------------------------------------------
create table public.role (
    id          INT4            NOT NULL  ,
    name        VARCHAR(45)     NOT NULL
)
with(oids=false);

alter table public.role
    add constraint role_pkey
Primary Key (id);

--------------------------------------------------
-- Create Table public.user_role
--------------------------------------------------
create table public.user_role (
    username         VARCHAR(45)        NOT NULL  ,
    role_id         INT4        NOT NULL
)
with(oids=false);

-- FK user_id
alter table public.user_role
    add constraint fk_u_r_user
foreign key (username)
references public.user(username);

-- FK role_id
alter table public.user_role
    add constraint fk_u_r_role
foreign key (role_id)
references public.role(id);


--------------------------------------------------
-- Create Table public.role_privilege
--------------------------------------------------
create table public.role_privilege (
    role_id             INT4        NOT NULL,
    privilege_id        INT4        NOT NULL
)
with(oids=false);

-- FK user_id
alter table public.role_privilege
    add constraint fk_r_p_user
foreign key (role_id)
references public.role(id);

-- FK privilege_id
alter table public.role_privilege
    add constraint fk_r_p_privilege
foreign key (privilege_id)
references public.privilege(id);

---------------------------------------------------
-- INSERT STATEMENTS
---------------------------------------------------

insert into public.privilege (id, name) values (1, 'READ_PRIVILEGE');
insert into public.privilege (id, name) values (2, 'WRITE_PRIVILEGE');

insert into public.role (id, name) values (1, 'ROLE_ADMIN');
insert into public.role (id, name) values (2, 'ROLE_USER');

insert into public.role_privilege (role_id, privilege_id) values (1, 1);
insert into public.role_privilege (role_id, privilege_id) values (1, 2);
insert into public.role_privilege (role_id, privilege_id) values (2, 1);

-- password: 1234
insert into public.user (username, password) values ('tdj', '$2a$12$zZ9F6ZXgTQG6FxRAPpvl4u0.16u4FbktvSPrC6Vpb97FrBOYc/BhG');


insert into public.user_role (username, role_id) values ('tdj', 2);
