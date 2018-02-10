CREATE KEYSPACE IF NOT EXISTS stockExchange
WITH REPLICATION = { 'class' : 'SimpleStrategy', 'replication_factor': 3 };

use stockExchange;

DROP TABLE IF EXISTS stock;

CREATE TABLE IF NOT EXISTS stock (
"20-day" FLOAT,
"200-day" FLOAT,
"50-day" LIST<FLOAT>,
"52-day" LIST<FLOAT>,
analystrecom FLOAT,
averagetruerange FLOAT,
averagevolume FLOAT,
beta FLOAT,
change FLOAT,
company VARCHAR,
epsttm FLOAT,
earningdate MAP<VARCHAR, VARCHAR>,
price FLOAT,
roi FLOAT,
id MAP<VARCHAR, VARCHAR>,
description MAP<VARCHAR ,VARCHAR>,
performance MAP<VARCHAR, FLOAT>,
ratio MAP<VARCHAR, FLOAT>,
primary key("20-day")
) ;