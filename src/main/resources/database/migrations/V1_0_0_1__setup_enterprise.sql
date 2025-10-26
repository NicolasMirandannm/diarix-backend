create table enterprise
(
    id            varchar(40) primary key,
    manager_id    varchar(40),
    name          varchar(255) not null,
    cnpj          varchar(30)  not null,
    owner_name    varchar(255) not null,
    owner_email   varchar(255) not null,
    owner_phone   varchar(20),
    address       varchar(255),
    status        varchar(20)  not null,
    base_daily_rate decimal(10, 2) not null,
    created_date  timestamp    not null default current_timestamp,
    created_by    varchar(100) not null,
    modified_date timestamp    not null default current_timestamp,
    modified_by   varchar(100) not null,
    version       bigint       not null
);

create unique index uk_enterprise_cnpj on enterprise (cnpj);
create unique index uk_enterprise_owner_email on enterprise (owner_email);
