package org.example;

//import java.util.logging.Logger;
import org.apache.log4j.Logger;
import org.flywaydb.core.Flyway;

import static org.example.Config.*;

public class FlywayMigration {
    private static final Logger logger = Logger.getLogger(FlywayMigration.class);
    public void fwMigration () {
        logger.debug("Flyway migration execute");

        Flyway.configure()
                .dataSource(jdbcUrl, username, password)
                .locations("classpath:flyway.scripts")
                .load()
                .migrate();
    }

}
