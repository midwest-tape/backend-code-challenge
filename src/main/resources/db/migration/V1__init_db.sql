drop table if exists Users;
create table Users
(
    id int not null primary key,
    addressId int,
    firstName varchar(255) not null,
    lastName  varchar(255) not null,
    username  varchar(255) unique not null,
    password  varchar(255) not null -- WHAT!? NOT ENCRYPTED!? ;-)
);

drop table if exists Address;
create table Address
(
    id int auto_increment primary key,
    address1 varchar(255) not null,
    address2  varchar(255),
    city  varchar(255) not null,
    state varchar(100) not null,
    postal varchar(10) not null
);

alter table Users
    add foreign key (addressId)
        references Address(id);

insert into Users
(id, addressId, firstName, lastName, username, password)
values (1, null,  'Phil', 'Ingwell', 'PhilIngwell', 'Password123');

insert into Address
(id, address1, address2, city, state, postal)
values (default, '100 W. Phillips St.', 'apt 1', 'Philsville', 'IL','60601');

update Users
set addressId = (select max(id) from Address)
where id = 1;

insert into Users
(id, addressId, firstName, lastName, username, password)
values (2, null,  'Anna', 'Conda', 'AnnaConda', 'Password234');

insert into Address
(id, address1, address2, city, state, postal)
values (default, '200 S. Anna Ave..', 'apt 2', 'Annville', 'WI','60602');

update Users
set addressId = (select max(id) from Address)
where id = 2;
commit;

select * from Address;
select * from Users;