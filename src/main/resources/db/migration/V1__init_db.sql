create table User
(
    id        bigint              not null primary key,
    firstName varchar(255)        not null,
    lastName  varchar(255)        not null,
    username  varchar(255) unique not null,
    password  varchar(255)        not null, -- WHAT!? NOT ENCRYPTED!? ;-)
	addressId bigint
);	
	
create table Address
(
	id bigint 						not null primary key auto_increment,
	address1 varchar(255) 			not null,
	address2 varchar(255),
	city varchar(255) 				not null,
	state varchar(255) 				not null,
	postal varchar(255) 			not null
);

alter table User
	add constraint FK_USER_ADDRESS foreign key (addressId)
	REFERENCES address (id)
	on update CASCADE;
	
	
insert into Address
	(address1, address2, city, state, postal)
	values('123 State St', '1', 'Boston', 'MA', '02148'),
	('456 State St', '2', 'Boston', 'MA', '02148');
	
insert into User
    (id, firstName, lastName, username, password, addressId)
values (1, 'Phil', 'Ingwell', 'PhilIngwell', 'Password123', 1) ,
    (2, 'Anna', 'Conda', 'AnnaConda', 'Password234', 2);
	
	
