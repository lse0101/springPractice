package springbook.user.dao;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import springbook.user.DuplicateUserIdException;
import springbook.user.domain.Level;
import springbook.user.domain.User;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserDaoJdbc implements UserDao{
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplate;

	private static RowMapper<User> userMapper = new RowMapper<User>() {
		@Override
		public User mapRow(ResultSet rs, int rowNum) throws SQLException {
			User user = new User();
			user.setId(rs.getString("id"));
			user.setName(rs.getString("name"));
			user.setPassword(rs.getString("password"));
			user.setLevel(Level.valueOf(rs.getInt("level")));
			user.setLogin(rs.getInt("login"));
			user.setRecommend(rs.getInt("recommend"));

			return user;
		}
	};

	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
		this.dataSource = dataSource;
	}

	public void add(final User user) throws DuplicateUserIdException{
		try {
			jdbcTemplate.update("insert into users(id, name, password, level, login, recommend) values(?, ?, ?, ?, ?, ?)",
								user.getId(), user.getName(), user.getPassword(), user.getLevel().intValue(), user.getLogin(), user.getRecommend());
		} catch (DuplicateKeyException e) {
			throw new DuplicateUserIdException(e);
		}
	}

	public User get(String id) {
	    return jdbcTemplate.queryForObject("select * from users where id = ?", new Object[]{id}, userMapper);

	}

	public void deleteAll() {
		jdbcTemplate.update("delete from users");
	}


	public int getCount() {
		return jdbcTemplate.queryForInt("select count(*) from users");
	}

	@Override
	public void update(User user1) {
		this.jdbcTemplate.update("update users set name = ?, password = ?, level = ?, login = ?, recommend = ? where id = ?",
                                user1.getName(), user1.getPassword(), user1.getLevel().intValue(), user1.getLogin(), user1.getRecommend(), user1.getId());
	}


	public List<User> getAll() {
	    return jdbcTemplate.query("select * from users", userMapper);
	}
}
