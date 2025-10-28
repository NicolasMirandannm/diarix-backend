create table daily_wage
(
    id              varchar(40) primary key,
    manager_id      varchar(40),
    date            timestamp      not null default current_timestamp,
    enterprise_id   varchar(40)    not null,
    day_laborer_id  varchar(40)    not null,
    payment_id      varchar(40),
    base_daily_rate decimal(10, 2) not null,
    bonus           decimal(10, 2),
    deduction       decimal(10, 2),
    payment_value   decimal(10, 2) not null,
    notes           varchar(500),
    payment_status  varchar(20)    not null,
    work_date       date       not null,
    start_hour      time       not null,
    end_hour        time       not null,
    created_date    timestamp      not null default current_timestamp,
    created_by      varchar(100)   not null,
    modified_date   timestamp      not null default current_timestamp,
    modified_by     varchar(100)   not null,
    version         bigint         not null,

    constraint fk_daily_wage_enterprise
        foreign key (enterprise_id) references enterprise (id),
    constraint fk_daily_wage_day_laborer
        foreign key (day_laborer_id) references day_laborer (id),
    constraint fk_daily_wage_payment
        foreign key (payment_id) references payment (id)
);