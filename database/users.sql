# grant_root_access.sql
ALTER USER 'root'@'localhost' IDENTIFIED BY 'urubu100';
CREATE USER 'root'@'%' IDENTIFIED BY 'urubu100';
GRANT ALL PRIVILEGES ON *.* TO 'root'@'%' WITH GRANT OPTION;
FLUSH PRIVILEGES;