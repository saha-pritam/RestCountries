drop database if exists restcountries;
create database restcountries;
use restcountries;

create table country
(
	cca2 char(2) primary key,
    ccn3 varchar(10) unique,
    cca3 char(3) unique,
    cioc varchar(10) unique,
    independent boolean,
    status varchar(30),
    unMember boolean,
    region varchar(30),
    subregion varchar(30),
    landlocked boolean,
    area double,
    flag varchar(100),
    population long,
    fifa varchar(10) unique,
    startofweek varchar(10)
);

create table name
(
	id integer auto_increment primary key,
    common varchar(100),
    official varchar(100),
    cca2 char(2),
    constraint fk_1_name foreign key (cca2) references country(cca2)
);

create table nativename
(
	id integer auto_increment primary key,
    langcode char(3),
    common varchar(100),
    official varchar(100),
    cca2 char(2),
    constraint fk_1_nativename foreign key (cca2) references country(cca2)
);

create table tld
(
	id integer auto_increment primary key,
    value varchar(100),
    cca2 char(2),
    constraint fk_1_tld foreign key (cca2) references country(cca2)
);

create table currencies
(
	id integer auto_increment primary key,
    currencycode char(3),
    name varchar(100),
    symbol varchar(10),
    cca2 char(2),
    constraint fk_1_currencies foreign key (cca2) references country(cca2)
);

create table idd_root
(
	cca2 char(2) primary key,
    root varchar(3),
    constraint fk_1_idd_root foreign key (cca2) references country(cca2)
);

create table idd_suffixes
(
	id integer auto_increment primary key,
	cca2 char(2),
    suffix varchar(5),
    constraint fk_1_idd_suffixes foreign key (cca2) references country(cca2)
);

create table capital
(
		id integer primary key auto_increment,
        cca2 char(2),
        capitalname varchar(20),
        constraint fk_1_capital foreign key (cca2) references country(cca2)
);

create table altspellings
(
		id integer primary key auto_increment,
        cca2 char(2),
        spelling varchar(50),
        constraint fk_1_altspellings foreign key (cca2) references country(cca2)
);

create table languages
(
	id integer primary key auto_increment,
    cca2 char(2),
    langcode char(3),
    langname varchar(20),
    constraint fk_1_languages foreign key (cca2) references country(cca2)
);

create table translations
(
	id integer primary key auto_increment,
    cca2 char(2),
    code char(3),
    official varchar(100),
    common varchar(100),
    constraint fk_1_translations foreign key (cca2) references country(cca2)
);

create table latlng
(
	id integer primary key auto_increment,
    cca2 char(2),
    lat double,
    lng double,
    constraint fk_1_latlng foreign key (cca2) references country(cca2)
);

create table borders
(
	id integer primary key auto_increment,
    cca2 char(2),
    code char(3),
    constraint fk_1_borders foreign key (cca2) references country(cca2)
);

create table demonyms
(
	id integer primary key auto_increment,
    cca2 char(2),
    code char(3),
    f varchar(100),
    m varchar(100),
    constraint fk_1_demonyms foreign key (cca2) references country(cca2)
);

create table maps
(
	cca2 char(2) primary key,
    googlemaps varchar(100),
    openstreetmaps varchar(100),
    constraint fk_1_maps foreign key (cca2) references country(cca2)
);

create table gini
(
	id integer primary key auto_increment,
    cca2 char(2),
    year char(4),
    value double,
    constraint fk_1_gini foreign key (cca2) references country(cca2)
);

create table car_side
(
	cca2 char(2) primary key,
    side varchar(15),
    constraint fk_1_car_side foreign key (cca2) references country(cca2)
);

create table car_signs
(
	id integer primary key auto_increment,
    cca2 char(2),
    sign varchar(10),
    constraint fk_1_car_signs foreign key (cca2) references country(cca2)
);

create table timezones
(
	id integer primary key auto_increment,
    cca2 char(2),
    value varchar(50),
	constraint fk_1_timezones foreign key (cca2) references country(cca2)
);

create table continents
(
	id integer primary key auto_increment,
    cca2 char(2),
    name varchar(50),
	constraint fk_1_continents foreign key (cca2) references country(cca2)
);

create table flags
(
	cca2 char(2) primary key,
    png varchar(500),
    svg varchar(500),
    alt varchar(500),
    constraint fk_1_flags foreign key (cca2) references country(cca2)
);

create table coatofarms
(
	cca2 char(2) primary key,
    png varchar(500),
    svg varchar(500),
    constraint fk_1_coatofarms foreign key (cca2) references country(cca2)
);

create table capitalinfo_latlng
(
	id integer primary key auto_increment,
    cca2 char(2),
    lat double,
    lng double,
    constraint fk_1_capitalinfo_latlng foreign key (cca2) references country(cca2)
);

create table postalcode
(
	cca2 char(2) primary key,
    format varchar(100),
    regex varchar(100),
    constraint fk_1_postalcode foreign key (cca2) references country(cca2)
);