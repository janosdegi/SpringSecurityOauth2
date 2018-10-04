create table public.oauth_client_details (
  client_id VARCHAR(256) PRIMARY KEY,
  resource_ids VARCHAR(256),
  client_secret VARCHAR(256),
  scope VARCHAR(256),
  authorized_grant_types VARCHAR(256),
  web_server_redirect_uri VARCHAR(256),
  authorities VARCHAR(256),
  access_token_validity INT4,
  refresh_token_validity INT4,
  additional_information VARCHAR(4096),
  autoapprove VARCHAR(256)
);

create table public.oauth_client_token (
  token_id VARCHAR(256),
  token BYTEA,
  authentication_id VARCHAR(256),
  user_name VARCHAR(256),
  client_id VARCHAR(256)
);

create table public.oauth_access_token (
  token_id VARCHAR(256),
  token BYTEA,
  authentication_id VARCHAR(256),
  user_name VARCHAR(256),
  client_id VARCHAR(256),
  authentication BYTEA,
  refresh_token VARCHAR(256)
);

create table public.oauth_refresh_token (
  token_id VARCHAR(256),
  token BYTEA,
  authentication BYTEA
);

create table public.oauth_code (
  code VARCHAR(256), authentication BYTEA
);

--BYTEA
--varying(2000)



Insert into oauth_client_details values('test_oauth2-client', 'rest_api', NULL, 'trust,read,write', 'client_credentials,authorization_code,implicit,password,refresh_token', NULL, 'ROLE_USER,ROLE_TRUSTED_CLIENT', 600, 6000, NULL, NULL);
Insert into oauth_client_details values('admin', 'rest_api', NULL, 'trust,read,write', 'client_credentials,authorization_code,implicit,password,refresh_token', NULL, 'ROLE_USER,ROLE_TRUSTED_CLIENT', 600, 6000, NULL, NULL);
Insert into oauth_client_details values('testuser', 'rest_api', NULL, 'trust,read,write', 'client_credentials,authorization_code,implicit,password,refresh_token', NULL, 'ROLE_USER,ROLE_TRUSTED_CLIENT', 600, 6000, NULL, NULL);
Insert into oauth_client_details values('jdbctestuser', 'rest_api', 'jdbctestusersecret', 'trust,read,write', 'client_credentials,authorization_code,implicit,password,refresh_token', NULL, 'ROLE_USER,ROLE_TRUSTED_CLIENT', 600, 6000, NULL, NULL);





