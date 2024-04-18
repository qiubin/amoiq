import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Main {

    private static String username = "itat";
    private static String password = "123";
    private static String database = "itat_emp";
    public static Connection getConnection() {
        Connection con = null;
        String url = "jdbc:mysql://localhost:3306/"+database;
        try {
            con = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return con;
    }
    public static void close(Connection con) {
        try {
            if(con!=null) con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void close(PreparedStatement ps) {
        try {
            if(ps!=null) ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void close(ResultSet rs) {
        try {
            if(rs!=null) rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws SQLException {
        System.out.println("hello world");
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<User> users = new ArrayList<User>();
        con = getConnection();
        String sql = "select * from t_user";
        ps = con.prepareStatement(sql);
        rs = ps.executeQuery();
        User u = null;
        while(rs.next()) {
            u = new User();
            u.setUsername(rs.getString("username"));
            u.setPassword(rs.getString("password"));
            u.setNickname(rs.getString("nickname"));
            users.add(u);
        }

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("数据驱动加载成功");
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }
    }
}
