use springbootdb
IF EXISTS ( SELECT  *
            FROM    sys.objects
            WHERE   object_id = OBJECT_ID(N'getArticleById')AND type IN ( N'P', N'PC' ) )
DROP procedure getArticleById
Go
create procedure getArticleById(@id INT)
AS
begin
SELECT articleId, title, category
FROM articles
WHERE articleId = @id
end