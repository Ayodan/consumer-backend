IF EXISTS ( SELECT  *
            FROM    sys.objects
            WHERE   object_id = OBJECT_ID(N'getUserByname2tables')AND type IN ( N'P', N'PC' ) )
DROP procedure getUserByname2tables
Go
create procedure getUserByname2tables(@userName varchar (250))
AS
begin
select user_roles.NAME
from users
inner join users_user_roles on users.ID = users_user_roles.USERS_ID
inner join user_roles on  users_user_roles.ROLES_ID=user_roles.ID
where USERS.USERNAME= @userName

select USERS.Id,USERS.USERNAME,USERS.PASSWORD
from users
where USERS.USERNAME= @userName
end