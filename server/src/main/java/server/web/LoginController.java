package server.web;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import server.dto.UserDto;
import server.entity.User;
import server.repository.UserRepository;
import server.security.jwt.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class LoginController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtTokenProvider jwtTokenProvider;

    @Autowired
    UserRepository userRepository;

    @PostMapping("/signin")
    public ResponseEntity<UserDto> singIn(@RequestBody AuthRequest request){
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
            String name = request.getUsername();
            User user = userRepository.findUserByUsername(name)
                    .orElseThrow(() -> new UsernameNotFoundException("User not found"));

            List<String> roles = user.getRoles();
            String token = jwtTokenProvider.createToken(name, user.getRoles());

            UserDto userDto = new UserDto();
            userDto.setUsername(name);
            userDto.setRoles(roles);
            userDto.setToken(token);

            return ResponseEntity.ok(userDto);
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid username or password");
        }
    }
}
