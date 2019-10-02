CREATE TABLE person
(
    id                CHAR(36) PRIMARY KEY,
    name              VARCHAR(255) NOT NULL,
    age               INTEGER,
    direction         VARCHAR(255) NOT NULL,
    auxiliary_json    LONGTEXT     NOT NULL CHECK (JSON_VALID(auxiliary_json)),
    modified_on       TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP(),
    version           BIGINT       NOT NULL
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci;

ALTER TABLE person ADD auxiliary_name VARCHAR(255) AS (JSON_VALUE(auxiliary_json, '$.name'));
CREATE INDEX person__auxiliary_name_index ON person(auxiliary_name);

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