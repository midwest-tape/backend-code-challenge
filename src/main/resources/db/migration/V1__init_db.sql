create table User
(
    id        bigint              not null AUTO_INCREMENT,
    firstName varchar(255)        not null,
    lastName  varchar(255)        not null,
    username  varchar(255) unique not null,
    password  varchar(255)        not null -- WHAT!? NOT ENCRYPTED!? ;-)
    addressId bigint not null,
    primary key(id),
    foreign key (addressId) references address(id)
);

insert into User
    (id, firstName, lastName, username, password)
values (1, 'Phil', 'Ingwell', 'PhilIngwell', 'Password123');
insert into User
    (id, firstName, lastName, username, password) values
    (2, 'Anna', 'Conda', 'AnnaConda', 'Password234');


 create table address ()
 id bigint not null auto_increment,
 address1 varchar(255) not null,
 address2 varchar(255),
 city varchar(255) not null,
 state varchar(100) not null,
 postal varchar(10) not null,
 primary key(id)

 );

insert into address
    (address1, address2, city, state,postal)
values ('1234 House', 'Street', 'Irving', 'Texas','76580');
insert into User
    (address1, address2, city, state,postal)
     values
    ('134 meadow', 'Street', 'Plano', 'Texas','76680');