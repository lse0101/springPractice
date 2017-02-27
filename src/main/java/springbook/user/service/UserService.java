package springbook.user.service;

import springbook.user.domain.User;

/**
 * Created by lse0101 on 21/02/2017.
 */
public interface UserService {
    void add(User user);
    void upgradeLevels();
}
