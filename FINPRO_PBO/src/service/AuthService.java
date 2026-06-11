package service;

import repository.UserRepository;
import model.User;

public class AuthService {
    private UserRepository userRepository;

    public AuthService() {
        this.userRepository = new UserRepository();
    }

    public User login(String username, String password) throws Exception {
        if (username == null || username.trim().isEmpty()) {
            throw new Exception("Username tidak boleh kosong.");
        }
        if (password == null || password.trim().isEmpty()) {
            throw new Exception("Password tidak boleh kosong.");
        }

        User user = userRepository.login(username.trim(), password);
        if (user == null) {
            throw new Exception("Login Gagal! Username atau password salah.");
        }

        return user;
    }
}
