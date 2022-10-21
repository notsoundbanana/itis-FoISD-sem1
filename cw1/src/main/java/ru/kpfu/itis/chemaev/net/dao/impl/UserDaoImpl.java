package ru.kpfu.itis.chemaev.net.dao.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.kpfu.itis.chemaev.net.dao.UserDao;
import ru.kpfu.itis.chemaev.net.model.User;
import util.PostgresConnectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class UserDaoImpl implements UserDao {

    //language=SQL
    private static final String SQL_SELECT_ALL_USERS = "select * from users;";

    //language=SQL
    private static final String SQL_SELECT_BY_ID = "select * from users where id = ?;";

    private static final String SQL_SAVE = "INSERT into users (login, firstname, lastname, password) VALUES (?, ?, ?, ?);";

    //language=SQL
    public static final String SQL_SELECT_BY_LOGIN = "select * from users where login = ?;";

    //language=SQL
    public static final String SQL_UPDATE = "UPDATE users SET firstname = ?, lastname = ?, password = ? where login = ?;";

    private static final Logger LOGGER = LoggerFactory.getLogger(UserDaoImpl.class);
    private final Connection connection = PostgresConnectionUtil.getConnection();

    private static final Function<ResultSet, User> userMapper = resultSet -> {
        try {
            return new User(
                    resultSet.getInt("id"),
                    resultSet.getString("login"),
                    resultSet.getString("firstname"),
                    resultSet.getString("lastname"),
                    resultSet.getString("password"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    };

    @Override
    public User get(int id) {
        try {
            PreparedStatement statement = connection.prepareStatement(SQL_SELECT_BY_ID);
            statement.setInt(1, id);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return userMapper.apply(resultSet);
                } else {
                    return null;
                }
            }

        } catch (SQLException e) {
            return null;
        }
    }

    @Override
    public List<User> getAll() {
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL_SELECT_ALL_USERS);

            List<User> users = new ArrayList<>();

            while (resultSet.next()) {
                User user = userMapper.apply(resultSet);
                users.add(user);
            }

            return users;
        } catch (SQLException e) {
            return List.of();
        }
    }

    @Override
    public void save(User user) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_SAVE);
            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2, user.getFirstName());
            preparedStatement.setString(3, user.getLastName());
            preparedStatement.setString(4, user.getPassword());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(User user) {

        try {
            PreparedStatement statement = connection.prepareStatement(SQL_UPDATE);

            statement.setString(1, user.getFirstName());
            statement.setString(2, user.getLastName());
            statement.setString(3, user.getPassword());
            statement.setString(4, user.getLogin());

             statement.executeUpdate();


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public User get(int id) {
        try {
            PreparedStatement statement = connection.prepareStatement(SQL_SELECT_BY_ID);
            statement.setInt(1, id);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return userMapper.apply(resultSet);
                } else {
                    return null;
                }
            }

        } catch (SQLException e) {
            return null;
        }
    }
}
