CREATE TABLE person
(
    id                CHAR(36) PRIMARY KEY,
    name              VARCHAR(255) NOT NULL,
    age               INTEGER      NOT NULL,
    direction         VARCHAR(255) NOT NULL,
    auxiliary_json    LONGTEXT     NOT NULL,
    modified_on       TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP() ON UPDATE CURRENT_TIMESTAMP(),
    version           BIGINT       NOT NULL
);

CREATE TABLE purchase_order
(
    id          BIGINT PRIMARY KEY AUTO_INCREMENT,
    name        VARCHAR(255) NOT NULL,
    age         INTEGER      NOT NULL,
    modified_on TIMESTAMP    NOT NULL,
    version     BIGINT       NOT NULL
);

CREATE TABLE order_item
(
    id             BIGINT PRIMARY KEY AUTO_INCREMENT,
    purchase_order BIGINT       NOT NULL,
    quantity       INTEGER      NOT NULL,
    product        VARCHAR(255) NOT NULL
);