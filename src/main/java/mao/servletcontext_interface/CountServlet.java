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
 * Class(类名): CountServlet
 * Author(作者）: mao
 * Author QQ：1296193245
 * GitHub：https://github.com/maomao124/
 * Date(创建日期)： 2021/12/23
 * Time(创建时间)： 14:51
 * Version(版本): 1.0
 * Description(描述)：  实现数据通讯
 * 在 Servlet 中，调用 ServletContext 接口的 setAttribute() 方法可以创建一些属性，这些属性被存放在 ServletContext 对象中。
 * 应用中所有 Servlet 都可以对这些属性进行访问和操作，通过它们可以实现应用内不同 Servlet 之间的数据通讯。
 * 数据通讯的相关方法
 * 返回值类型	方法	描述
 * void	setAttribute(String name, Object object)	把一个 Java 对象与一个属性名绑定，并将它作为一个属性存放到 ServletContext 中。
 * 参数 name 为属性名，参数 object 为属性值。
 * void	removeAttribute(String name)	从 ServletContext 中移除属性名为 name 的属性。
 * Object	getAttribute(String name)	根据指定的属性名 name，返回 ServletContext 中对应的属性值。
 * ServletContext 属性与上下文初始化参数对比
 * 虽然 ServletContext 的属性与上下文初始化参数都是存放在 ServletContext 对象中，但它们是不同的。
 * 不同点	ServletContext 的属性	上下文初始化参数
 * 创建方式	ServletContext 的属性通过调用 ServletContext 接口的 setAttribute() 方法创建	上下文初始化参数通过 web.xml 使用 <context-param> 元素配置
 * 可进行的操作	ServletContext 的属性可以通过 ServletContext 接口的方法进行读取、新增、修改、移除等操作	上下文初始化参数在容器启动后只能被读取，不能进行新增、修改和移除操作
 * 生命周期	ServletContext 中属性的生命周期从创建开始，到该属性被移除（remove）或者容器关闭结束	上下文初始化参数的生命周期，从容器启动开始，到 Web 应用被卸载或容器关闭结束
 * 作用	使用 ServletContext 中的属性可以实现 Servlet 之间的数据通讯	使用上下文初始化参数无法实现数据通讯
 */

@WebServlet("/CountServlet")
public class CountServlet extends HttpServlet
{
    private static final long serialVersionUID = 1L;

    public void init() throws ServletException
    {
        // 获取ServletContext对象
        ServletContext context = this.getServletContext();
        // 初始化时，向ServletContext中设置count属性，初始值为0
        context.setAttribute("count", 0);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        // 调用httpServlet父类GenericServlet的getServletContext方法获取ServletContext对象
        ServletContext context = super.getServletContext();
        // 获取count的值，自增
        Integer count = (Integer) context.getAttribute("count");
        // 存入到域对象中
        context.setAttribute("count", ++count);
        // 向页面输出内容
        response.setContentType("text/html;charset=UTF-8");
        response.getWriter().write("CountServlet：当前count：" + count);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        doGet(request, response);
    }

}
