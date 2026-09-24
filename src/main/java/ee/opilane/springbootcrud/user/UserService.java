package ee.opilane.springbootcrud.user;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public List<User> listAll() {
        List<User> users = new ArrayList<>();
        repository.findAll().forEach(users::add);
        return users;
    }

    public void save(User user) {
        repository.save(user);
    }

    public User get(Integer id) throws UserNotFoundException {
        return repository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(
                        "Kasutajat ID-ga " + id + " ei leitud."));
    }

    public void delete(Integer id) throws UserNotFoundException {
        if (!repository.existsById(id)) {
            throw new UserNotFoundException(
                    "Kasutajat ID-ga " + id + " ei leitud.");
        }
        repository.deleteById(id);
    }
}
