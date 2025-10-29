create table payment
(
    id              varchar(40) primary key,
    manager_id      varchar(40),
    date            timestamp      not null default current_timestamp,
    value           decimal(10, 2) not null,
    method          varchar(50)    not null,
    observations     varchar(500),
    created_date    timestamp      not null default current_timestamp,
    created_by      varchar(100)   not null,
    modified_date   timestamp      not null default current_timestamp,
    modified_by     varchar(100)   not null,
    version         bigint         not null
);