package Implementations;

import Connection.ConnectionManager;
import Entities.DalUser;
import Interfaces.CRUD_User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class UserToDataBase implements CRUD_User {

    private Connection con;
    PreparedStatement preparedStatement;
    ResultSet rs;

    @Override
    public void create(DalUser user) {
        try {
            con = ConnectionManager.getConnection();
            String query = "INSERT INTO gbuser(Username,UserPassword,Avatar,FirstName,LastName, UserId, Teacher)" +
                    "VALUES(?,?,?,?,?,?,?)";
            preparedStatement = con.prepareStatement(query);
            preparedStatement.setString(1, user.getUserName());
            preparedStatement.setString(2, user.getPassWord());
            preparedStatement.setString(3, user.getAvatarUrl());
            preparedStatement.setString(4, user.getFirstName());
            preparedStatement.setString(5, user.getLastName());
            preparedStatement.setInt(6, user.getPlayerId());
            preparedStatement.setBoolean(7, user.getTeacherId());

            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            try {
                preparedStatement.close();
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public DalUser read(String username, String passWord) {
        try {
            con = ConnectionManager.getConnection();
            String query = "SELECT UserId,UserName,FirstName,LastName,Score,CoinAmount,Teacher,Avatar FROM GBUser Where Username = \"" + username + "\" AND UserPassword = \"" + passWord + "\"";
            preparedStatement = con.prepareStatement(query);
            rs = preparedStatement.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("UserId");
                String userName = rs.getString("UserName");
                int score = rs.getInt("Score");
                int coinAmount = rs.getInt("CoinAmount");
                boolean teacher = rs.getBoolean("Teacher");
                String avatar = rs.getString("Avatar");
                String firstName = rs.getString("FirstName");
                String lastName = rs.getString("LastName");

                return new DalUser(id, userName, passWord, firstName, lastName, teacher, coinAmount, score, avatar);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            try {
                preparedStatement.close();
                rs.close();
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public void update(DalUser user) {
        try {
            con = ConnectionManager.getConnection();
            String query = "Update [User] SET [UserName] = ?,[TeacherId] = ?, [Avatar] = ? " +
                    "WHERE [UserId] = ?";
            preparedStatement = con.prepareStatement(query);
            preparedStatement.setString(1, user.getUserName());
            preparedStatement.setBoolean(2, user.getTeacherId());
            preparedStatement.setInt(3, user.getPlayerId());
            preparedStatement.setString(4, user.getAvatarUrl());

            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            try {
                preparedStatement.close();
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void delete(DalUser user) {
        try {
            con = ConnectionManager.getConnection();
            String query = "DELETE FROM [User]" +
                    "WHERE [UserId] = ?";
            preparedStatement = con.prepareStatement(query);
            preparedStatement.setInt(1, user.getPlayerId());
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            try {
                preparedStatement.close();
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        }

    public void UpdatePlayerInGameAttributes(DalUser player) {
        try {
            con = ConnectionManager.getConnection();
            String query = "Update [User] SET [Score] = ?, [CoinAmount] = ?" +
                    "WHERE [UserId] = ?";
            preparedStatement = con.prepareStatement(query);
            preparedStatement.setInt(1, player.getScore());
            preparedStatement.setInt(2, player.getCoins());
            preparedStatement.setInt(3, player.getPlayerId());
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            try {
                preparedStatement.close();
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
