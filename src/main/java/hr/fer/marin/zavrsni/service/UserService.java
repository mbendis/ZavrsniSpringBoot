package hr.fer.marin.zavrsni.service;

import hr.fer.marin.zavrsni.model.User;
import hr.fer.marin.zavrsni.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public List<User> getAll(){
        return userRepository.findAll();
    }
    public User getById(Integer id){
        return userRepository.getOne(id);
    }
    public User add(User user){
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setRole("USER");
        return userRepository.save(user);
    }
    public void delete(Integer id){
        userRepository.deleteById(id);
    }
    public User findUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }


}
