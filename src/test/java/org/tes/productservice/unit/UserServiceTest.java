package org.tes.productservice.unit;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.tes.productservice.TestFactory;
import org.tes.productservice.model.Cart;
import org.tes.productservice.model.LaptopProduct;
import org.tes.productservice.model.User;
import org.tes.productservice.persistence.UserRepository;
import org.tes.productservice.service.CartService;
import org.tes.productservice.service.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest extends TestFactory {
    @Mock
    private UserRepository userRepository;

    @Mock
    private CartService cartService;

    @InjectMocks
    private UserService userService;

    @Test
    public void givenCorrectUser_whenSave_thenReturnSavedUser() {
        User user = buildUser();
        user.setId(1L);

        when(userRepository.save(user)).thenReturn(user);

        assertEquals(user, userService.save(Optional.of(user)));
    }

    @Test
    public void givenCorrectUserWithCart_whenSave_thenReturnSavedUser() {
        User user = buildUser();
        user.setId(1L);

        Cart cart = new Cart();
        cart.setId(1L);
        cart.setUser(user);
        user.setCart(cart);

        when(userRepository.save(user)).thenReturn(user);

        assertEquals(user, userService.save(Optional.of(user)));
    }

    @Test
    public void givenSavedUser_whenFindByAll_thenReturnSavedUser() {
        User user = buildUser();
        user.setId(1L);
        List<User> users = new ArrayList<>();
        users.add(user);

        when(userRepository.findAll()).thenReturn(users);

        assertEquals(users, userService.findAll());
    }

    @Test
    public void givenCorrectUser_whenFindById_thenReturnSavedUser() {
        User user = buildUser();
        user.setId(1L);

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        assertEquals(user, userService.findById(1L));
    }

    @Test
    public void givenCorrectUpdatedUser_whenUpdate_thenReturnUpdatedUser() {
        User user = buildUser();
        user.setId(1L);

        when(userRepository.save(user)).thenReturn(user);

        assertEquals(user, userService.update(Optional.of(user)));
    }

    @Test
    public void givenCorrectUserId_whenDelete_thenDoNotThrowException() {
        LaptopProduct laptopProduct = new LaptopProduct();
        laptopProduct.setId(1L);

        assertDoesNotThrow(() -> userService.deleteById(1L));
    }
}
