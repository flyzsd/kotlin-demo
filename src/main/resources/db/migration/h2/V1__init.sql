CREATE TABLE person (
  id VARCHAR(255) PRIMARY KEY,
  name VARCHAR(255) NOT NULL,
  age INTEGER,
  direction VARCHAR(255) NOT NULL,
  modified_on TIMESTAMP NOT NULL,
  version BIGINT NOT NULL
);

CREATE TABLE order_table (
  id IDENTITY,
  name VARCHAR(255) NOT NULL,
  age INTEGER,
  modified_on TIMESTAMP NOT NULL,
  version BIGINT NOT NULL
);