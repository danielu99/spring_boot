INSERT INTO usuarios(username,password,enabled,nombre,apellido,email) VALUES('daniel','$2a$10$QikgNmW8NKRa1g4M67vLP.Nk8TZTClaJ6fH4pF6VAMupeRR/13GjK',1,'Daniel','Leon','daniel@correo.com');
INSERT INTO usuarios(username,password,enabled,nombre,apellido,email) VALUES('admin','$2a$10$u1wOJDlpuJlegvaZcD32/usdQO08e45fwvobICGuyG6q34XxFlo/i',1,'John','Doe','john.doe@bolsadeideas.com');

INSERT INTO roles(nombre) VALUES('ROLE_USER');
INSERT INTO roles(nombre) VALUES('ROLE_ADMIN');

INSERT INTO usuarios_to_roles (user_id,role_id) VALUES(1,1);
INSERT INTO usuarios_to_roles (user_id,role_id) VALUES(2,2);
INSERT INTO usuarios_to_roles (user_id,role_id) VALUES(2,1);
