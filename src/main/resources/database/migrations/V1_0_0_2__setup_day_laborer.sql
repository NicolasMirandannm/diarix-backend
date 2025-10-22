create table day_laborer
(
    id            varchar(40) primary key,
    manager_id    varchar(40),
    name          varchar(150) not null,
    cpf           varchar(14)  not null,
    phone_number  varchar(20)  not null,
    status        varchar(20)  not null,
    pix_key       varchar(120),
    created_date  timestamp    not null default current_timestamp,
    created_by    varchar(100) not null,
    modified_date timestamp    not null default current_timestamp,
    modified_by   varchar(100) not null,
    version       bigint       not null
);
