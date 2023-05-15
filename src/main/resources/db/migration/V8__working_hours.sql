create table working_hours
(
    courier_id    integer not null
        constraint fk78c6gwsu910dv7tlp8gblcf80
            references courier,
    working_hours varchar(255)
);

alter table working_hours
    owner to postgres;

