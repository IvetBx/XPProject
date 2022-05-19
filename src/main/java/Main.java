import dbConnect.DbContext;
import org.postgresql.ds.PGSimpleDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class Main {

    public static void main(String[] args) {
        PGSimpleDataSource dataSource = new PGSimpleDataSource();

        dataSource.setServerName("");
        dataSource.setPortNumber(5432);
        dataSource.setDatabaseName("");
        dataSource.setUser("");
        dataSource.setPassword("");

        InputReader inputReader = new InputReader();
        try (Connection connection = dataSource.getConnection()) {
            inputReader.read(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbContext.clear();
        }

    }

}
