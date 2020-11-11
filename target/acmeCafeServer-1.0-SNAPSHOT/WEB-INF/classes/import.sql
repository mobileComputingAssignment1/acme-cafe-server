INSERT INTO menu (id, timestamp, name, price) VALUES ('28449918-30cc-4197-9017-886b41cfe1ee', NOW(), 'coca-cola', 2);
INSERT INTO menu (id, timestamp, name, price) VALUES ('a93ad337-4fb9-4a5a-9bf8-c60b5beeb51a', NOW(), 'coca-cola zero', 2.5);
INSERT INTO menu (id, timestamp, name, price) VALUES ('69b4eb40-06ad-4260-baf2-7c16cbcb3461', NOW(), 'fanta', 2);
INSERT INTO menu (id, timestamp, name, price) VALUES ('781383aa-aa5e-4082-a646-00ea941b789b', NOW(), 'fanta zero', 2.5);
INSERT INTO menu (id, timestamp, name, price) VALUES ('e3c78620-c444-4689-98aa-d4687b804ef4', NOW(), 'super bock', 1.5);
INSERT INTO menu (id, timestamp, name, price) VALUES ('689f41f0-892a-490c-ad99-d4a236f1ef38', NOW(), 'bifana', 1.5);
INSERT INTO menu (id, timestamp, name, price) VALUES ('2ccff0d5-e126-4eaf-a199-9ad96bcfe808', NOW(), 'coffee', 1);



INSERT INTO users (id, timestamp, first_name, last_name, nif, credit_card, user_name, password, rsa_key) VALUES ('333f41f0-892a-490c-ad99-d4a236f1e333', NOW(), 'Joze', 'Gorisek', 281731640, '4797091646894574', 'gorisek', 'joze', '1rweadf');
INSERT INTO users (id, timestamp, first_name, last_name, nif, credit_card, user_name, password, rsa_key) VALUES ('fd9e546e-4327-414c-830f-6589e8db6e83', NOW(), 'Joao', 'Silva', 201726580, '4898351512560974', 'silva', 'joze', '1rweadf');
INSERT INTO users (id, timestamp, first_name, last_name, nif, credit_card, user_name, password, rsa_key) VALUES ('646c3dac-883c-46ba-94b7-9a8b44bea083', NOW(), 'Enrique', 'De Jong', 202841766, '4166830853062422', 'dejong', 'joze', '1rweadf');
INSERT INTO users (id, timestamp, first_name, last_name, nif, credit_card, user_name, password, rsa_key) VALUES ('d85712a4-f2af-4ded-858c-a90126d44726', NOW(), 'Francis', 'Kent', 218867433, '4801711109469895', 'kent', 'joze', '1rweadf');



INSERT INTO vouchers (id, timestamp, type, name) VALUES ('4ccf48df-82c2-4af0-b2c7-f99ad743c459', NOW(), 0, 'free coffee');
INSERT INTO vouchers (id, timestamp, type, name) VALUES ('d3d75981-2e2d-4e80-a616-0db48567b2b7', NOW(), 1, '5% discount');
INSERT INTO vouchers (id, timestamp, type, name) VALUES ('59c5bdfb-4944-4840-be44-3fecf603fe28', NOW(), 1, '5% discount');


INSERT INTO orders (id, timestamp, user_id, voucher_id, order_id, menu_items, total_price, receipt_id) VALUES ('4ccf48df-82c2-4af0-b2c7-f99ad743c459', NOW(), '333f41f0-892a-490c-ad99-d4a236f1e333', '4ccf48df-82c2-4af0-b2c7-f99ad743c459', '4ccf48df-82c2-4af0-b2c7-f99ad743c459', '1e36aebe-dc01-464c-9ae8-7ae696e2bd0f', 15.5, '4ccf48df-82c2-4af0-b2c7-f99ad743c459');




-- INSERT INTO order_menu_items (id, timestamp, order_id, menu_item_id, quantity) VALUES ('11662500-dbca-4c91-b7ac-059e9a3affa9', NOW(), '4ccf48df-82c2-4af0-b2c7-f99ad743c459', '1e36aebe-dc01-464c-9ae8-7ae696e2bd0f', 2);
-- INSERT INTO order_menu_items (id, timestamp, order_id, menu_item_id, quantity) VALUES ('b7fd2c69-68a9-4f57-b5b4-5f71e9715ff5', NOW(), '4ccf48df-82c2-4af0-b2c7-f99ad743c999', '689f41f0-892a-490c-ad99-d4a236f1ef38', 1);




