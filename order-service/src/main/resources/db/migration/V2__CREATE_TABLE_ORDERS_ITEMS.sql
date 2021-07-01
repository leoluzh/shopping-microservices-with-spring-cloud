CREATE TABLE orders_items (
    id BIGSERIAL PRIMARY KEY ,
    sku TEXT NOT NULL ,
    price DECIMAL(12,4) NOT NULL ,
    quantity INTEGER NOT NULL ,
    item_id BIGINT NOT NULL ,
        CONSTRAINT fk_orders
            FOREIGN KEY (item_id)
                REFERENCES orders(id)
                    ON DELETE CASCADE
);
