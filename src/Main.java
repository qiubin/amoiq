import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException {
        System.out.println("hello world");

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<User> users = new ArrayList<User>();
        con = JDBCUtil.getConnection();
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
        for(User ue:users){
            System.out.println("NickName:"+ue.getNickname());
            System.out.println("password:"+ue.getPassword());
            System.out.println("username:"+ue.getUsername());
        }

            //Class.forName("com.mysql.cj.jdbc.Driver");
            //System.out.println("数据驱动加载成功");
            System.out.println("success!");

            JDBCUtil.close(rs);
            JDBCUtil.close(ps);
            JDBCUtil.close(con);

    }
}
