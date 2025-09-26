USE shop_db;

CREATE TABLE products (
                          id BIGINT AUTO_INCREMENT PRIMARY KEY,
                          title VARCHAR(255),
                          image VARCHAR(500),
                          price DECIMAL(10,2),
                          old_price DECIMAL(10,2),
                          shop_name VARCHAR(255),
                          comment_count INT,
                          good_rate VARCHAR(10),
                          tags VARCHAR(255)
);
-- 插入测试数据
INSERT INTO products (title, image, price, old_price, shop_name, comment_count, good_rate, tags)
VALUES ('海尔三开门冰箱', 'https://img10.360buyimg.com/n7/jfs/...jpg', 1899, 2399, '京东自营', 3564, '98%', '自营,热卖');