/** Create database user, database and schema in PostgreSQL **/
CREATE ROLE testuser LOGIN
  ENCRYPTED PASSWORD 'md550f63ef397ff72af7275884e0986d0f8'
  NOSUPERUSER INHERIT NOCREATEDB NOCREATEROLE NOREPLICATION;

CREATE DATABASE testdb
  WITH OWNER = testuser
       ENCODING = 'UTF8'
       TABLESPACE = pg_default
       LC_COLLATE = 'en_US.UTF-8'
       LC_CTYPE = 'en_US.UTF-8'
       CONNECTION LIMIT = -1;
  
/** Tables and Sequences creation **/  
CREATE SCHEMA spaexample;

CREATE TABLE spaexample.car (
  id INTEGER PRIMARY KEY,
  brand VARCHAR(100),
  model VARCHAR(100),
  price INTEGER );

CREATE SEQUENCE spaexample.seq_car;

/** OAuth2 Tables **/
create schema auth;
set schema 'auth';

create table oauth_client_details (
  client_id VARCHAR(256) PRIMARY KEY,
  resource_ids VARCHAR(256),
  client_secret VARCHAR(256),
  scope VARCHAR(256),
  authorized_grant_types VARCHAR(256),
  web_server_redirect_uri VARCHAR(256),
  authorities VARCHAR(256),
  access_token_validity INTEGER,
  refresh_token_validity INTEGER,
  additional_information VARCHAR(4096),
  autoapprove VARCHAR(256)
);

create table oauth_client_token (
  token_id VARCHAR(256),
  token BYTEA,
  authentication_id VARCHAR(256) PRIMARY KEY,
  user_name VARCHAR(256),
  client_id VARCHAR(256)
);

create table oauth_access_token (
  token_id VARCHAR(256),
  token BYTEA,
  authentication_id VARCHAR(256) PRIMARY KEY,
  user_name VARCHAR(256),
  client_id VARCHAR(256),
  authentication BYTEA,
  refresh_token VARCHAR(256)
);

create table oauth_refresh_token (
  token_id VARCHAR(256),
  token BYTEA,
  authentication BYTEA
);

create table oauth_code (
  code VARCHAR(256), authentication BYTEA
);

create table oauth_approvals (
	userId VARCHAR(256),
	clientId VARCHAR(256),
	scope VARCHAR(256),
	status VARCHAR(10),
	expiresAt TIMESTAMP,
	lastModifiedAt TIMESTAMP
);


/** OAuth Client (Application) **/
insert into auth.oauth_client_details 
(client_id, client_secret, authorized_grant_types, scope, authorities, autoapprove)
values
('amoraesdev.spa-example', 's3cr3t', 'authorization_code,refresh_token,password,client_credentials', 'openid', 'ROLE_CLIENT', 'true');

/** Custom User Profile table **/ 
CREATE TABLE user_profile (
  id INTEGER PRIMARY KEY,
  email VARCHAR(100),
  name VARCHAR(100),
  password VARCHAR(32),
  active BOOLEAN );

CREATE SEQUENCE seq_user_profile;

INSERT INTO user_profile VALUES (NEXTVAL('seq_user_profile'),'alessandro@amoraesdev.com','Alessandro Moraes',md5('123'),true);
