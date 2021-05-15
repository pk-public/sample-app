package com.pk.sample.dal;


import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Primary;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;
import org.testcontainers.containers.PostgreSQLContainer;

import javax.sql.DataSource;
import java.lang.annotation.*;
import java.time.Duration;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Inherited
@SpringBootTest
@ActiveProfiles("test")
@DirtiesContext
@Transactional
@Rollback
@Import(DbTest.DbTestConf.class)
public @interface DbTest {

    class DbTestConf {

        public static final String DATABASE_NAME = "test";
        private static final String USERNAME = "testUsername";
        private static final String PASSWORD = "testPassword";

        @Primary
        @Bean
        public DataSource dataSource(PostgreSQLContainer embeddedPostgresContainer) {
            return DataSourceBuilder.create()
                    .url(embeddedPostgresContainer.getJdbcUrl())
                    .driverClassName(org.postgresql.Driver.class.getName())
                    .username(USERNAME)
                    .password(PASSWORD)
                    .build();
        }

        @Bean // is autocloseable - spring will automatically shut down container
        public PostgreSQLContainer embeddedPostgresContainer() {
            final PostgreSQLContainer postgreSQLContainer = (PostgreSQLContainer)
                    new PostgreSQLContainer("postgres:11.1")
                            .withDatabaseName(DATABASE_NAME)
                            .withUsername(USERNAME)
                            .withPassword(PASSWORD)
                            .withStartupTimeout(Duration.ofMillis(500));
            postgreSQLContainer.start();
            return postgreSQLContainer;
        }
    }

}
