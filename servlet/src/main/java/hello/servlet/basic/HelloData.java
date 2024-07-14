package hello.servlet.basic;

import lombok.Getter;
import lombok.Setter;

/**
 * Json형식으로 파싱할 수 있게 해주는 객체
 */
@Getter
@Setter
public class HelloData {

    private String username;
    private int age;
}
