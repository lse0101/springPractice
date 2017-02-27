package springbook.user.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by lse0101 on 03/02/2017.
 */
public interface StatementStrategy {
    public PreparedStatement makePreparedStatement(Connection c) throws SQLException;
}
