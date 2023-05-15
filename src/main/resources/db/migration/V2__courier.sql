create table courier
(
    courier_id   integer not null
        primary key,
    courier_type varchar(255)
);

alter table courier
    owner to postgres;

INSERT INTO public.courier (courier_id, courier_type) VALUES (1, 'FOOT');
INSERT INTO public.courier (courier_id, courier_type) VALUES (2, 'BIKE');
INSERT INTO public.courier (courier_id, courier_type) VALUES (3, 'AUTO');
