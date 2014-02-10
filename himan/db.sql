CREATE TABLE test.s_user (
	id INT NOT NULL AUTO_INCREMENT,
	account VARCHAR(100) NOT NULL,
	name VARCHAR(100) NOT NULL,
	reg_date DATE NOT NULL,
	create_date DATE NOT NULL,
	create_by VARCHAR(100) NOT NULL,
	updated_date DATE NOT NULL,
	updated_by VARCHAR(100) NOT NULL,
	primary key (id,account)
)
ENGINE=InnoDB 
DEFAULT CHARSET=utf8 
COLLATE=utf8_general_ci;


CREATE TABLE test.s_role (
	id INT NOT NULL AUTO_INCREMENT,
	name VARCHAR(100) NOT NULL,
	create_date DATE NOT NULL,
	create_by VARCHAR(100) NOT NULL,
	updated_date DATE NOT NULL,
	updated_by VARCHAR(100) NOT NULL,
	primary key (id,name)
)
ENGINE=InnoDB 
DEFAULT CHARSET=utf8 
COLLATE=utf8_general_ci;



CREATE TABLE test.s_user_role(
	id INT NOT NULL AUTO_INCREMENT,
	user_id INT NOT NULL,
	role_id INT NOT NULL,
	create_date DATE NOT NULL,
	create_by VARCHAR(100) NOT NULL,
	updated_date DATE NOT NULL,
	updated_by VARCHAR(100) NOT NULL,
	primary key (id,user_id,role_id)
)
ENGINE=InnoDB 
DEFAULT CHARSET=utf8 
COLLATE=utf8_general_ci;


CREATE TABLE test.s_resource(
	id INT NOT NULL AUTO_INCREMENT,
	name VARCHAR(100) NOT NULL,
	url VARCHAR(200) NOT NULL,
	create_date DATE NOT NULL,
	create_by VARCHAR(100) NOT NULL,
	updated_date DATE NOT NULL,
	updated_by VARCHAR(100) NOT NULL,
	primary key (id)
)
ENGINE=InnoDB 
DEFAULT CHARSET=utf8 
COLLATE=utf8_general_ci;


CREATE TABLE test.s_resource_role(
	id INT NOT NULL AUTO_INCREMENT,
	role_id INT NOT NULL ,
	resource_id INT NOT NULL ,
	create_date DATE NOT NULL,
	create_by VARCHAR(100) NOT NULL,
	updated_date DATE NOT NULL,
	updated_by VARCHAR(100) NOT NULL,
	primary key (id,role_id,resource_id)
)
ENGINE=InnoDB 
DEFAULT CHARSET=utf8 
COLLATE=utf8_general_ci;





