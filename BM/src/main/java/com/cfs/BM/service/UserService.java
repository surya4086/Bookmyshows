package com.cfs.BM.service;

import com.cfs.BM.dto.LoginRequest;
import com.cfs.BM.dto.UserRequest;
import com.cfs.BM.entity.User;
import com.cfs.BM.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    //register
    public User register(UserRequest request)
        {
         if(userRepository.existsByEmail(request.getEmail()))
         {
             throw new RuntimeException("Email alreary exists :" +request.getEmail() );
         }
         User user=User.builder()
                 .name(request.getName())
                 .email(request.getEmail())
                 .password(request.getPassword())
                 .phone(request.getPhone())
                 .build();
        return userRepository.save(user);
        }
        //login
    public User login(LoginRequest request)
    {
        User user=userRepository.findByEmail(request.getEmail()).orElseThrow(()->new RuntimeException("User not found with email :"+request.getEmail()));
        if(!user.getPassword().equals(request.getPassword()))
        {
         throw new RuntimeException("Invalid Password");
        }
        return user;
    }
    public List<User> getAllUsers()
    {
        return userRepository.findAll();
    }
    public User getUserById(Long id)
    {
        return userRepository.findById(id).orElseThrow(()->new RuntimeException("User not found with id :"+id));
    }

}
