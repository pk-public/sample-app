This project consists of two main modules:

- backend
- frontend

#General

The build process is as follows:
1. port-allocator-maven-plugin allocates random port for build-time postgres instnace
2. docker-maven-plugin builds and starts docker container with running postgres
3. flyway-maven-plugin migrates postgres
4. jooq-codegen-maven generates jooq classes
 

#Backend Module
This module, build on top of spring-boot-web, is the server-side application, responsible for exposing data via REST API.
Building this module, generates Database Layer Jooq code.


Sample DB cration script:
```shell script
sudo -u postgres -i
psql
create user sampleapp with encrypted password 'sampleapp';
create database sampleapp;
grant ALL on DATABASE sampleapp to sampleapp ;
```

#Frontend
This is Angular based frontend application, that handles web UI.

#Database
For this project, postgreSQL was chosen.
Database is managed b

#ToDo
- seperate development (build-time) database from runtime. Migrate it to in-memory DB such as  
- migrate mvn -> gradle