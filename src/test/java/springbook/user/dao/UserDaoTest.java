package springbook.user.dao;

import org.junit.*;
import org.junit.runner.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import springbook.user.DuplicateUserIdException;
import springbook.user.domain.Level;
import springbook.user.domain.User;

import java.sql.SQLException;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/test-applicationContext.xml")
public class UserDaoTest {

    @Autowired
    UserDao dao;

    User user1;
    User user2;
    User user3;

    @Before
    public void setUp(){
        user1 = new User("gyumee", "박성철", "springno1", Level.BASIC, 1, 0);
        user2 = new User("leegw700", "이길원", "springno2", Level.SILVER, 55, 10);
        user3 = new User("bumjin", "박범진", "springno3", Level.GOLD, 100, 40);
    }

    @Test
	public void addAndGet() throws SQLException {

		dao.deleteAll();
		assertThat(dao.getCount(), is(0));

        dao.add(user1);
        dao.add(user2);
		assertThat(dao.getCount(), is(2));


        User target1 = dao.get(user1.getId());
        checkSameUser(target1, user1);

        User target2 = dao.get(user2.getId());
        checkSameUser(target2, user2);
    }


	@Test
    public void count() throws SQLException {


        dao.deleteAll();
        assertThat(dao.getCount(), is(0));

        dao.add(user1);
        assertThat(dao.getCount(), is(1));

        dao.add(user2);
        assertThat(dao.getCount(), is(2));

        dao.add(user3);
        assertThat(dao.getCount(), is(3));

    }


    @Test(expected = EmptyResultDataAccessException.class)
    public void getUserFailure() throws SQLException {
        dao.deleteAll();
        assertThat(dao.getCount(), is(0));

        dao.get("unKnown_id");
    }

    @Test
    public void getAll() throws Exception {
        dao.deleteAll();

        dao.add(user1);
        List<User> users1 = dao.getAll();
        assertThat(users1.size(), is(1));
        checkSameUser(users1.get(0), user1);

        dao.add(user2);
       List<User> users2 = dao.getAll();
       assertThat(users2.size(), is(2));
       checkSameUser(users2.get(0), user1);
        checkSameUser(users2.get(1), user2);

        dao.add(user3);
        List<User> users3 = dao.getAll();
        assertThat(users3.size(), is(3));
        checkSameUser(users3.get(0), user3);
        checkSameUser(users3.get(1), user1);
        checkSameUser(users3.get(2), user2);

    }

    private void checkSameUser(User user, User user1) {
        assertThat(user.getId(), is(user1.getId()));
        assertThat(user.getName(), is(user1.getName()));
        assertThat(user.getPassword(), is(user1.getPassword()));
        assertThat(user.getLevel(), is(user1.getLevel()));
        assertThat(user.getLogin(), is(user1.getLogin()));
        assertThat(user.getRecommend(), is(user1.getRecommend()));
    }

    @Test(expected = DuplicateUserIdException.class)
    public void duplicateKey() throws Exception {
        dao.deleteAll();

        dao.add(user1);
        dao.add(user1);

    }

    @Test
    public void update() throws Exception {
        dao.deleteAll();

        dao.add(user1);
        dao.add(user2);

        user1.setName("오민규");
        user1.setPassword("springno6");
        user1.setLevel(Level.GOLD);
        user1.setLogin(1000);
        user1.setRecommend(999);
        dao.update(user1);

        User user1update = dao.get(user1.getId());
        checkSameUser(user1update, user1);
        User user2update = dao.get(user2.getId());
        checkSameUser(user2update, user2);
    }
}
