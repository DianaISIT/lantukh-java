CREATE TABLE client (
	id INTEGER PRIMARY KEY AUTOINCREMENT,
	first_name VARCHAR NOT NULL,
	last_name VARCHAR NOT NULL,
	patronymic VARCHAR NOT NULL,
	passport VARCHAR NOT NULL
);

CREATE TABLE currency (
	code VARCHAR NOT NULL,
	name VARCHAR NOT NULL
);

CREATE TABLE transaction (
	id INTEGER PRIMARY KEY AUTOINCREMENT,
	client_id INTEGER NOT NULL,
	currency_code_from VARCHAR NOT NULL,
	currency_code_to VARCHAR NOT NULL,
	amount float NOT NULL,
	date datetime NOT NULL,
	result float NOT NULL
);

CREATE TABLE currency_rate (
	currency_From_code VARCHAR NOT NULL,
	currency_To_code VARCHAR NOT NULL,
	value_purchase float NOT NULL,
	value_pokypka float NOT NULL
);






