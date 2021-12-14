GO
create database SkuciSeDb
GO
USE SkuciSeDb
GO

create table Ad
(
	id int primary key identity(1,1) NOT NULL,
	title nvarchar(50) NOT NULL,
	flat_house int NOT NULL,
	sell_rent int NOT NULL,
	number_of_rooms int NOT NULL,
	description nvarchar(MAX) NOT NULL,
	size real NOT NULL,
	date_start nvarchar(50) NOT NULL,
	date_end nvarchar(50),
	price real NOT NULL,
	location nvarchar(MAX) NOT NULL,
	floor int NOT NULL,
	internet int NOT NULL,
	ac int NOT NULL,
	intercom int NOT NULL,
	garage int NOT NULL,
	elevator int NOT NULL,
	balcony int NOT NULL,
	yard int NOT NULL,
	heating int NOT NULL,
	tv int NOT NULL,
	user_id int NOT NULL,
)

create table AdImage
(
	id int primary key identity(1,1) NOT NULL,
	ad_id int NOT NULL,
	image nvarchar(MAX) NOT NULL
)

create table Appointment
(
	id int primary key identity(1,1) NOT NULL,
	user_id int NOT NULL,
	ad_id int NOT NULL,
	date nvarchar(50) NOT NULL,
	approved int NOT NULL,
)


create table [SkuciSeDb].[dbo].[User]
(
	id int primary key identity(1,1) NOT NULL,
	username nvarchar(50) NOT NULL,
	name nvarchar(50) NOT NULL,
	email nvarchar(50) NOT NULL,
	hash nvarchar(50) NOT NULL,
	salt nvarchar(50) NOT NULL,
)


create table UserImage
(
	id int primary key identity(1,1) NOT NULL,
	user_id int NOT NULL,
	image nvarchar(MAX) NOT NULL
)










