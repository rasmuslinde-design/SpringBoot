package ee.opilane.springbootcrud.user;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository repository;

    @InjectMocks
    private UserService service;

    @Test
    void listAllReturnsUsersFromRepository() {
        User user = new User();
        user.setEmail("test@example.com");
        when(repository.findAll()).thenReturn(List.of(user));

        List<User> result = service.listAll();

        assertThat(result).containsExactly(user);
    }

    @Test
    void getThrowsWhenUserDoesNotExist() {
        when(repository.findById(42)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> service.get(42))
                .isInstanceOf(UserNotFoundException.class)
                .hasMessageContaining("42");
    }

    @Test
    void deleteRemovesExistingUser() throws UserNotFoundException {
        when(repository.existsById(1)).thenReturn(true);

        service.delete(1);

        verify(repository).deleteById(1);
    }
}
