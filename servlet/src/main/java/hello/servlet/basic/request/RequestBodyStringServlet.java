package hello.servlet.basic.request;

import jakarta.servlet.ServletException;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * HTTP요청 데이터 API메시지 바디 - 단순 텍스트
 * 메세지 바디에 데이터를 직접 담아서 요청
 * inputStream은 바이트 코드를 반환하는데, 바이트 코드를 우리가 읽을 수 있는 문자로 보려면 문자표(Charset)을 지정해주어야함
 */

@WebServlet(name = "requestBodyStringServlet", urlPatterns = "/request-body-string")
public class RequestBodyStringServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        ServletInputStream inputStream = request.getInputStream(); //메세지바디의 내용을 바이트코드로 얻을 수 있음
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
        System.out.println("messageBody = " + messageBody);
        response.getWriter().write("ok");
    }
}
