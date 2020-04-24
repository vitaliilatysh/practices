package practice8.db;

import practice8.db.entity.Group;
import practice8.db.entity.User;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class DBManager {
    private static DBManager dbManager;

    private DBManager() {
        getConnection();
    }

    public static synchronized DBManager getInstance() {
        if (dbManager == null) {
            dbManager = new DBManager();
        }
        return dbManager;
    }

     static Connection getConnection() {
        Properties prop = new Properties();
        Connection connection = null;
        FileInputStream in = null;
        try {
            in = new FileInputStream(new File("db.properties").getAbsolutePath());
            prop.load(in);

            String userName = prop.getProperty("db.user");
            String password = prop.getProperty("db.password");

            connection = DriverManager.getConnection(prop.getProperty("db.jdbcUrl"), userName, password);
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }

        return connection;
    }

    public void insertUser(User user) {
        String userName = user.getLogin();
        try (Connection connection = DBManager.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO users (login) VALUES (?)");
            statement.setString(1, userName);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public User getUser(String userName) {
        User resultUser = new User();
        try (Connection connection = DBManager.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("select * from users where login=?");
            statement.setString(1, userName);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                resultUser.setId(resultSet.getInt("id"));
                resultUser.setLogin(resultSet.getString("login"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultUser;
    }

    public List<User> findAllUsers() {
        List<User> userList = new ArrayList<>();

        String sql = "select * from users";
        try (Connection connection = DBManager.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt("id"));
                user.setLogin(resultSet.getString("login"));
                userList.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return userList;
    }

    public List<Group> getUserGroups(User user) {
        List<Group> groupList = new ArrayList<>();

        try (Connection connection = DBManager.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("select * from groups inner join users_groups on groups.id = users_groups.group_id where users_groups.user_id=?");
            statement.setInt(1, user.getId());
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Group resultGroup = new Group();
                resultGroup.setName(resultSet.getString("name"));
                groupList.add(resultGroup);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return groupList;
    }

    public void insertGroup(Group group) {
        String groupName = group.getName();
        try (Connection connection = DBManager.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO groups (name) VALUES (?)");
            statement.setString(1, groupName);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Group getGroup(String groupName) {
        Group resultGroup = new Group();

        try (Connection connection = DBManager.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("select * from groups where name=?");
            statement.setString(1, groupName);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {

                resultGroup.setId(resultSet.getInt("id"));
                resultGroup.setName(resultSet.getString("name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultGroup;
    }

    public void deleteGroup(Group group) {
        try (Connection connection = DBManager.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("delete from groups where id=?");
            statement.setInt(1, group.getId());
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateGroup(Group group) {

        try (Connection connection = DBManager.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("UPDATE groups set name=? where id=?");
            statement.setString(1, group.getName());
            statement.setInt(2, group.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Group> findAllGroups() {
        List<Group> groupList = new ArrayList<>();
        String sql = "select * from groups";
        try (Connection connection = DBManager.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                Group group = new Group();
                group.setId(resultSet.getInt("id"));
                group.setName(resultSet.getString("name"));
                groupList.add(group);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return groupList;
    }

    public void setGroupsForUser(User user, Group... groupsName) {
        String sql = "insert into users_groups values (?,?)";

        Connection connection = null;
        try {
            connection = DBManager.getConnection();
            connection.setAutoCommit(false);
            PreparedStatement statement = connection.prepareStatement(sql);
            User foundUser = getUser(user.getLogin());

            for (Group group : groupsName) {
                Group foundGroup = getGroup(group.getName());
                statement.setInt(1, foundUser.getId());
                statement.setInt(2, foundGroup.getId());
                statement.executeUpdate();
            }

            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }
}
