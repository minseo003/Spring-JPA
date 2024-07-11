package hello.servlet.web.frontcontroller.v5.adapter;

import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.v4.ControllerV4;
import hello.servlet.web.frontcontroller.v5.MyHandlerAdapter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ControllerV4HandlerAdapter implements MyHandlerAdapter {
    @Override
    public boolean supports(Object handler) {
        return (handler instanceof ControllerV4);
    }

    @Override
    public ModelView handle(HttpServletRequest request, HttpServletResponse response, Object handler)
            {
                ControllerV4 controller = (ControllerV4) handler;
                Map<String, String> paramMap = createParamMap(request);
                Map<String, Object> model = new HashMap<>();

                String viewName = controller.process(paramMap, model); //컨트롤러 실행

                ModelView mv = new ModelView(viewName);  //뷰 실행 String으로 반환되기 때문에 ModelView를 쓰는 어댑터를 쓰려면
                mv.setModel(model);  //ModelView로 변환해 처리해주어야한다.
                return mv;
            }
    private Map<String, String> createParamMap(HttpServletRequest request) {

        Map<String, String> paramMap = new HashMap<>();

        request.getParameterNames().asIterator()
                .forEachRemaining(paramName -> paramMap.put(paramName, request.getParameter(paramName)));
        return paramMap;
    }
}
