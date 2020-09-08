package bit.ss.surveysystem.common.utils;

import java.sql.*;

public class HiveUtils {
    private static String DriverName = "org.apache.hive.jdbc.HiveDriver";

    private static String ServerUrl = "jdbc:hive2://localhost:10000/default";
    private static String ServerUser = "hadoop";
    private static String ServerPwd = "hadoop";

    private  static Connection hiveConn;
    private  static Statement hiveStmt;

    public static int doSql(String sql) throws SQLException {
        try {
            Class.forName(DriverName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return -1;
        }
        hiveConn = DriverManager.getConnection(ServerUrl, ServerUser, ServerPwd);
        hiveStmt = hiveConn.createStatement();
        ResultSet hqlOutput = hiveStmt.executeQuery(sql);

        hqlOutput.close();
        hiveStmt.close();
        hiveConn.close();
        return 1;
    }
}
