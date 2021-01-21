create table if not exists exp_users(
	user_id serial primary key,
	first_name varchar(10) not NULL,
	last_name varchar(10) not NULL,
	username varchar(60) not null unique,
	password varchar(60) not null,
	user_type varchar(12) not null,
	email varchar(80) not null,
	timestamp varchar(80) not null
	
);

create table if not exists exp_tickets(
	ticket_id serial primary key,
	user_id int references exp_users(user_id),
	title varchar(30),
	description text,
	ticket_type int not NULL,
	image bytea,
	approved boolean,
	timestamp varchar(80) not null
);

create function get_ticket_information(id int)
language sql AS
$$

$$;

create function get_tickets()
language sql as
$$

$$;

create procedure get_users()
language sql as
$$
	select us.type, concat_ws(' ', us.first_name, us.last_name) as name, us.username, us.password, us.user_type, us.email from exp_users as us;
$$;

create or replace procedure drop_tables() 
language sql 
as $$
	drop table exp_tickets;
	drop table exp_users;
$$;

create or replace procedure remove_user(int id)
language plpgsql 
as 
$$
	BEGIN
		delete from exp_tickets where user_id = id;
		delete from exp_users where user_id = id;
	END
$$;


begin;
	drop table exp_tickets;
	drop table exp_users;
commit;

--create role admin_user LOGIN;