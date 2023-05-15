create table orders
(
    order_id      integer not null
        primary key,
    complete_time timestamp(6) with time zone,
    cost          integer,
    status        varchar(255),
    region        integer,
    weight        real
);

alter table orders
    owner to postgres;


