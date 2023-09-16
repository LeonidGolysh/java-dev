package org.example;

import org.apache.log4j.Logger;
import org.example.data.Member;
import org.example.data.OsbbCrud;

import java.io.IOException;
import java.sql.SQLException;
/**
 * Hello world!
 *
 */
public class App {

    private static final Logger logger = Logger.getLogger(App.class);

    public static void main( String[] args )
    {
        logger.info("The program is started");
        try (OsbbCrud crud = new OsbbCrud().init()){
            for (Member member : crud.getMembersWithAutoNotAllowed()) {
                final StringBuffer sb = new StringBuffer();
                sb.append(member.getId())
                        .append(" : ")
                        .append(member.getName())
                        .append(" : ")
                        .append(member.getAutoAllow())
                        .append(" : ")
                        .append(member.getRole())
                        .append(" : ")
                        .append(member.getFlatNumber())
                        .append(" : ")
                        .append(member.getFlatLevel())
                        .append(" : ")
                        .append(member.getFlatSquare())
                        .append(" : ")
                        .append(member.getAdress())
                        .append("\r\n");
                System.out.println(sb);
            }

        } catch (SQLException | IOException e) {
            logger.fatal(e);
        }
    }
}
