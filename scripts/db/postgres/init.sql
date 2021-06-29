CREATE DATABASE "inventory-service"
    WITH
    OWNER = postgres
    ENCODING = 'UTF8'
    LC_COLLATE = 'en_US.utf8'
    LC_CTYPE = 'en_US.utf8'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1;

CREATE USER inventory_user WITH ENCRYPTED PASSWORD 'pwd12345#';
GRANT ALL PRIVILEGES ON DATABASE "inventory-service" TO inventory_user;

CREATE DATABASE "order-service"
    WITH
    OWNER = postgres
    ENCODING = 'UTF8'
    LC_COLLATE = 'en_US.utf8'
    LC_CTYPE = 'en_US.utf8'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1;

CREATE USER order_user WITH ENCRYPTED PASSWORD 'pwd12345#';
GRANT ALL PRIVILEGES ON DATABASE "order-service" TO order_user;