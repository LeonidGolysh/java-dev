package org.example.data;

import org.apache.log4j.Logger;
import org.flywaydb.core.Flyway;

import java.io.Closeable;
import java.io.IOException;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;

import static org.example.Config.*;

public class OsbbCrud implements Closeable {
    private static final Logger logger = Logger.getLogger(OsbbCrud.class);

    private Connection conn = null;

    private static final String sqlMembersWithAutoNotAllowedQuery =
            "SELECT \n" +
            "members.`id`,\n" +
            "tenants.`name`,\n" +
            "tenants.`auto_allow`,\n" +
            "members_role.`role`,\n" +
            "flats.`number` AS 'flat_number',\n" +
            "flats.`level` AS 'flat_level',\n" +
            "flats.`square` AS 'flat_square',\n" +
            "buildings.`adress`\n" +
            "\n" +
            "FROM `tenants`\n" +
            "JOIN `members_role` ON `tenants`.`member_id` = `members_role`.`id` \n" +
            "JOIN `members` ON `tenants`.`member_id` = `members`.`id`\n" +
            "JOIN `flats` ON `tenants`.`flat_id` = `flats`.`id`\n" +
            "JOIN `buildings` ON `flats`.`building_id` = `buildings`.`id`\n" +
            "\n" +
            "WHERE NOT tenants.auto_allow;";

    private void fwMigration () {
        logger.debug("Flyway migration execute");

        Flyway.configure()
                .dataSource(jdbcUrl, username, password)
                .locations("classpath:flyway.scripts")
                .load()
                .migrate();
    }

    public OsbbCrud init() throws SQLException {
        logger.info("Crud has initialized");
        fwMigration();

        conn = DriverManager.getConnection(jdbcUrl, username, password);
        return this;
    }

    @Override
    public void close() throws IOException {
        try {
            conn.close();
            conn = null;
        } catch (SQLException e) {
            throw new IOException(e);
        }
    }

    public List<Member> getMembersWithAutoNotAllowed() throws SQLException {
        logger.trace("Call getting members with auto not allowed method");

        final List<Member> result = new LinkedList<>();
        try (PreparedStatement preparedStatement = conn.prepareStatement(sqlMembersWithAutoNotAllowedQuery)) {
            final ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                result.add(new Member()
                        .setId(resultSet.getInt("id"))
                        .setName(resultSet.getString("name"))
                        .setAutoAllow(resultSet.getInt("auto_allow"))
                        .setRole(resultSet.getString("role"))
                        .setFlatNumber(resultSet.getInt("flat_number"))
                        .setFlatLevel(resultSet.getInt("flat_level"))
                        .setFlatSquare(resultSet.getInt("flat_square"))
                        .setAdress(resultSet.getString("adress")));
            }
        }
        return result;
    }
}
