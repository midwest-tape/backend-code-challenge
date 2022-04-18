create table User
(
    id        bigint              not null primary key,
    first_name varchar(255)        not null,
    last_Name  varchar(255)        not null,
    username  varchar(255) unique not null,
    password  varchar(255)        not null -- WHAT!? NOT ENCRYPTED!? ;-)
);

insert into User
    (id, first_Name, last_Name, username, password)
values (1, 'Phil', 'Ingwell', 'PhilIngwell', 'Password123') ,
    (2, 'Anna', 'Conda', 'AnnaConda', 'Password234');

create table Address
(
    user_id        bigint              not null primary key,
     address1 varchar(255)       not null,
    address2  varchar(255),
    city  varchar(255)          not null,
    state  varchar(100)         not null,
    postal  varchar(10)         not null,
    FOREIGN KEY (user_id)
      REFERENCES user (id)
      ON DELETE CASCADE
);

insert into Address
    (user_id, address1, address2, city, state,postal)
values (1, 'Something Street', 'Apt 3', 'Chicago', 'IL', '60677') ,
    (2, 'Another Ave', 'Unit 4', 'NYC', 'NY', '10001');

--SELECT * FROM USER INNER JOIN AddRESS on ADDRESS. USER_ID = USER.ID