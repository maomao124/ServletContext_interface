package mao.servletcontext_interface;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

/**
 * Project name(项目名称)：ServletContext_interface
 * Package(包名): mao.servletcontext_interface
 * Class(类名): ReadContextServlet
 * Author(作者）: mao
 * Author QQ：1296193245
 * GitHub：https://github.com/maomao124/
 * Date(创建日期)： 2021/12/23
 * Time(创建时间)： 14:32
 * Version(版本): 1.0
 * Description(描述)：
 * Servlet 容器启动时，会为每个 Web 应用（webapps 下的每个目录都是一个 Web 应用）创建一个唯一的 ServletContext 对象，
 * 该对象一般被称为“Servlet 上下文”。
 * ServletContext 对象的生命周期从 Servlet 容器启动时开始，到容器关闭或应用被卸载时结束。
 * Web 应用中的所有 Servlet 共享同一个 ServletContext 对象，不同 Servlet 之间可以通过 ServletContext 对象实现数据通讯，
 * 因此 ServletContext 对象也被称为 Context 域对象。
 * 获得 ServletContext 对象
 * 获得 ServletContext 对象有以下 4 种方式：
 * 1. 通过 GenericServlet 提供的 getServletContext() 方法
 * //通过 GenericServlet的getServletContext方法获取ServletContext对象
 * ServletContext servletContext = this.getServletContext();
 * 2. 通过 ServletConfig 提供的 getServletContext() 方法
 * //通过 ServletConfig的 getServletContext方法获取ServletContext对象
 * ServletContext servletContext = this.getServletConfig().getServletContext();
 * 3. 通过 HttpSession 提供的 getServletContext() 方法
 * //通过 HttpSession的 getServletContext方法获取ServletContext对象
 * ServletContext servletContext = req.getSession().getServletContext();
 * 4. 通过 HttpServletRequest 提供的 getServletContext() 方法
 * //通过 HttpServletRequest的 getServletContext方法获取ServletContext对象
 * ServletContext servletContext = req.getServletContext();
 * <p>
 * 调用接口中方法获取初始化参数
 * Servlet 容器启动时，会为容器内每个 Web 应用创建一个 ServletContext 对象，并将 <context-param>
 * 元素中的上下文初始化参数以键值对的形式存入该对象中，因此我们可以通过 ServletContext 的相关方法获取到这些初始化参数。
 * 返回值类型	方法	描述
 * String	getInitParameter(String name)	根据初始化参数名 name，返回对应的初始化参数值。
 * Enumeration<String>	getInitParameterNames()	返回 Web 应用所有上下文初始化参数名的枚举集合，
 * 如果该 Web 应用没有上下文初始化参数，则返回一个空的枚举集合。
 */

public class ReadContextServlet extends HttpServlet
{
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter writer = response.getWriter();
        // 调用httpServlet父类GenericServlet的getServletContext方法获取ServletContext对象
        ServletContext context = super.getServletContext();
        // 返回 context 上下文初始化参数的名称
        Enumeration<String> initParameterNames = context.getInitParameterNames();
        while (initParameterNames.hasMoreElements())
        {
            // 获取初始化参数名称
            String initParamName = initParameterNames.nextElement();
            // 获取相应的初始参数的值
            String initParamValue = context.getInitParameter(initParamName);
            // 向页面输出
            writer.write(initParamName + "  :  " + initParamValue + "<br/>");
        }
        // 关闭流
        writer.close();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        doGet(request, response);
    }

}
