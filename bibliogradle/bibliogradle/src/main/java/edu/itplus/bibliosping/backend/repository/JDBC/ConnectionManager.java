package edu.itplus.bibliosping.backend.repository.JDBC;

import edu.itplus.bibliosping.backend.utils.PropertyProvider;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.LinkedList;

@Component
public class ConnectionManager {
    private LinkedList<Connection> pool;
    @Autowired
    private Logger LOG;

    @Autowired
    private PropertyProvider prop;

    @PostConstruct
    public void postConstruct(){
        pool = new LinkedList<>();
        try {
            Class.forName(prop.getProperty("JDBC_DRIVER"));
            for (int i = 0; i < Integer.parseInt(prop.getProperty("POOL_SIZE")); i++) {
                pool.add(DriverManager.getConnection(prop.getProperty("JDBC_URL"), prop.getProperty("JDBC_USER"), prop.getProperty("JDBC_PASSWORD")));
            }
            LOG.info("Pool initialized successfully");
        } catch (ClassNotFoundException | SQLException e) {
            LOG.error("Pool initialized error",e);
            throw new RuntimeException("Pool initialized successfully",e);
        }
    }

    public Connection getConnection() {
        if (pool.isEmpty()) {
            return null;
        }
        return pool.pop();
    }

    public void returnConnection(Connection conn) {
        if (pool.size() < Integer.parseInt(prop.getProperty("POOL_SIZE"))) {
            pool.push(conn);
        }
    }

}
