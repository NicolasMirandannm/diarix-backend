create table manager
(
    id            varchar(40) primary key,
    name          varchar(255) not null,
    email         varchar(255) not null,
    password      varchar(255) not null,
    phone_number  varchar(20),
    created_date  timestamp    not null default current_timestamp,
    created_by    varchar(100) not null,
    modified_date timestamp    not null default current_timestamp,
    modified_by   varchar(100) not null,
    version       bigint       not null
);

create unique index uk_manager_email on manager (email);