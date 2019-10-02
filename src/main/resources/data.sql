-- Insert Testing Data via a Transaction
BEGIN;

Insert into role (name) values ('BaseRole');
Insert into role (name) values ('Admin');

Insert into user (username, email, password) values ('Tester', 'Test@Cronus.com', '12345');
Insert into user (username, email, password) values ('Admin', 'Admin@Cronus.com', 'SecurePassword12345');


Insert into user_roles (users_id, roles_id) values (1, 1);
Insert into user_roles (users_id, roles_id) values (2, 1);
Insert into user_roles (users_id, roles_id) values (2, 2);

Insert into list (id_user_id) values (1);
Insert into list (id_user_id) values (2);

COMMIT;
