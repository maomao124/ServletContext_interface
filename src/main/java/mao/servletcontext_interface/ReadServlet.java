package mao.servletcontext_interface;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Properties;

/**
 * Project name(项目名称)：ServletContext_interface
 * Package(包名): mao.servletcontext_interface
 * Class(类名): ReadServlet
 * Author(作者）: mao
 * Author QQ：1296193245
 * GitHub：https://github.com/maomao124/
 * Date(创建日期)： 2021/12/23
 * Time(创建时间)： 15:14
 * Version(版本): 1.0
 * Description(描述)： 无
 */

@WebServlet("/ReadServlet")
public class ReadServlet extends HttpServlet
{
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter writer = response.getWriter();
        // 获取相对路径中的输入流对象
        InputStream inputStream = getServletContext().getResourceAsStream("db.properties");
        // 获取输入流
        Properties properties = new Properties();
        // 加载
        properties.load(inputStream);
        // 获取文件中的内容
        String name = properties.getProperty("name");
        String url = properties.getProperty("url");
        String desc = properties.getProperty("desc");
        writer.write("用户名：" + name + "<br/>" + "地址：" + url + "<br/>" + "描述：" + desc + "<br/>");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        doGet(request, response);
    }
}
