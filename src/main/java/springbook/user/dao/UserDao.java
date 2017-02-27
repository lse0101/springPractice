package springbook.user.dao;

import springbook.user.domain.User;

import java.util.List;

/**
 * Created by lse0101 on 09/02/2017.
 */
public interface UserDao {
    void add(User user);
    User get(String id);
    List<User> getAll();
    void deleteAll();
    int getCount();
    void update(User user1);
}
