package com.example.SignInSecurity.service;

import com.example.SignInSecurity.entity.TokenConfirm;
import com.example.SignInSecurity.entity.User;
import com.example.SignInSecurity.exception.BadRequestException;
import com.example.SignInSecurity.repository.TokenConfirmRepository;
import com.example.SignInSecurity.repository.UserRepository;
import com.example.SignInSecurity.request.LoginRequest;
import com.example.SignInSecurity.request.RegisterRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AuthService {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private MailService mailService;
    @Autowired
    private TokenConfirmRepository tokenConfirmRepository;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserRepository userRepository;
    public String login(LoginRequest loginRequest, HttpSession session){
        try {
            // 1. Tạo đối tượng xác thực
            UsernamePasswordAuthenticationToken token =
                    new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword());

            // 2. Tiến hành xác thực
            Authentication authentication = authenticationManager.authenticate(token);

            // 3. Lưu đối tượng vào trong context
            SecurityContextHolder.getContext().setAuthentication(authentication);

            // 4. Set cookie
            session.setAttribute("MY_SESSION", authentication.getName());

            return "Đăng nhập thành công";
        }
        catch (AuthenticationException e) {
            throw new RuntimeException("Tài khoản hoặc mật khẩu không chính xác");
        }
    }

    public String logout(HttpSession session) {
        session.invalidate();
        return "Đăng xuất thành công";
    }

    public String register(RegisterRequest registerRequest) {
        Optional<User> userOptional=userRepository.findByEmail(registerRequest.getEmail());
        if(userOptional.isPresent()){
            User user=userOptional.get();
            if(!user.getEnabled()&& user.getName().equals(registerRequest.getName())
            &&user.getEmail().equals(registerRequest.getEmail())){
                //generate ra token
                //return link kich hoat o day
                return generateTokenAndSendMail(user);
            }
            throw new BadRequestException("Email da"+registerRequest.getEmail()+"ton tai");
        }
        //tao user moi
        User user=new User(registerRequest.getName(),registerRequest.getEmail(),passwordEncoder.encode(registerRequest.getPassword()), List.of("USER"));
        userRepository.save(user);
        return generateTokenAndSendMail(user);
    }
    public String generateTokenAndSendMail(User user){
        String generateToken= UUID.randomUUID().toString();
        TokenConfirm tokenConfirm=new TokenConfirm(generateToken, LocalDateTime.now(),LocalDateTime.now().plusMinutes(15),user);
        tokenConfirmRepository.save(tokenConfirm);
        String link="http://localhost:8080/api/auth/confirm?token="+generateToken;
        mailService.send(user.getEmail(),"Kich hoat tai khoan",link);
        return link;
    }
    @Transactional
    public String confirm(String token) {
        Optional<TokenConfirm> tokenConfirmOptional=tokenConfirmRepository.findByToken(token);
        if(tokenConfirmOptional.isEmpty()){
            throw new BadRequestException("Token not found");
        }
        //Kiem tra con thoi han hay khong
        TokenConfirm tokenConfirm=tokenConfirmOptional.get();
        if(tokenConfirm.getConfirmedAt()!=null){
            throw new BadRequestException("token da duoc kich hoat");
        }
        if(tokenConfirm.getExpiresAt().isBefore(LocalDateTime.now())){
            throw new BadRequestException("Token het han");
        }
        tokenConfirm.setConfirmedAt(LocalDateTime.now());
        tokenConfirmRepository.save(tokenConfirm);
        //kich hoat user
        User user=tokenConfirm.getUser();
        user.setEnabled(true);
        userRepository.save(user);
        return "confirmed";
    }
}
