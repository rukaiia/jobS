
package com.example.jobsearch.service.impl;

import com.example.jobsearch.common.Utilities;
import com.example.jobsearch.dto.user.*;
import com.example.jobsearch.exception.UserNotFoundException;
import com.example.jobsearch.model.User;
import com.example.jobsearch.repository.UserRepository;
import com.example.jobsearch.service.EmailService;
import com.example.jobsearch.service.UserService;
import com.example.jobsearch.util.FileUtil;
import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.UnsupportedEncodingException;
import java.util.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final EmailService emailService;
    private final PasswordEncoder encoder;
    @Override
    public Map<String, Object> forgotPassword(HttpServletRequest request) {
        Map<String, Object> model = new HashMap<>();
        try {
            makeResetPasswordLink(request);
            model.put("message", "we have sent reset password link to your email. Please check.");
        } catch (UsernameNotFoundException | UnsupportedEncodingException e) {
            model.put("error", e.getMessage());
        } catch (MessagingException e) {
            model.put("error", "Error while sending reset password link to your email.");
        }
        return model;
    }

    @Override
    public Map<String, Object> resetPasswordGet(String token) {
        Map<String, Object> model = new HashMap<>();
        try {
            getByToken(token);
            model.put("token", token);
        } catch (UsernameNotFoundException e) {
            model.put("error", "Invalid token");
        }
        return model;
    }

    @Override
    public Map<String, Object> resetPasswordPost(HttpServletRequest request) {
        Map<String, Object> model = new HashMap<>();
        String token = request.getParameter("token");
        String password = request.getParameter("password");

        try {
            User user = getByToken(token);
            updatePassword(user, password);
            model.put("message", "You have successfully changed your password.");
        } catch (UsernameNotFoundException e) {
            model.put("message", "Invalid token");
        }
        return model;
    }

    private void makeResetPasswordLink(HttpServletRequest request) throws MessagingException, UnsupportedEncodingException {
        String email = request.getParameter("email");
        String token = UUID.randomUUID().toString();
        updateToken(token, email);

        String resetPasswordLink = Utilities.getSiteUrl(request) + "/users/reset_password?token=" + token;
        emailService.sendMail(email, resetPasswordLink);
    }

    private User getByToken(String token) {
        return userRepository.findByResetPasswordToken(token)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    private void updatePassword(User user, String password) {
        String encodedPassword = encoder.encode(password);
        user.setPassword(encodedPassword);
        user.setResetPasswordToken(null);
        userRepository.save(user);
    }

    private void updateToken(String token, String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Could not find any user with the email: " + email));
        user.setResetPasswordToken(token);
        userRepository.save(user);
    }



    private final UserRepository userRepository;
    private final FileUtil fileUtil;
    private final PasswordEncoder passwordEncoder;


    @Override
    public List<UserDto> getUsers() {
        List<User> users = userRepository.findAll();
        return getUserDtos(users);
    }

    @Override
    public UserDto getUserById(int id) {
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("Can not find user with id: " + id));
        return UserDto.builder()
                .id(user.getId())
                .name(user.getName())
                .surname(user.getSurname())
                .age(user.getAge())
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .avatar(user.getAvatar())
                .accountType(user.getAccountType())
                .build();
    }

    @Override
    public List<UserDto> getUserByName(String name) {
        List<User> users = userRepository.findUserByName(name);
        if (!users.isEmpty()) {
            return getUserDtos(users);
        }
        throw new NoSuchElementException("Нет юзера с именем: " + name);
    }

    @Override
    public List<UserDto> getEmployee(EmployeeFindDto employeeFindDto) {
        String name = Objects.requireNonNullElse(employeeFindDto.getName(), "null");
        String surname = Objects.requireNonNullElse(employeeFindDto.getSurname(), "null");
        String email = Objects.requireNonNullElse(employeeFindDto.getEmail(), "null");

        List<User> users = userRepository.findEmployeesByTags(name, surname, email);
        if (!users.isEmpty()) {
            return getUserDtos(users);
        }
        throw new NoSuchElementException("Нет совпадений соискателя по этим параметрам");
    }

    @Override
    public List<UserDto> getEmployer(String name) {
        List<User> users = userRepository.findEmployer(name);
        if (!users.isEmpty()) {
            return getUserDtos(users);
        }
        throw new NoSuchElementException("Нет совпадений работодателя с названием: " + name);
    }

    private List<UserDto> getUserDtos(List<User> users) {
        List<UserDto> dtos = new ArrayList<>();
        users.forEach(e -> dtos.add(UserDto.builder()
                .id(e.getId())
                .name(e.getName())
                .surname(e.getSurname())
                .age(e.getAge())
                .email(e.getEmail())
                .phoneNumber(e.getPhoneNumber())
                .avatar(e.getAvatar())
                .accountType(e.getAccountType())
                .build()));
        return dtos;
    }

    @Override
    public UserDto getUserByPhone(String phone) {
        User user = userRepository.findUserByPhoneNumber(phone)
                .orElseThrow(() -> new UserNotFoundException("Can not find user with phone: " + phone));
        return UserDto.builder()
                .id(user.getId())
                .name(user.getName())
                .surname(user.getSurname())
                .age(user.getAge())
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .avatar(user.getAvatar())
                .accountType(user.getAccountType())
                .build();
    }

    @Override
    public UserDto getUserByEmail(String email) {
        User user = userRepository.findUserByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("Can not find user with email: " + email));
        return UserDto.builder()
                .id(user.getId())
                .name(user.getName())
                .surname(user.getSurname())
                .age(user.getAge())
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .avatar(user.getAvatar())
                .accountType(user.getAccountType())
                .build();
    }

    @Override
    public void uploadUserAvatar(UserAvatarFileDto avatarDto) {
        String filename = fileUtil.saveUploadedFile(avatarDto.getFile(), "images");
        UserAvatarDto userAvatarDto = new UserAvatarDto();
        userAvatarDto.setUserId(avatarDto.getUserId());
        userAvatarDto.setFileName(filename);

        userRepository.saveAvatar(userAvatarDto.getFileName(), userAvatarDto.getUserId());
    }

    @SneakyThrows
    @Override
    public ResponseEntity<?> downloadAvatar(int userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("Can not find user with id: " + userId));
        String filename = user.getAvatar();
        return fileUtil.getOutputFile(filename, "images", MediaType.IMAGE_JPEG);
    }

    @Override
    public Boolean isUserInSystem(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public Boolean isUserInSystem(int id) {
        return userRepository.existsById(id);
    }

    @Override
    public Boolean isEmployee(String userEmail) {
        return "EMPLOYEE".equalsIgnoreCase(getUserByEmail(userEmail).getAccountType());
    }
    @Override
    public Boolean isEmployer(String userEmail) {
        return "EMPLOYER".equalsIgnoreCase(getUserByEmail(userEmail).getAccountType());
    }

    @Override
    public Boolean isEmployer(int userId) {
        return "EMPLOYER".equalsIgnoreCase(getUserById(userId).getAccountType());
    }

    @Override
    public Boolean isEmployee(int userId) {
        return "EMPLOYEE".equalsIgnoreCase(getUserById(userId).getAccountType());
    }

    @Override
    public HttpStatus createUser(UserDto userDto, MultipartFile file) {
        if (!isUserInSystem(userDto.getEmail())) {
            if (userDto.getAccountType().equals("EMPLOYER") || userDto.getAccountType().equals("EMPLOYEE")) {
                User user = User.builder()
                        .name(userDto.getName())
                        .surname(userDto.getSurname())
                        .age(userDto.getAge())
                        .email(userDto.getEmail())
                        .password(passwordEncoder.encode(userDto.getPassword()))
                        .phoneNumber(userDto.getPhoneNumber())
                        .accountType(userDto.getAccountType())
                        .build();

                User newUser = userRepository.saveAndFlush(user);

                if (file.getOriginalFilename().length() != 0) {
                    uploadUserAvatar(UserAvatarFileDto.builder()
                            .file(file)
                            .userId(newUser.getId())
                            .build());
                } else {
                    userRepository.updateAvatar("default.png", newUser.getId());
                }
                return HttpStatus.OK;
            }
            throw new UserNotFoundException("Категория '" + userDto.getAccountType() + "' не найдена в списке доступных");
        }
        throw new UserNotFoundException("Пользователь с таким Email уже существует");
    }

    @Override
    public void updateUser(UserDto userDto, MultipartFile file) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDto actualUser = getUserByEmail(authentication.getName());

        if (userDto.getName().isBlank()) {
            userDto.setName(actualUser.getName());
        }

        if (userDto.getSurname().isBlank()) {
            userDto.setSurname(actualUser.getSurname());
        }

        if (userDto.getAge() == null) {
            userDto.setAge(actualUser.getAge());
        }

        if (userDto.getPhoneNumber().isBlank()) {
            userDto.setPhoneNumber(actualUser.getPhoneNumber());
        }
        User user = User.builder()
                .id(actualUser.getId())
                .name(userDto.getName())
                .surname(userDto.getSurname())
                .age(userDto.getAge())
                .phoneNumber(userDto.getPhoneNumber())
                .build();

        if (!userDto.getPassword().isBlank()) {
            user.setPassword(userDto.getPassword());
        }

        userRepository.updateBy(user.getName(),
                user.getSurname(),
                user.getAge(),
                user.getPhoneNumber(),
                user.getId());

        if (file.getOriginalFilename().length() != 0) {
            uploadUserAvatar(UserAvatarFileDto.builder()
                    .file(file)
                    .userId(actualUser.getId())
                    .build());
        }
    }

    @Override
    public HttpStatus login(AuthUserDto authUserDto) {
        if (authUserDto != null && authUserDto.getUsername() != null && authUserDto.getPassword() != null) {
            String email = authUserDto.getUsername();
            String password = authUserDto.getPassword();

            if (isUserInSystem(email)) {
                UserDto user = getUserByEmail(email);

                if (passwordEncoder.matches(password, user.getPassword())) {
                    log.info("Аутентификация юзера " + email + " успешно пройдена");
                    return HttpStatus.OK;

                } else {
                    log.error("Не верно указан пароль юзером: " + email);
                    throw new UserNotFoundException("Не верно указан пароль юзером: " + email);
                }
            } else {
                log.error("Не найден юзер с email: " + email);
                throw new UserNotFoundException("Не найден юзер с email: " + email);
            }
        } else {
            log.error("Отсутствуют данные в форме");
            throw new UserNotFoundException("Отсутствуют данные в форме");
        }
    }
}
