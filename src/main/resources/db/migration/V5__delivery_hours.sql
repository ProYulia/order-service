create table delivery_hours
(
    order_id       integer not null
        constraint fkc0km9uvtc53e1acnjc43v8vu1
            references orders,
    delivery_hours varchar(255)
);

alter table delivery_hours
    owner to postgres;


