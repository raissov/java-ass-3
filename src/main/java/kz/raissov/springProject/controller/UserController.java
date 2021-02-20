package kz.raissov.springProject.controller;

import kz.raissov.springProject.exception.ResourceNotFoundException;
import kz.raissov.springProject.model.User;
import kz.raissov.springProject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/users")
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUserById(@PathVariable(value = "id") Long id) throws ResourceNotFoundException{
        User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found for this id : :" + id));
        return ResponseEntity.ok().body(user);
    }
    @GetMapping("/users/email/{email_id}")
    public  ResponseEntity<User> getUserByEmailId(@PathVariable (value = "email_id") String emailId) throws ResourceNotFoundException {
        User user = userRepository.findByEmailId(emailId);
        return ResponseEntity.ok().body(user);
    }
    @PostMapping("/users")
    public User createUser(@Valid @RequestBody User user) {
        return userRepository.save(user);
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<User> updateUser(@PathVariable(value = "id") Long id, @Valid @RequestBody User userDetails) throws ResourceNotFoundException{
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found for this ID::" + id));
        user.setEmailId(userDetails.getEmailId());
        user.setFirstName(userDetails.getFirstName());
        user.setLastName(userDetails.getLastName());
        user.setPassword(userDetails.getPassword());
        user.setStatus(userDetails.getStatus());
        final User updateUser = userRepository.save(user);
        return ResponseEntity.ok(updateUser);
    }

    @DeleteMapping("/users/{id}")
    public Map<String, Boolean> deleteUser(@PathVariable(value = "id") Long id) throws ResourceNotFoundException{
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found for this ID :: " + id));
        userRepository.delete(user);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

}
