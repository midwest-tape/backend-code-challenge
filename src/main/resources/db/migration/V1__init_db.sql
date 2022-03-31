drop table if exists address;
drop table if exists user;


DROP TABLE IF EXISTS ADDRESS;

CREATE TABLE ADDRESS (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  address1 VARCHAR(255) NOT NULL,
  address2 VARCHAR(255) NOT NULL,
  city VARCHAR(255) not NULL,
  state VARCHAR(100)  not NULL,
  postal VARCHAR(10)  not  NULL
);

 create table User
    (
        id        int     AUTO_INCREMENT primary key,
        firstName varchar(255)        not null,
        lastName  varchar(255)        not null,
        username  varchar(255)  not null,
        password  varchar(255)        not null,
        addressId int not null,
        foreign key (addressId) references address(id)
    );



