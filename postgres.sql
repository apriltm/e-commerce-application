--CREATE DATABASE ecommerce_db;
--
--CREATE TABLE orders (
--  id UUID NOT NULL UNIQUE DEFAULT gen_random_uuid(),
--   date_created timestamp default CURRENT_TIMESTAMP NOT NULL,
--   date_updated timestamp default CURRENT_TIMESTAMP NOT NULL,
--   total_price DOUBLE PRECISION,
--   CONSTRAINT pk_orders PRIMARY KEY (id)
--);
--
--
--CREATE TABLE products (
--  id UUID NOT NULL UNIQUE DEFAULT gen_random_uuid(),
--   name VARCHAR(255) NOT NULL,
--   price DOUBLE PRECISION NOT NULL,
--   image VARCHAR(255) NOT NULL,
--   quantity INTEGER NOT NULL,
--   CONSTRAINT pk_products PRIMARY KEY (id)
--);
--
--
--CREATE TABLE order_items (
--  id UUID NOT NULL UNIQUE DEFAULT gen_random_uuid(),
--   order_id UUID,
--   product_id UUID,
--   price DOUBLE PRECISION NOT NULL,
--   quantity INTEGER NOT NULL,
--   CONSTRAINT pk_order_items PRIMARY KEY (id)
--);
--
--
--ALTER TABLE order_items ADD CONSTRAINT FK_ORDER_ITEMS_ON_ORDER FOREIGN KEY (order_id) REFERENCES orders (id);
--
--ALTER TABLE order_items ADD CONSTRAINT FK_ORDER_ITEMS_ON_PRODUCT FOREIGN KEY (product_id) REFERENCES products (id);
--
--
--CREATE FUNCTION update_updated_on_orders()
--RETURNS TRIGGER AS $$
--BEGIN
--    NEW.updated_on = now();
--    RETURN NEW;
--END;
--$$ language 'plpgsql';
--
--
--CREATE TRIGGER update_orders_updated_on
--    BEFORE UPDATE
--    ON
--        orders
--    FOR EACH ROW
--EXECUTE PROCEDURE update_updated_on_orders();
--
--
--CREATE FUNCTION calc_orders()
--  RETURNS TRIGGER AS $$
--  BEGIN
--      NEW.total_price = price * quantity FROM order_items;
--      RETURN NEW;
--  END;
--  $$ language 'plpgsql';
--
--CREATE TRIGGER orders_calc
--	BEFORE UPDATE
--	ON
--		orders
--	FOR EACH ROW
--EXECUTE PROCEDURE calc_orders();
--
--
--INSERT INTO products (name, price, image, quantity) VALUES ('PlayStation 5', 499.99, 'https://m.media-amazon.com/images/I/31lXl8o5KNL._AC_UF894,1000_QL80_.jpg', 5);
--INSERT INTO order_items (order_id, product_id, price, quantity) VALUES ((SELECT id FROM orders WHERE orders.id = id)), (SELECT id FROM products WHERE products.id = id), (SELECT price FROM products WHERE products.name = 'PlayStation 5'), 2);
--
--INSERT INTO products (name, price, image, quantity) VALUES ('Nintendo Switch OLED', 349.99, 'https://m.media-amazon.com/images/I/7148mbvrbWL._AC_UF1000,1000_QL80_.jpg', 21);
--INSERT INTO order_items (product_id, price, quantity) SELECT id, price, 4 FROM products p1 WHERE p1.name = 'Nintendo Switch OLED';
--
--INSERT INTO products (name, price, image, quantity) VALUES ('Xbox Series X', 449.99, 'https://m.media-amazon.com/images/I/51ojzJk77qL._AC_UF1000,1000_QL80_.jpg', 12);
--INSERT INTO order_items (product_id, price, quantity) SELECT id, price, 1 FROM products p1 WHERE p1.name = 'Xbox Series X';
--
--
--UPDATE order_items as oi SET order_id = id FROM orders as o INNER JOIN oi.order_id = o.id;
--
--
--INSERT INTO orders(id) SELECT gen_random_uuid();
--
--
--INSERT INTO orders (total_price) SELECT price * quantity FROM order_items;