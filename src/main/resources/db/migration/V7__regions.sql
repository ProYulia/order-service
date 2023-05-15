create table regions
(
    courier_id integer not null
        constraint fk3dnbib48w1ktvtla08clgj3oo
            references courier,
    regions    integer
);

alter table regions
    owner to postgres;

