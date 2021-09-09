 if not exists (select * from sysobjects where name='users' and xtype='U')
    create table users (
       id INT IDENTITY(1, 1) PRIMARY KEY,
  username VARCHAR(256) UNIQUE NOT NULL,
  password VARCHAR(256) NOT NULL,
  enabled BIT
    )
go

 if not exists (select * from sysobjects where name='authorities' and xtype='U')
    create table authorities (
      username VARCHAR(256) NOT NULL,
  authority VARCHAR(256) NOT NULL,
  PRIMARY KEY(username, authority)
    )
go

drop table if exists USERS_USER_ROLES;
CREATE TABLE USERS_USER_ROLES (
    ROLES_ID int NOT NULL PRIMARY KEY,
    USERS_ID int FOREIGN KEY REFERENCES USERS(ID)
);

INSERT INTO users ( username, password, enabled) VALUES ( 'user', '{bcrypt}$2a$10$cyf5NfobcruKQ8XGjUJkEegr9ZWFqaea6vjpXWEaSqTa2xL9wjgQC', 1);
INSERT INTO authorities (username, authority) VALUES ('user', 'ROLE_USER');

drop table if exists USER_ROLES;
CREATE TABLE USER_ROLES (
    ID int NOT NULL PRIMARY KEY,
    NAME varchar(250) not null
);

IF EXISTS ( SELECT  *
            FROM    sys.objects
            WHERE   object_id = OBJECT_ID(N'getUserByname')AND type IN ( N'P', N'PC' ) )
DROP procedure getUserByname
Go
create procedure getUserByname(@userName varchar (250))
AS
begin
select USERS.*,user_roles.NAME
from users
left join users_user_roles on users.ID = users_user_roles.USERS_ID
left join user_roles on  users_user_roles.ROLES_ID=user_roles.ID where USERS.USERNAME= @userName
end