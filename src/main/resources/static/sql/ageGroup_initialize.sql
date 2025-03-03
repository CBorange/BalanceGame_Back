delete from age_group;

insert into age_group (name, description, range) values('20대 미만', '1 ~ 19', '^(1[0-9]|[1-9])$');
insert into age_group (name, description, range) values('20대', '20 ~ 29', '^2[0-9]$');
insert into age_group (name, description, range) values('30대', '30 ~ 39', '^3[0-9]$');
insert into age_group (name, description, range) values('40대', '40 ~ 49', '^4[0-9]$');
insert into age_group (name, description, range) values('50대 이상', '50 ~ 1000', '^(50|[5-9][0-9]|[1-9][0-9]{2}|1000)$');