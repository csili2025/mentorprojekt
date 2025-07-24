package edu.itplus.bibliosping.backend.repository.JDBC;

import edu.itplus.bibliosping.backend.model.User;
import edu.itplus.bibliosping.backend.repository.UserDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
@Profile("JDBC")
public class JdbcUserDAO implements UserDAO {
    @Autowired
    private Logger LOG;
    @Autowired
    private ConnectionManager connectionManager;
    @Override
    public User findByID(Long id) {
        Connection conn = null;
        try {
            conn = connectionManager.getConnection();
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM users WHERE id=?");
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                User result = new User();
                result.setId(rs.getLong("id"));
                result.setUuid(rs.getString("uuid"));
                result.setUsername(rs.getString("username"));
                result.setPassword(rs.getString("password"));
                return result;
            }
            return null;
        } catch (SQLException e) {
            LOG.error("ERROR: findById",e);
            throw new RuntimeException("ERROR: findById",e);
        } finally {
            connectionManager.getConnection();
        }
    }

    @Override
    public User findbyUsername(String username) {
        Connection conn=null;
        try {
            conn = connectionManager.getConnection();
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM users WHERE username=?");
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();

            if(rs.next()) {
                User result = new User();
                result.setId(rs.getLong("id"));
                result.setUuid(rs.getString("uuid"));
                result.setUsername(rs.getString("username"));
                result.setPassword(rs.getString("password"));
                return result;
            }
            return null;
        }
        catch(SQLException e) {
            LOG.error("ERROR: findbyUsername", e);
            throw new RuntimeException("ERROR: findbyUsername", e);
        }
        finally{
            connectionManager.getConnection();
        }
    }

    @Override
    public User create(User user) {
        Connection conn = null;
        try {
            conn = connectionManager.getConnection();
            PreparedStatement ps = conn.prepareStatement("INSERT INTO users(uuid, username, password) VALUES (?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, user.getUuid());
            ps.setString(2, user.getUsername());
            ps.setString(3, user.getPassword());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            rs.next();
            user.setId(rs.getLong(1));
            return user;
        } catch (SQLException e) {
            LOG.error("ERROR: create", e);
            throw new RuntimeException("ERROR: create", e);
        } finally {
            connectionManager.getConnection();
        }

    }

    @Override
    public void update(User user) {
        Connection conn = null;
        try{
            conn = connectionManager.getConnection();
            PreparedStatement prepstate = conn.prepareStatement("UPDATE users SET uuid = ?, username = ?, password = ? WHERE id = ?");
            prepstate.setString(1, user.getUuid());
            prepstate.setString(2, user.getUsername());
            prepstate.setString(3, user.getPassword());
            prepstate.setLong(4, user.getId());
            prepstate.executeUpdate();
        } catch (SQLException e) {
            LOG.error("ERROR: update", e);
            throw new RuntimeException("Error: update: ",e);
        }finally{
            connectionManager.getConnection();
        }
    }

    @Override
    public void delete(User user) {
        Connection conn = null;
        try{
            conn = connectionManager.getConnection();
            PreparedStatement prep = conn.prepareStatement("DELETE FROM users WHERE id = ?");
            prep.setLong(1, user.getId());
            prep.executeUpdate();
        } catch (SQLException e) {
            LOG.error("ERROR: delete", e);
            throw new RuntimeException("ERROR: delete: ",e);
        } finally {
            connectionManager.returnConnection(conn);
        }
    }

    @Override
    public List<User> findAll() {
        Connection conn=null;
        List<User> userList=new ArrayList<>();
        try{
            conn=connectionManager.getConnection();
            PreparedStatement prep = conn.prepareStatement("SELECT * FROM users");
            ResultSet rs = prep.executeQuery();
            while(rs.next()){
                User user=new User();
                user.setId(rs.getLong("id"));
                user.setUuid(rs.getString("uuid"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                userList.add(user);
            }
            return userList;
        }
        catch(SQLException e){
            LOG.error("ERROR: findAll", e);
            throw new RuntimeException("ERROR: findAll", e);
        }
        finally{
            connectionManager.returnConnection(conn);
        }
    }
}
