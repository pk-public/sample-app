Simple App responsible for exposing transfer data via REST API.

This project consists of two main modules:

- backend
- model


# Backend Module
This module, build on top of spring-boot-web, is the RESTfull server-side application, responsible for exposing data via REST API.

Building this module, launches postgres docker image (testcontainer), which is then migrated (flyway) and used for code generation (jooq) 

Also, running tests on this module starts testcontainer postgres db, so that database does not need to be provided 

The build process is as follows:
1. port-allocator-maven-plugin allocates random port for build-time postgres instnace
2. docker-maven-plugin builds and starts docker container with running postgres
3. flyway-maven-plugin migrates postgres
4. jooq-codegen-maven generates jooq classes

# Model
This module contains all buisness related model entities, not dependant on web nor db layers.

# Database
For this project, PostgreSQL was chosen.

# Configuring dev env
Sample dev env DB config. Properties provided by springs default spring.datasource.* properties

```shell script
sudo -u postgres -i
psql
create user sampleapp with encrypted password 'sampleapp';
create database sampleapp;
grant ALL on DATABASE sampleapp to sampleapp ;
```

# ToDo 
- dockerize and automate dev env ( e.g. db )
- separate migrations from backend app ( preper for multi node ) 
- test all conditions
- seperate integration from unit tests
- shared docker DB instance on mvn tests (and maybe even build ?)
