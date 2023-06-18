package ru.homework.hometask07.controller.mvc;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.homework.hometask07.service.UserService;

@Controller
@Slf4j
@RequestMapping("/user/registration")
@RequiredArgsConstructor
public class MvcUserController {
    private final UserService userService;

    //TODO продожить MVC(фильм и директор), здесь авторизацию делать

}
