//package cz.librarius.h2;
//
//import org.h2.tools.Server;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.context.event.ContextRefreshedEvent;
//import org.springframework.context.event.EventListener;
//
//import java.sql.SQLException;
//
//public class StartH2Listener {
//
//    private static boolean h2started = false;
//    private final Logger LOG = LoggerFactory.getLogger(getClass());
//
//    /**
//     * Start H2 server and log url to system log
//     *
//     * @param event spring context refresh event
//     */
//    @EventListener
//    public void handleContextRefresh(ContextRefreshedEvent event) {
//        if (!h2started) {
//            try {
//                Server server = Server.createTcpServer().start();
//                LOG.info("Server started and connection is open.");
//                LOG.info("URL: jdbc:h2:" + server.getURL() + "/mem:name_of_your_db");
//            } catch (SQLException e) {
//                throw new IllegalStateException("Could not start H2 server", e);
//            }
//            h2started = true;
//        }
//    }
//}