import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HelloServlet extends  HttpServlet{


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
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

            resp.setContentType("text/html;charset=UTF-8");
            PrintWriter out = resp.getWriter();
            out.println("<html>");
            out.println("<body>");
            //获取上下文路径
            out.println("<h1>"+req.getContextPath()+"</h1>");
            //获取绝对路径
            out.println("<h5>"+req.getSession().getServletContext().getRealPath("/")+"</h5>");
            //获取请求参数
            String username = req.getParameter("username");
            String password = req.getParameter("password");
            out.println("<h1>hello:"+username+",password:"+password+"</h1>");
            Enumeration<String> enu = req.getParameterNames();
            while(enu.hasMoreElements()) {
                out.println(enu.nextElement()+"<br/>");
            }
            for(int i=0;i<10;i++) {
                out.println("<h1>"+i+"</h1>");
            }
            out.println("</body>");
            out.println("</html>");
        }
        catch (SQLException e){
            e.printStackTrace();
        }finally {
            JDBCUtil.close(rs);
            JDBCUtil.close(ps);
            JDBCUtil.close(con);
        }

}

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}
