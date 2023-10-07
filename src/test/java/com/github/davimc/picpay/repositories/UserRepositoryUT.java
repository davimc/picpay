package com.github.davimc.picpay.repositories;

import com.github.davimc.picpay.entities.User;
import com.github.davimc.picpay.entities.UserLegal;
import com.github.davimc.picpay.entities.UserNatural;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
public class UserRepositoryUT {

    @Autowired
    private UserRepository repository;

    private User natural;
    private User legal;

    @BeforeEach
    public void setup() {
        natural  = new UserNatural("nova@pessoa.com", "123456","pessoa","nova", "35258402096");
        legal = new UserLegal("nova@empresa.com", "123456", "empresa","empresa nova ltda", "60028949000121");
    }

    @DisplayName("JUnit test for Given User Object when Save then Return Saved User")
    @Test
    void testGivenUserObject_whenSave_thenReturnSavedUser() {

        // Given / Arrange

        // When / Act
        User savedUser = repository.save(natural);

        // Then / Assert
        assertNotNull(savedUser);
        assertTrue(savedUser.getId() > 0);
    }
    @DisplayName("JUnit test for Given User List when findAll then Return User List")
    @Test
    void testGivenUserList_whenFindAll_thenReturnUserList() {

        // Given / Arrange


        //repository.save(legal);
//        repository.save(natural);

        // When / Act
        List<User> userList = repository.findAll();

        // Then / Assert
        assertNotNull(userList);
        assertEquals(3, userList.size());
    }
    @DisplayName("JUnit test for Given User List when findAll then Return User List")
    @Test
    void testGivenId_whenFind_thenReturnUser() {

        // Given / Arrange

        //User userSaved = repository.save(natural);

        // When / Act
        User userFind = repository.findById(1L).get();

        // Then / Assert
        assertNotNull(userFind);
        assertEquals(1L, userFind.getId());
        assertTrue(userFind instanceof UserNatural);
    }
}
