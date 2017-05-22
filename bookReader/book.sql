DROP TABLE book;

CREATE TABLE book(
	book_id VARCHAR2(30) PRIMARY KEY,
	title VARCHAR2(300) NOT NULL,
	author VARCHAR2(280) NOT NULL,
	publisher VARCHAR2(100) NOT NULL,
	publish_date VARCHAR2(30)
);

SELECT Book_id , COUNT(*)
  FROM book
 GROUP BY Book_id
 HAVING COUNT(*) > 1 ;



SELECT *
FROM book;

SELECT count(*)
FROM book;

INSERT INTO book
VALUES ('1','1','1','1','1');

SELECT *
FROM book
WHERE title LIKE '%스트레스 사용설명서%';