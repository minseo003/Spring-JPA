package hello.springtx.propagation;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.UnexpectedRollbackException;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Slf4j
@SpringBootTest
public class MemberServiceTest {

    @Autowired
    MemberService memberService;
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    LogRepository logRepository;

    /**
     * MemberService    @Transactional:off
     * MemberRepository @Transactional:on
     * LogRepository    @Transactional:on
     */

    @Test
    void outerTxOff_success() {
        //given
        String username = "outerTxOff_success";
        //when
        memberService.join1(username);
        //then
        assertTrue(memberRepository.find(username).isPresent());
        assertTrue(logRepository.find(username).isPresent());
    }



    @Test
    void outerTxOff_fail() {
        //given
        String username = "로그예외_outerTxOff_fail";
        //when
        assertThatThrownBy(() -> memberService.join1(username))
                .isInstanceOf(RuntimeException.class);

        //then
        assertTrue(memberRepository.find(username).isPresent());
        assertTrue(logRepository.find(username).isEmpty());
    }

    /**
     * MemberService    @Transactional:on
     * MemberRepository @Transactional:off
     * LogRepository    @Transactional:off
     */
    @Test
    void singleTx() {
        //given
        String username = "singleTx";
        //when
        memberService.join1(username);
        //then
        assertTrue(memberRepository.find(username).isPresent());
        assertTrue(logRepository.find(username).isPresent());
    }
    /**
     * MemberService    @Transactional:on
     * MemberRepository @Transactional:on
     * LogRepository    @Transactional:on
     */

    @Test
    void outerTxOn_success() {
        //given
        String username = "outerTxOn_success";
        //when
        memberService.join1(username);
        //then
        assertTrue(memberRepository.find(username).isPresent());
        assertTrue(logRepository.find(username).isPresent());
    }

    /**
     * MemberService    @Transactional:on
     * MemberRepository @Transactional:on
     * LogRepository    @Transactional: Exception
     */
    @Test
    void outerTxOff_fail2() {
        //given
        String username = "로그예외_outerTxOff_fail";
        //when
        assertThatThrownBy(() -> memberService.join1(username))
                .isInstanceOf(RuntimeException.class);

        //then
        assertTrue(memberRepository.find(username).isEmpty());
        assertTrue(logRepository.find(username).isEmpty());
    }

    /**
     * MemberService    @Transactional:on
     * MemberRepository @Transactional:on
     * LogRepository    @Transactional: Exception
     */
    @Test
    void recoverException_fail() {
        //given
        String username = "로그예외_recoverException_fail";
        //when
        assertThatThrownBy(() -> memberService.join2(username))
                .isInstanceOf(UnexpectedRollbackException.class);

        //then
        assertTrue(memberRepository.find(username).isEmpty());
        assertTrue(logRepository.find(username).isEmpty());
    }

    /**
     * MemberService    @Transactional:on
     * MemberRepository @Transactional:on
     * LogRepository    @Transactional(REQUIRES_NEW) Exception
     */
    @Test
    void recoverException_success() {
        //given
        String username = "로그예외_recoverException_success";
        //when
        memberService.join2(username);

        //then
        assertTrue(memberRepository.find(username).isPresent());
        assertTrue(logRepository.find(username).isEmpty());  //물리롤백
    }
}
