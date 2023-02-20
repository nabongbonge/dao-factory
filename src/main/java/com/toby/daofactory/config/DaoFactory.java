package com.toby.daofactory.config;

import com.toby.daofactory.dao.UserDao;
import com.toby.daofactory.repository.MConnectionMaker;

public class DaoFactory {
  public UserDao userDao() {
    return new UserDao(new MConnectionMaker());
  }
}
