package ru.homework.hometask07.controller.mvc;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.homework.hometask07.controller.dto.UserDto;
import ru.homework.hometask07.mapper.UserMapper;
import ru.homework.hometask07.service.UserService;

import static ru.homework.hometask07.constants.UserRolesConstants.ADMIN;

@Controller
@Slf4j
@RequestMapping("/users/registration")
@RequiredArgsConstructor
public class MvcUserController {
    private final UserService userService;
    private final UserMapper userMapper;

    @GetMapping("")
    public String registration(Model model) {
        model.addAttribute("userForm", new UserDto());
        return "registration";
    }

    @PostMapping("")
    public String registration(@ModelAttribute("userForm") UserDto userDTO,
                               BindingResult bindingResult) {
        if (userDTO.getLogin().equalsIgnoreCase(ADMIN.name()) || userService.getUserByLogin(userDTO.getLogin()) != null) {
            bindingResult.rejectValue("login", "error.login", "Такой логин уже существует");
            return "registration";
        }
        if (userService.getUserByEmail(userDTO.getEmail()) != null) {
            bindingResult.rejectValue("email", "error.email", "Такой e-mail уже существует");
            return "registration";
        }
        userDTO.setRoleID(1);   // заглушка  //
        userService.createUser(userMapper.dtoToEntity(userDTO));
        return "redirect:login";
    }
}
