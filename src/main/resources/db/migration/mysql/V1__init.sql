CREATE TABLE person
(
    id          CHAR(36) PRIMARY KEY,
    name        VARCHAR(255) NOT NULL,
    age         INTEGER,
    direction   VARCHAR(255) NOT NULL,
    modified_on TIMESTAMP    NOT NULL,
    version     BIGINT       NOT NULL
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci;

CREATE TABLE purchase_order
(
    id          BIGINT PRIMARY KEY AUTO_INCREMENT,
    name        VARCHAR(255) NOT NULL,
    age         INTEGER,
    modified_on TIMESTAMP    NOT NULL,
    version     BIGINT       NOT NULL
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci;

CREATE TABLE order_item
(
    id             BIGINT PRIMARY KEY AUTO_INCREMENT,
    purchase_order BIGINT       NOT NULL,
    quantity       INTEGER,
    product        VARCHAR(255) NOT NULL
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci;