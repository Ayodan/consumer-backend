use springbootdb
if exists(select *
from sys.objects
where object_id=OBJECT_Id(N'getRangeOfResult') and type IN (N'P', N'PC'))
drop procedure getRangeOfResult
go
create procedure getRangeOfResult(@number  INT ,@offset  INT)
as
begin
select * from articles
order by articleId
offset @offset rows
fetch first @number rows only
end