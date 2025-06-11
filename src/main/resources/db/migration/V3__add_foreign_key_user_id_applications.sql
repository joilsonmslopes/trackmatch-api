ALTER TABLE tb_applications
ADD CONSTRAINT fk_applications_user
FOREIGN KEY (user_id) REFERENCES tb_users(id);