INSERT into STOCK_USER values(1,'Philip','Fry');
INSERT into STOCK_USER values(2,'Joahn','Zoindberg');
INSERT into STOCK_USER values(3,'Hubert','Farnsworth');
INSERT into STOCK_USER values(4,'Hermes','Conrad');
INSERT into STOCK_USER values(5,'Bender','Rodríguez');
INSERT into STOCK_USER values(6,'Turanga','Leela');

INSERT into STOCK values(1,'AAPL');
INSERT into STOCK values(2,'GOOGL');
INSERT into STOCK values(3,'MSFT');

INSERT into STOCK_ITEM(id, quantity, user_id, stock_id) values(1,10,1,1);
INSERT into STOCK_ITEM(id, quantity, user_id, stock_id) values(2,20,1,2);
INSERT into STOCK_ITEM(id, quantity, user_id, stock_id) values(3,30,1,3);

INSERT into STOCK_ITEM(id, quantity, user_id, stock_id) values(4,20,2,1);
INSERT into STOCK_ITEM(id, quantity, user_id, stock_id) values(5,20,2,2);
INSERT into STOCK_ITEM(id, quantity, user_id, stock_id) values(6,22,2,3);

INSERT into STOCK_ITEM(id, quantity, user_id, stock_id) values(7,1,3,1);
INSERT into STOCK_ITEM(id, quantity, user_id, stock_id) values(8,2,3,2);
INSERT into STOCK_ITEM(id, quantity, user_id, stock_id) values(9,19,3,3);

INSERT into STOCK_ITEM(id, quantity, user_id, stock_id) values(10,12,4,1);
INSERT into STOCK_ITEM(id, quantity, user_id, stock_id) values(11,30,4,2);
INSERT into STOCK_ITEM(id, quantity, user_id, stock_id) values(12,31,4,3);

INSERT into STOCK_ITEM(id, quantity, user_id, stock_id) values(13,1,5,1);
INSERT into STOCK_ITEM(id, quantity, user_id, stock_id) values(14,1,5,2);
INSERT into STOCK_ITEM(id, quantity, user_id, stock_id) values(15,1,5,3);

INSERT into STOCK_ITEM(id, quantity, user_id, stock_id) values(16,2,6,1);
INSERT into STOCK_ITEM(id, quantity, user_id, stock_id) values(17,9,6,2);
INSERT into STOCK_ITEM(id, quantity, user_id, stock_id) values(18,10,6,3);