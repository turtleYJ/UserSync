package com.example.usersync;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @DataJpaTest
 * 1. Spring Context 로드: JPA 관련 컴포넌트(EntitiyManager, DataSource, JPA리파지토리)만 로드하여 테스트 환경을 구축 => 속도 향상
 * 2. 자동 롤백: 테스트가 끝난 후 데이터베이스 변경 사항을 자동 롤백
 * 3. 내장 데이터베이스 사용: => 외부 데이터 베이스 환경에 의존하지 않고 독립적으로 테스트할 수 있음.
 */
@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository; // 목 객체를 사용하지 않는 이유: 실제 데이터 베이스와 리포지토리와의 상호작용을 테스트하는데 목적이 있기 때문

    @Test
    public void testCreateUser() {
        User user = new User(); // 객체 생성
        user.setName("John Doe"); // JPA: property 변경
        user = userRepository.save(user);

        Optional<User> foundUser = userRepository.findById(user.getId());
        assertThat(foundUser).isPresent();
        assertThat(foundUser.get().getName()).isEqualTo("John Doe");
    }

    @Test
    public void testUpdateUser() {
        User user = new User();
        user.setName("John Doe");
        user = userRepository.save(user);

        // 이름을 변경
        user.setName("Jane Doe");
        user = userRepository.save(user);

        Optional<User> foundUser = userRepository.findById(user.getId());
        assertThat(foundUser).isPresent();
        assertThat(foundUser.get().getName()).isEqualTo("Jane Doe");
    }

    @Test
    public void testDeleteUser() {
        User user = new User();
        user.setName("John Doe");
        user = userRepository.save(user);

        userRepository.delete(user);

        Optional<User> foundUser = userRepository.findById(user.getId());
        assertThat(foundUser).isNotPresent();
    }
}
