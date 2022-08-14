package jdbc;


import javax.imageio.stream.ImageInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class Test {


    public static void main(String[] args) throws SQLException {
        System.out.println("你好呀" +
                "");

    }

    public static void runTest() throws SQLException {
        Connection con = getConnection();
        Statement statement = con.createStatement();
        List<String> sqlList = new ArrayList<>();
        sqlList.add("select * from t_user");
        sqlList.add("update book set author = 'wj' where price = 100");
        for (String sql : sqlList) {
            boolean isResult = statement.execute(sql);
            if (isResult) {
                try (ResultSet res = statement.getResultSet()) {
                    showResultSet(res);
                }
            } else {
                int updateCount = statement.getUpdateCount();
                System.out.println(updateCount + "条记录收到影响。\n");
            }
        }

    }

    private static void showResultSet(ResultSet res) throws SQLException {
        ResultSetMetaData resultSetMetaData = res.getMetaData();
        int columnCount = resultSetMetaData.getColumnCount();
        for (int i = 1; i <= columnCount; i++) {
            System.out.print(resultSetMetaData.getColumnLabel(i)+" ");
        }
        System.out.println();
        while (res.next()) {
            for (int i=1;i<=columnCount;i++) {
                System.out.print(res.getString(i));
            }
            System.out.println();
        }
    }

    public static Connection getConnection() throws SQLException {

        System.setProperty("jdbc.drivers", "com.mysql.cj.jdbc.Driver");
        String url = "jdbc:mysql://127.0.0.1:3306/how2java?serverTimezone=GMT&characterEncoding=UTF-8";
        String username = "root";
        String password = "admin";
        return DriverManager.getConnection(url, username, password);
    }

}
