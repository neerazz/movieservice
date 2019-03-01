select count(*) , "title_akas" from movies.title_akas
union all
select count(*) , "title_basics" from movies.title_basics
union all
select count(*) , "title_crew" from movies.title_crew
union all
select count(*) , "title_episode" from movies.title_episode
union all
select count(*) , "title_principals" from movies.title_principals
union all
select count(*) , "title_rating" from movies.title_rating
union all
select count(*) , "namebasic" from movies.namebasic;