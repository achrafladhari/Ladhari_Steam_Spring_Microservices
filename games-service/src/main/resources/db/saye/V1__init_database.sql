create table if not exists category
(
    id          integer not null
    primary key,
    description varchar(255),
    name        varchar(255)
    );

create table if not exists games
(
    id                 integer          not null
    primary key,
    avaiblity double precision not null,
    description        varchar(255),
    name               varchar(255),
    price              double precision not null,
    image              varchar(255),
    category_id        integer
    constraint fk1mtsbur82frn64de7balymq9s
    references category
    );

create sequence if not exists category_seq increment by 50;
create sequence if not exists games_seq increment by 50;

