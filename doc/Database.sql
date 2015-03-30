CREATE TABLE list_comment (
	id integer,
	author varchar,
	date date,
	comment varchar,
	QRcode varchar,
	PRIMARY KEY(id)
);

INSERT INTO list_comment VALUES(0, 'Marie C.', '2015-03-29', "J'adore!", '1');
INSERT INTO list_comment VALUES(1, 'Marion D.', '2015-03-30', 'Sublime!', '1');