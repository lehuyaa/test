package com.example.demo.controller;


import com.example.demo.model.BankAccount;
import com.example.demo.model.User;
import com.example.demo.repository.BankAccountRepository;
import com.example.demo.repository.TrannferRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.request.LoginRequest;
import com.example.demo.request.SignupRequest;
import com.example.demo.request.TransferPhoneRequest;
import com.example.demo.response.JwtResponse;
import com.example.demo.response.MessageResponse;
import com.example.demo.response.UserResponse;
import com.example.demo.security.jwt.JwtUtils;
import com.example.demo.security.services.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    List<BankAccount> accounts = new ArrayList<BankAccount>();

    @Autowired
    TrannferRepository  trannferRepository;


    @Autowired
    BankAccountRepository bankAccountRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser( @RequestBody LoginRequest loginRequest) {

        if (!userRepository.existsByUsername(loginRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Không Tồn Tại Số Điện Thoại"));
        }


        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();


        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getName(),
                userDetails.getMoneyNumber()
               ));
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser( @RequestBody SignupRequest signUpRequest) {
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Số Điện Thoại Này Đã Có Người Sử Dụng"));
        }


        // Create new user's account
        User user = new User(signUpRequest.getUsername(),
                encoder.encode(signUpRequest.getPassword()),signUpRequest.getName(), 0L);


        userRepository.save(user);

        return ResponseEntity.ok(new MessageResponse("Đăng Ký Thành Công"));
    }

    @GetMapping("/user/{userName}")
    public ResponseEntity<?> getTutorialByUserName(@PathVariable("userName") String username) {
        User user = userRepository.findByUsername(username);

        if (!userRepository.existsByUsername(username)) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Không tồn tại Số Điện Thoại"));
        }

        return ResponseEntity.ok(new UserResponse(
                user.getId(),
                user.getUsername(),
                user.getName(),
                user.getMoneyNumber()
        ));


    }
    

    @PutMapping("/transferToPhone")
    public ResponseEntity<?> transferToPhone ( @RequestBody TransferPhoneRequest transferPhoneRequest) {
        User userFrom  = userRepository.findByUsername(transferPhoneRequest.getPhoneFrom());
        User userTo = userRepository.findByUsername(transferPhoneRequest.getPhoneTo());

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(transferPhoneRequest.getPhoneFrom(), transferPhoneRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);


        if(transferPhoneRequest.getPhoneTo().equals(transferPhoneRequest.getPhoneFrom())){
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Tài Khoản Gửi Trùng Tài Khoản Nhận"));
        }

        if (userFrom.getMoneyNumber() < transferPhoneRequest.getMoney()){
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Số Dư Tài Khoản Không Đủ"));
        }


        userFrom.setMoneyNumber(userFrom.getMoneyNumber()-transferPhoneRequest.getMoney());
        userTo.setMoneyNumber(userTo.getMoneyNumber()+transferPhoneRequest.getMoney());

        userRepository.save(userFrom);
        userRepository.save(userTo);

        return ResponseEntity.ok(new MessageResponse("Giao Dịch Thành Công"));
    }

    @GetMapping("/bank_account/{user_id}")
    public List<BankAccount> getBankAccountByUserId(@PathVariable("user_id") Long id) {


        accounts = bankAccountRepository.findByIdUser(id);
        return accounts;
    }

}