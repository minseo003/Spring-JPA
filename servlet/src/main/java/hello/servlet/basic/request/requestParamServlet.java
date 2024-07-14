package hello.servlet.basic.request;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "requestParamServlet",urlPatterns = "/request-param")
public class requestParamServlet extends HttpServlet {

    /**
     * request.getParameter()는 하나의 파라미터 이름에 대해서 단 하나의 값만 있을때 사용해야한다
     * 만약 중복일때 request.getParameter()을 사용하면 첫번째값을 반환한다
     * GET URL parameter방법으로 요청하는경우, 메세지바디에 담을 데이터가 없으므로 메세지바디의형식인 content-type을 지정할 필요가 없다
     * 반면 POST HTML Form parameter방법으로 요청하는경우 conten-type:application/x-www-form-urlencoded
     */
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        System.out.println("[전체 파라미터 조회] -start");

        /*
        Enumeration<String> parameterNames = request.getParameterNames();
        while (parameterNames.hasMoreElements()) {
        String paramName = parameterNames.nextElement();
        System.out.println(paramName + "=" +
        request.getParameter(paramName));
}
*/
        request.getParameterNames().asIterator()
                .forEachRemaining(paramName -> System.out.println(paramName +
                        "=" + request.getParameter(paramName)));
        System.out.println("[전체 파라미터 조회] - end");
        System.out.println();

        System.out.println("단일 파라미터 조회");
        String username = request.getParameter("username");
        System.out.println("request.getParameter(username) = " + username);

        String age = request.getParameter("age");
        System.out.println("request.getParameter(age) = " + age);
        System.out.println();

        System.out.println("[이름이 같은 복수 파라미터 조회]");
        System.out.println("request.getParameterValues(username)");
        String[] usernames = request.getParameterValues("username");
        for (String name : usernames) {
            System.out.println("username = " + name);
        }
        response.getWriter().write("ok");
    }
}
