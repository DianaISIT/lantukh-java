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

CREATE TABLE tranzaction (
	id INTEGER PRIMARY KEY AUTOINCREMENT,
	client_id INTEGER NOT NULL REFERENCES client(id),
	currency_code_from VARCHAR NOT NULL REFERENCES currency(code),
	currency_code_to VARCHAR NOT NULL REFERENCES currency(code),
	amount float NOT NULL,
	date datetime NOT NULL,
	result float NOT NULL
);

CREATE TABLE currency_rate (
	currency_from_code VARCHAR NOT NULL REFERENCES currency(code),
	currency_to_code VARCHAR NOT NULL REFERENCES currency(code),
	value_purchase float NOT NULL,
	value_pokypka float NOT NULL
);






