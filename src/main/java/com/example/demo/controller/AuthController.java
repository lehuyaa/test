package com.example.demo.controller;


import com.example.demo.model.*;
import com.example.demo.repository.*;
import com.example.demo.request.AddMoneyRequest;
import com.example.demo.request.LoginRequest;
import com.example.demo.request.SignupRequest;
import com.example.demo.request.TransferPhoneRequest;
import com.example.demo.response.HistoryResponse;
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

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collections;
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
    List<HistoryResponse> historyResponses = new ArrayList<>();
    List<History> historys = new ArrayList<>();



    @Autowired
    TransferToPhoneRepository transferToPhoneRepository;

    @Autowired
    AddMoneyRepository addMoneyRepository;

    @Autowired
    HistoryRepository historyRepository ;

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

        Long time = System.currentTimeMillis();
        TransferToPhone transfertoPhone = new TransferToPhone(userTo.getId(),
                userFrom.getId(),
                transferPhoneRequest.getMoney(),
                transferPhoneRequest.getContent(),
                new Date(time));
        transferToPhoneRepository.save(transfertoPhone);

        History history1 = new History(userFrom.getId(),
                transfertoPhone.getId(),
                1L,
                "Chuyển Tiền Cho "+userTo.getName(),
                "Mất");

        History history2 = new History(userTo.getId(),
                transfertoPhone.getId(),
                1L,
                "Nhận Tiền Từ "+userFrom.getName(),
                "Thêm");
        historyRepository.save(history1);
        historyRepository.save(history2);

        return ResponseEntity.ok(new MessageResponse("Giao Dịch Thành Công"));
    }

    @GetMapping("/bank_account/{user_id}")
    public List<BankAccount> getBankAccountByUserId(@PathVariable("user_id") Long id) {


        accounts = bankAccountRepository.findByIdUser(id);
        return accounts;
    }


    @PutMapping("/addMoney")
    public ResponseEntity<?> addMoney  ( @RequestBody AddMoneyRequest addMoneyRequest) {
       Optional<BankAccount> bankAccount = bankAccountRepository.findById(addMoneyRequest.getBankID());
       Optional<User> user = userRepository.findById(addMoneyRequest.getUserID());

        if(bankAccount.get().getMoneyNumber() < addMoneyRequest.getMoney()){
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Số Dư Tài Khoản Không Đủ"));
        }

        bankAccount.get().setMoneyNumber(bankAccount.get().getMoneyNumber()- addMoneyRequest.getMoney() );
        user.get().setMoneyNumber(user.get().getMoneyNumber()+addMoneyRequest.getMoney());

        userRepository.save(user.get());
        bankAccountRepository.save(bankAccount.get());

        Long time = System.currentTimeMillis();

        AddMoney addMoney = new AddMoney(user.get().getId(),
                addMoneyRequest.getMoney(),
                "Nạp Tiền Từ Ngân Hàng " + bankAccount.get().getBankName(),
                addMoneyRequest.getBankID(), new Date(time));
        addMoneyRepository.save(addMoney);

        History history = new History(user.get().getId(),
                addMoney.getId(),
                2L,
                "Nạp Tiền Từ Ngân Hàng "+bankAccount.get().getBankName(),
                "Thêm");

        historyRepository.save(history);
        return ResponseEntity.ok(new MessageResponse("Nạp Tiền Thành Công"));

    }

    @GetMapping("/history/{user_id}")
    public List<HistoryResponse> getHistory (@PathVariable("user_id") Long id) {
        historys = historyRepository.findByIdUser(id);
        historyResponses.clear();
        for (int i = 0 ; i < historys.size() ;i++ ){
            HistoryResponse historyResponse = new HistoryResponse();
            historyResponse.setTitle(historys.get(i).getTitle());
            historyResponse.setType(historys.get(i).getType());
            historyResponse.setIdTypeDeal(historys.get(i).getIdTypeDeal());
            if (historys.get(i).getIdTypeDeal()==1L){
                Optional<TransferToPhone> transfertoPhone = transferToPhoneRepository.findById(historys.get(i).getIdDetails());
                Optional<User> userTo  = userRepository.findById(transfertoPhone.get().getIdTo());
                Optional<User> userFrom   = userRepository.findById(transfertoPhone.get().getIdFrom());
                DetailsHistory  detail = new DetailsHistory(userFrom.get().getName(),
                        userTo.get().getName(),
                        transfertoPhone.get().getContent(),
                        transfertoPhone.get().getMoney(),transfertoPhone.get().getDate());
                historyResponse.setDetails(detail);
            }else if (historys.get(i).getIdTypeDeal()==2L){
                Optional<AddMoney> addMoney  = addMoneyRepository.findById(historys.get(i).getIdDetails());
                Optional<BankAccount> bankAccount = bankAccountRepository.findById(addMoney.get().getBankId());
                Optional<User> userTo  = userRepository.findById(addMoney.get().getIdUser());
                DetailsHistory  detail = new DetailsHistory(bankAccount.get().getBankName(),
                        userTo.get().getName(),
                        addMoney.get().getContent(),
                        addMoney.get().getMoney(),addMoney.get().getDate());
                historyResponse.setDetails(detail);
            }
            historyResponses.add(historyResponse);

        }
        return historyResponses;
//        return historys;


    }
}