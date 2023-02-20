package com.toby.daofactory.dao;

import com.toby.daofactory.domain.User;
import com.toby.daofactory.repository.ConnectionMaker;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {
  private ConnectionMaker connectionMaker;

  public UserDao(ConnectionMaker connectionMaker) {
    this.connectionMaker = connectionMaker;
  }

  public void add(User user) {
    Connection conn = null;
    PreparedStatement psmt = null;
    try {
      conn = connectionMaker.makeConnection();

      psmt = conn.prepareStatement("insert into users (id, name, password) values (?, ?, ?)");
      psmt.setString(1, user.getId());
      psmt.setString(2, user.getName());
      psmt.setString(3, user.getPassword());
      psmt.executeUpdate();

    } catch (SQLException e) {
      throw new RuntimeException(e);
    } catch (ClassNotFoundException e) {
      throw new RuntimeException(e);
    } finally {

      if (psmt != null) {
        try {
          psmt.close();
        } catch (SQLException e) {
          throw new RuntimeException(e);
        }
      }

      if (conn != null) {
        try {
          conn.close();
        } catch (SQLException e) {
          throw new RuntimeException(e);
        }
      }
    }
  }

  public User get(String id) {
    Connection conn = null;
    PreparedStatement psmt = null;
    ResultSet rs = null;

    try {
      conn = connectionMaker.makeConnection();

      psmt = conn.prepareStatement("select * from users where id = ?");
      psmt.setString(1, id);

      rs = psmt.executeQuery();
      rs.next();

      return new User(rs.getString("id"), rs.getString("name"), rs.getString("password"));
    } catch (SQLException e) {
      throw new RuntimeException(e);
    } catch (ClassNotFoundException e) {
      throw new RuntimeException(e);
    } finally {
      if (rs != null) {
        try {
          rs.close();
        } catch (SQLException e) {
          throw new RuntimeException(e);
        }
      }

      if (psmt != null) {
        try {
          psmt.close();
        } catch (SQLException e) {
          throw new RuntimeException(e);
        }
      }

      if (conn != null) {
        try {
          conn.close();
        } catch (SQLException e) {
          throw new RuntimeException(e);
        }
      }
    }
  }
}
