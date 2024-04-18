import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HelloServlet extends  HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
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

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}
