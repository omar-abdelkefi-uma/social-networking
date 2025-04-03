ALTER TABLE user ADD UNIQUE (username);
INSERT INTO user (
    username, password, fullname, dateofbirth, gender, cin, city, phone, postal_code, type, lastlogin, isactive
) VALUES (
    'test@gmail.com', '$2a$10$IDuaB3EdhVI8UTnrCPpF9OUXW1IePTR/TlhRKu1hSUZnT02Kgo0/q', 'Default User', '2000-01-01', 'Other', '1234567890', 'Default City', 1234567890, '00000', 'regular', NULL, TRUE
);
