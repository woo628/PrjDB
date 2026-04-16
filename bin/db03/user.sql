/* 
 TMEM
 회원관리
 번호 숫자(6) 기본키 자동증가
 이름 문자(30) 필수입력
 아이디 문자(20) 필수입력 중복방지
 암호 문자(20) 필수입력
 이메일 문자(320) 중복방지 
 가입일 날짜 기본값
 
 CREATE table TUSER (
    ID VARCHAR2(20) PRIMARY KEY,
    NAME VARCHAR2(30) NOT NULL,
    EMAIL VARCHAR2(320) UNIQUE
);

INSERT INTO TUSER VALUES ('A1','가가','A1.COM');
INSERT INTO TUSER VALUES ('B1','나나','B1.COM');
INSERT INTO TUSER VALUES ('C1','다다','C1.COM');
INSERT INTO TUSER VALUES ('D1','라라','D1.COM');
INSERT INTO TUSER VALUES ('E1','마마','E1.COM');
COMMIT;
 */
 