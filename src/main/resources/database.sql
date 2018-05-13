drop table BOOK if exists;
create table BOOK(id bigint,
reader varchar(20),
isbn varchar(50),
title varchar(100),
author varchar(20),
description varchar(500));