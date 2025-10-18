create table contract_manager (
    id varchar(40) primary key,
    name varchar(255) not null,
    email varchar(255) not null,
    password varchar(255) not null,
    phone_number varchar(20),
    created_date timestamp not null default current_timestamp,
    created_by varchar(100) not null,
    modified_date timestamp not null default current_timestamp,
    modified_by varchar(100) not null,
    version bigint not null
);

create unique index uk_contract_manager_email on contract_manager(email);

-- email = 'nicolas@contracts.com.br', senha = 123
insert into contract_manager (id, name, email, password, phone_number, created_date, created_by, modified_date, modified_by, version)
values ('ebae93ea-59e3-4810-8f37-693d59f6305b', 'Nicolas Leonardo Miranda Lima', 'nicolas@contracts.com.br', '$2a$12$O8VKh4WDddoJN7D.3R3nUO.XbW/tfMbQSyGlLB1qVLlJ5nQXaT9WW', '(67) 99291-1805', current_timestamp, 'system', current_timestamp, 'system', 1);