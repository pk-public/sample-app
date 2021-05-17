This project consists of two main modules:

- backend
- model
- frontend


#Backend Module
This module, build on top of spring-boot-web, is the server-side application, responsible for exposing data via REST API.

Building this module, launches postgres docker image (testcontainer), which is then migrated and used for jooq code generator.

Also, running tests on this module starts testcontainer postgres db

The build process is as follows:
1. port-allocator-maven-plugin allocates random port for build-time postgres instnace
2. docker-maven-plugin builds and starts docker container with running postgres
3. flyway-maven-plugin migrates postgres
4. jooq-codegen-maven generates jooq classes

#Model
This module contains all buisness related model entities, not dependan

This allows the business model to be technology-agnostic (e.g. db-agnostic).
Also, those are ubiquitous definition defined in order for seperate modules to communicate. 

#Frontend
This is Angular based frontend application, that handles web UI.

#Database
For this project, postgreSQL was chosen.
Database is managed b


#Configuring dev env

Sample dev env DB config.


```shell script
sudo -u postgres -i
psql
create user sampleapp with encrypted password 'sampleapp';
create database sampleapp;
grant ALL on DATABASE sampleapp to sampleapp ;
```

#ToDo 
- migrate mvn -> gradle
- introduce frontend tests
- dockerize and automate dev env ( e.g. db )
- separate migrations from backend app ( preper for multi node ) 
- configure jooq for automatic enum conversion
- seperate criteria model from db layer