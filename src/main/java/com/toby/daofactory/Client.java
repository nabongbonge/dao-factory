package com.toby.daofactory;

import com.toby.daofactory.config.DaoFactory;
import com.toby.daofactory.dao.UserDao;
import com.toby.daofactory.domain.User;

public class Client {
  public static void main(String[] args) {
    UserDao dao = new DaoFactory().userDao();

    dao.add(new User("wotjr210", "nayoung", "dkagh12"));

    User findUser = dao.get("wotjr210");
    System.out.println(findUser);

  }
}
