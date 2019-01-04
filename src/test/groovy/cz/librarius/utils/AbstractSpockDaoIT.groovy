package cz.librarius.utils

import org.h2.tools.Server
import org.hibernate.Session
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.ApplicationContext
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.test.annotation.Rollback
import org.springframework.test.context.ContextConfiguration
import org.springframework.transaction.annotation.Transactional
import spock.lang.Specification

import javax.persistence.EntityManager
import javax.persistence.PersistenceContext
import javax.sql.DataSource
import java.sql.SQLException

@Transactional
@Rollback(true)
@ContextConfiguration
abstract class AbstractSpockDaoIT extends Specification {

    Logger log = LoggerFactory.getLogger(getClass())

    @Autowired
    ApplicationContext applicationContext
    JdbcTemplate jdbcTemplate

    /**
     * JDBC executed statement counter, which use Hibernate internal Listener API
     * to get count of executed JDBC statements and batch statements
     */
    def statementsCounter = new ExecutedStatementsCounter()

    def setup() {
        session().addEventListeners(statementsCounter)
    }

    /**
     * Set the {@code DataSource}, typically provided via Dependency Injection.
     * <p>This method also instantiates the {@link #jdbcTemplate} instance variable.
     */
    @Autowired
    void setDataSource(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource)
    }

    @PersistenceContext
    EntityManager entityManager
    /**
     * Retrieves currently opened EntityManager instance.
     *
     * @return current EntityManager
     */
    def em() {
        entityManager
    }

    def session() {
        entityManager.unwrap(Session)
    }

    /**
     * Flush EntityManager
     */
    void flush() {
        entityManager.flush()
    }

    /**
     * Flush and clear EntityManager unit of work (first level cache).
     */
    void flushAndClearEntityManager() {
        entityManager.flush()
        entityManager.clear()
    }

    /**
     * Optionally starts H2 TCP server to allowing connecting to in-memory H2 DB from external client.
     *
     * <p/>
     * <strong>!!! Note, that this method has to be run within same JVM process !!!</strong>
     */
    void startH2TcpServer() {
        try {
            Server server = Server.createTcpServer().start()
            log.info("Server started and connection is open.")
            log.info("URL: jdbc:h2:" + server.getURL() + "/mem:test")
        } catch (SQLException e) {
            throw new IllegalStateException("Could not start H2 server", e)
        }
    }

}
