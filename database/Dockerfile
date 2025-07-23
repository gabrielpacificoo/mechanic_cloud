FROM mysql:latest
WORKDIR /
ENV MYSQL_ROOT_PASSWORD=urubu100
COPY ./users.sql /docker-entrypoint-initdb.d/
COPY ./cloud.sql /docker-entrypoint-initdb.d/
COPY ./inserts.sql /docker-entrypoint-initdb.d/
EXPOSE 3306
