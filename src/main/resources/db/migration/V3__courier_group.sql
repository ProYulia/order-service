create table courier_group
(
    group_id   integer not null
        primary key,
    courier_id integer,
    date       varchar(255)
);

alter table courier_group
    owner to postgres;


