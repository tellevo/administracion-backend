package cl.tellevo.admin.controller;

import cl.tellevo.admin.model.User;
import cl.tellevo.admin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public String register(@RequestBody Map<String, String> userMap) {
        String username = userMap.get("username");
        String password = userMap.get("password");
        userService.registerUser(username, password);
        return "Usuario registrado exitosamente";
    }

    @PostMapping("/login")
    public String login(@RequestBody Map<String, String> userMap) {
        String username = userMap.get("username");
        String password = userMap.get("password");

        return userService.loginUser(username, password)
                .map(user -> "Inicio de sesión exitoso")
                .orElse("Credenciales inválidas");
    }
}
