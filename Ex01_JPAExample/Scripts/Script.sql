SELECT * FROM tab;
CREATE SEQUENCE exfile_idx_seq;
CREATE TABLE exfile(
	idx NUMBER PRIMARY KEY,
	REF NUMBER,
	uuid varchar2(2000),
	name varchar2(2000)
);
DROP TABLE exfile;
SELECT * FROM FBOARD2 f ;
CREATE TABLE board(
	idx NUMBER PRIMARY KEY,
	name varchar2(500) NOT NULL,
	password varchar2(500) NOT NULL,
	subject varchar2(500) NOT NULL,
	content varchar2(500) NOT NULL,
	regdate timestamp NOT null
);
DROP TABLE board;
CREATE SEQUENCE board_idx_seq;
SELECT * FROM BOARD f ;
SELECT * FROM exfile;