create table star
(
    id   uuid primary key,
    star_name varchar(20),
    star_description text,
    star_size varchar(20),
    planet_distance_from_sun text,
    is_popular bool false,
    created_timestamp timestamp CURRENT_TIMESTAMP,
    updatedTimestamp timestamp CURRENT_TIMESTAMP
);

create table planet
(
    id   uuid primary key,
    planet_name varchar(20),
    planet_description text,
    planet_size varchar(20),
    planet_distance_from_sun text,
    is_popular bool false,
    created_timestamp timestamp CURRENT_TIMESTAMP,
    updatedTimestamp timestamp CURRENT_TIMESTAMP
);