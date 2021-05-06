package com.disertatie.rent.car.repository;

import com.disertatie.rent.car.entities.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Optional;

import static com.disertatie.rent.car.prototype.UserPrototype.aUser;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserRepositoryTest {

    @Resource
    private UserRepository userRepository;

    @Test
    public void findByEmail() {
        userRepository.save(aUser());
        Optional<User> foundUser = userRepository.findByEmail(aUser().getEmail());
        assertThat(foundUser).isNotEmpty();
        assertThat(foundUser.get().getName()).isEqualTo(aUser().getName());
    }

}