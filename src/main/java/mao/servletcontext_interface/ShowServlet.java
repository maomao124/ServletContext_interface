package mao.servletcontext_interface;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Project name(项目名称)：ServletContext_interface
 * Package(包名): mao.servletcontext_interface
 * Class(类名): ShowServlet
 * Author(作者）: mao
 * Author QQ：1296193245
 * GitHub：https://github.com/maomao124/
 * Date(创建日期)： 2021/12/23
 * Time(创建时间)： 14:54
 * Version(版本): 1.0
 * Description(描述)： 读取 Web 应用下的资源文件
 * 在实际开发中，有时会需要读取 Web 应用中的一些资源文件，如配置文件和日志文件等。为此，ServletContext 接口定义了一些读取 Web 资源的方法
 * 返回值类型	方法	方法描述
 * Set	getResourcePaths(String path)	返回一个 Set 集合，该集合中包含资源目录中的子目录和文件的名称。
 * String 	getRealPath(String path) 	返回资源文件的真实路径（文件的绝对路径）。
 * URL 	getResource(String path)	返回映射到资源文件的 URL 对象。
 * InputStream	getResourceAsStream(String path)	返回映射到资源文件的 InputStream 输入流对象。
 */

@WebServlet("/ShowServlet")
public class ShowServlet extends HttpServlet
{
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        // 获取ServletContext中存放的count属性（即页面的访问次数）
        ServletContext servletContext=this.getServletContext();

        Integer count = (Integer) servletContext.getAttribute("count");
        // 向页面输出
        response.setContentType("text/html;charset=UTF-8");
        // 若CountServlet已被访问
        if (count != null)
        {
            response.getWriter().write("<h2>该网站一共被访问了" + count + "次</h2>");
        }
        else
        {
            // 若CountServlet未被访问，提示先访问CountServlet
            response.getWriter().write("<h3>请先访问 CountServlet</h3>");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        doGet(request, response);
    }
}
