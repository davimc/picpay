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
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

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

    @DisplayName("JUnit test for Given User Natural Object when Save then Return Saved User")
    @Test
    void testGivenUserNatural_whenSave_thenReturnSavedUser() {

        // Given / Arrange

        // When / Act
        User savedUser = repository.save(natural);

        // Then / Assert
        assertNotNull(savedUser);
        assertTrue(savedUser instanceof UserNatural);
        assertTrue(savedUser.getId() > 0);
    }
    @DisplayName("JUnit test for Given User Legal Object when Save then Return Saved User")
    @Test
    void testGivenUserLegal_whenSave_thenReturnSavedUser() {

        // Given / Arrange

        // When / Act
        User savedUser = repository.save(legal);

        // Then / Assert
        assertNotNull(savedUser);
        assertTrue(savedUser instanceof UserLegal);
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
    @DisplayName("JUnit test for Given Id when findById then Return User")
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
    @DisplayName("JUnit test for Given nonexistent Id when findById then Throw Exception")
    @Test
    void testGivenNonexistentId_whenFind_thenThrowException() {

        // Given / Arrange
        Long nonexistentId = 1000L;
        //User userSaved = repository.save(natural);

        // When / Act
        Optional<User> obj = repository.findById(nonexistentId);

        // Then / Assert
        assertThrows(NoSuchElementException.class, () -> obj.get());
    }
    @DisplayName("JUnit test for Given Email when findByEmail then Return User")
    @Test
    void testGivenEmail_whenFind_thenReturnUser() {

        // Given / Arrange
        String email = "bob@gmail.com";

        //User userSaved = repository.save(natural);

        // When / Act
        User userFind = repository.findByEmail(email).get();

        // Then / Assert
        assertNotNull(userFind);
        assertEquals(email, userFind.getEmail());
    }
    @DisplayName("JUnit test for Given nonexistent Id when findById then Throw Exception")
    @Test
    void testGivenNonexistentEmail_whenFind_thenThrowException() {

        // Given / Arrange
        String nonexistentEmail = "nao@existent.tv";
        //User userSaved = repository.save(natural);

        // When / Act
        Optional<User> obj = repository.findByEmail(nonexistentEmail);

        // Then / Assert
        assertThrows(NoSuchElementException.class, () -> obj.get());
    }


}
