package springbook.user.dao;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by lse0101 on 03/02/2017.
 */
public class JdbcContext {

   private DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void executeSql(final String sql) throws SQLException {
        workWithStatementStrategy(
                new StatementStrategy() {
                    @Override
                    public PreparedStatement makePreparedStatement(Connection c) throws SQLException {
                        return c.prepareStatement(sql);
                    }
                }
        );
    }

    public void executeSql(final String sql, final Object[] params) throws SQLException {
       workWithStatementStrategy(
               new StatementStrategy() {
                   @Override
                   public PreparedStatement makePreparedStatement(Connection c) throws SQLException {
                       PreparedStatement psmt = c.prepareStatement(sql);
                       int i = 0;

                       for (Object param : params) {
                           if (param.getClass() == Integer.class) {
                               psmt.setInt(++i, Integer.parseInt(param.toString()));
                           }

                           if (param.getClass() == String.class) {
                               psmt.setString(++i, param.toString());
                           }
                       }

                       return psmt;
                   }
               }
       );
    }

    private void workWithStatementStrategy(StatementStrategy  stmt) throws SQLException {

        Connection c = null;
        PreparedStatement ps = null;

        try {
            c = dataSource.getConnection();

            ps = stmt.makePreparedStatement(c);
            ps.executeUpdate();

        } catch (SQLException e) {
            throw e;
        } finally {
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

            try {
                c.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
