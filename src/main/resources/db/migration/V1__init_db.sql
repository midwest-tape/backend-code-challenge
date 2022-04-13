create table Address
(
    id        bigint            not null primary key,
    address1 varchar(255)       not null,
    address2  varchar(255),
    city  varchar(255)          not null,
    state  varchar(100)         not null, 
    postal  varchar(10)         not null
);

insert into Address
    (id, address1, address2, city, state, postal)
values (1, '1 main st', null, 'Ingwellood', 'CA', '12345') ,
    (2, '283 1st Ave', 'suite 2', 'Clearwater', 'FL', '34695');

create table User
(
    id        bigint              not null primary key,
    firstName varchar(255)        not null,
    lastName  varchar(255)        not null,
    username  varchar(255)        unique not null,
    password  varchar(255)        not null, -- WHAT!? NOT ENCRYPTED!? ;-)
    addressId bigint,             
    foreign key (addressId) references Address(id)
);

insert into User
    (id, firstName, lastName, username, password, addressId)
values (1, 'Phil', 'Ingwell', 'PhilIngwell', 'Password123', 1) ,
    (2, 'Anna', 'Conda', 'AnnaConda', 'Password234', 2);
