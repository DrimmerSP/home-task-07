package ru.homework.hometask07.controller.mvc;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.homework.hometask07.controller.dto.UserDto;
import ru.homework.hometask07.service.UserService;

@Controller
@Slf4j
@RequestMapping("/user/registration")
@RequiredArgsConstructor
public class MvcUserController {
    private final UserService userService;

    @GetMapping("/registration")
    public String registration(Model model) {
        model.addAttribute("userForm", new UserDto());
        return "registration";
    }

    //TODO продолжить добавлять кучу файлов и редактировать для регистрации пользователя(МвцЮзерКонтроллер,
    // ЮзерСервис, ДженерикСервис и т.д.)
 /*   @PostMapping("/registration")
    public String registration(@ModelAttribute("userForm") UserDto userDto,
                               BindingResult bindingResult) {
        if (userDto.getLogin().equalsIgnoreCase(ADMIN) || userService.getUserByLogin(userDto.getLogin()) != null) {
            bindingResult.rejectValue("login", "error.login", "Такой логин уже существует");
            return "registration";
        }
        if (userService.getUserByEmail(userDto.getEmail()) != null) {
            bindingResult.rejectValue("email", "error.email", "Такой e-mail уже существует");
            return "registration";
        }
        userService.create(userDto);
        return "redirect:login";
    }

    //MINIO

    @GetMapping("/remember-password")
    public String rememberPassword() {
        return "users/rememberPassword";
    }

    @PostMapping("/remember-password")
    public String rememberPassword(@ModelAttribute("changePasswordForm") UserDTO userDto) {
        userDto = userService.getUserByEmail(userDto.getEmail());
        if (Objects.isNull(userDto)) {
            return "Error!";//TODO: Error!
        } else {
            userService.sendChangePasswordEmail(userDto);
            return "redirect:/login";
        }
    }

    @GetMapping("/change-password")
    public String changePassword(@PathParam(value = "uuid") String uuid,
                                 Model model) {
        model.addAttribute("uuid", uuid);
        return "users/changePassword";
    }

    @PostMapping("/change-password")
    public String changePassword(@PathParam(value = "uuid") String uuid,
                                 @ModelAttribute("changePasswordForm") UserDTO userDTO) {
        userService.changePassword(uuid, userDTO.getPassword());
        return "redirect:/login";
    }*/
    //TODO продожить MVC(фильм и директор), здесь авторизацию делать

}
