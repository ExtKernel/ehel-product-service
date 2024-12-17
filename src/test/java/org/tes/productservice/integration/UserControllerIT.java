package org.tes.productservice.integration;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.tes.productservice.model.User;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Profile("test")
@TestPropertySource(locations = "classpath:application-integrationtest.properties")
public class UserControllerIT extends AbstractIntegrationTest {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void givenCorrectUser_whenSave_thenReturnSavedUser()
            throws Exception {
        User user = buildUser();
        user.setId(1L);

        assertEquals(user, performPostRequestExpectedSuccess(
                "/secured/user",
                user,
                User.class
        ));
    }

    @Test
    public void givenCorrectUser_whenUpdate_thenReturnUpdatedUser()
            throws Exception {
        User user = buildUser();
        user.setId(1L);

        performPostRequest(
                "/secured/user",
                user,
                User.class,
                MockMvcResultMatchers.status().isOk()
        );

        user.setCity("new-test-city");

        assertEquals(user, performPutRequestExpectedSuccess(
                "/secured/user",
                user,
                User.class
        ));
    }

    @Test
    public void givenCorrectUsers_whenFindAll_thenReturnUsers()
            throws Exception {
        User user1 = buildUser();
        user1.setId(1L);
        User user2 = buildUser();
        user2.setId(2L);
        User user3 = buildUser();
        user3.setId(3L);

        List<User> users = new ArrayList<>();
        users.add(user1);
        users.add(user2);
        users.add(user3);

        users.forEach(user -> {
            try {
                performPostRequest(
                        "/secured/user",
                        user,
                        User.class,
                        MockMvcResultMatchers.status().isOk()
                );
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        List<String> expectedJsonStrings = new ArrayList<>();
        users.forEach(user -> {
            try {
                String jsonString = objectMapper.writeValueAsString(user);
                expectedJsonStrings.add(jsonString);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        });

        List<?> actualList = performGetRequestExpectedSuccess(
                "/secured/user",
                List.class
        );

        List<String> actualJsonStrings = new ArrayList<>();
        actualList.forEach(item -> {
            try {
                String jsonString = objectMapper.writeValueAsString(item);
                actualJsonStrings.add(jsonString);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        });

        assertEquals(expectedJsonStrings, actualJsonStrings);
    }
}
