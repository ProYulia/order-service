create table order_group
(
    group_id integer not null
        constraint fk1lrk95a2qlruhscoesi5kr5sx
            references courier_group,
    order_id integer
);

alter table order_group
    owner to postgres;

