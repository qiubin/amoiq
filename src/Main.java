import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException {
        System.out.println("hello world");
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            List<User> users = new ArrayList<User>();
            con = JDBCUtil.getConnection();
            String sql = "select * from t_user";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            User u = null;
            while (rs.next()) {
                u = new User();
                u.setUsername(rs.getString("username"));
                u.setPassword(rs.getString("password"));
                u.setNickname(rs.getString("nickname"));
                users.add(u);
            }
            for (User ue : users) {
                System.out.println("NickName:" + ue.getNickname());
                System.out.println("password:" + ue.getPassword());
                System.out.println("username:" + ue.getUsername());
            }
            System.out.println("success!");
        }catch (SQLException exception){
            throw exception;
        }finally {
            JDBCUtil.close(rs);
            JDBCUtil.close(ps);
            JDBCUtil.close(con);
        }
    }
}
