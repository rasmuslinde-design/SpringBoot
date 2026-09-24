package ee.opilane.springbootcrud.user;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping("/users")
    public String showUserList(Model model) {
        model.addAttribute("listUsers", service.listAll());
        return "users";
    }

    @GetMapping("/users/new")
    public String showNewForm(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("pageTitle", "Lisa uus kasutaja");
        return "user_form";
    }

    @PostMapping("/users/save")
    public String saveUser(User user, RedirectAttributes redirectAttributes) {
        service.save(user);
        redirectAttributes.addFlashAttribute(
                "message", "Kasutaja salvestati edukalt.");
        return "redirect:/users";
    }

    @GetMapping("/users/edit/{id}")
    public String showEditForm(@PathVariable Integer id, Model model,
                               RedirectAttributes redirectAttributes) {
        try {
            model.addAttribute("user", service.get(id));
            model.addAttribute("pageTitle", "Muuda kasutajat (ID: " + id + ")");
            return "user_form";
        } catch (UserNotFoundException exception) {
            redirectAttributes.addFlashAttribute("message",
                    exception.getMessage());
            return "redirect:/users";
        }
    }

    @GetMapping("/users/delete/{id}")
    public String deleteUser(@PathVariable Integer id,
                             RedirectAttributes redirectAttributes) {
        try {
            service.delete(id);
            redirectAttributes.addFlashAttribute("message",
                    "Kasutaja ID-ga " + id + " kustutati.");
        } catch (UserNotFoundException exception) {
            redirectAttributes.addFlashAttribute("message",
                    exception.getMessage());
        }
        return "redirect:/users";
    }
}
