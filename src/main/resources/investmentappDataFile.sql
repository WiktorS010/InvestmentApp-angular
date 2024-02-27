CREATE TABLE IF NOT EXISTS investmentappdb.crypto_currency
(
    id                BIGINT AUTO_INCREMENT PRIMARY KEY,
    day_change        DOUBLE,
    image             VARCHAR(255),
    market_cap        BIGINT,
    market_cap_rank   BIGINT,
    market_cap_string VARCHAR(255),
    name              VARCHAR(255),
    price             DOUBLE,
    price_string      VARCHAR(255),
    symbol            VARCHAR(255)
);



CREATE TABLE IF NOT EXISTS investmentappdb.fiat_currency
(
    id                       BIGINT AUTO_INCREMENT PRIMARY KEY,
    name                     VARCHAR(255),
    price_compared_to_dollar BIGINT,
    short_name               VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS investmentappdb.user
(
    id         BIGINT AUTO_INCREMENT PRIMARY KEY,
    age        INT,
    email      VARCHAR(255),
    first_name VARCHAR(255),
    last_name  VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS investmentappdb.investment
(
    id                         BIGINT AUTO_INCREMENT PRIMARY KEY,
    created                    DATETIME(6),
    enter_price                DOUBLE,
    income                     DOUBLE,
    investment_name            VARCHAR(255),
    quantity_of_cryptocurrency DOUBLE,
    user_id                    BIGINT,
    CONSTRAINT FKgncrxrbv7kyjobb2wvg74ctif
        FOREIGN KEY (user_id) REFERENCES investmentappdb.user (id)
);

INSERT INTO investmentappdb.user (age, email, first_name, last_name)
VALUES (25, 'emial@gmail.com', 'James', 'Bond');

INSERT INTO investmentappdb.investment (created, enter_price, income, investment_name, quantity_of_cryptocurrency,
                                        user_id)
VALUES ('2024-02-27 12:00:00', 1000.00, 50.00, 'Investment 1', 10.0, 1),
       ('2024-02-27 12:30:00', 1500.00, 75.00, 'Investment 2', 15.0, 1),
       ('2024-02-27 13:00:00', 2000.00, 100.00, 'Investment 3', 20.0, 1);