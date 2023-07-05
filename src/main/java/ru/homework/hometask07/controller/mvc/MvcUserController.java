package ru.homework.hometask07.controller.mvc;

import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.homework.hometask07.controller.dto.UserDto;
import ru.homework.hometask07.controller.dto.UserUpdateDto;
import ru.homework.hometask07.dao.entity.UserEntity;
import ru.homework.hometask07.mapper.RoleMapper;
import ru.homework.hometask07.mapper.UserMapper;
import ru.homework.hometask07.service.UserService;

import java.util.Collections;
import java.util.Objects;

import static ru.homework.hometask07.constants.UserRolesConstants.ADMIN;

@Controller
@Slf4j
@RequestMapping("/users/registration")
@RequiredArgsConstructor
public class MvcUserController {
    private final UserService userService;
    private final UserMapper userMapper;
    private final RoleMapper roleMapper;

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
        userDTO.setRoleID(1L);   // заглушка  //
        userDTO.setOrderIds(Collections.emptyList());
        userService.create(userMapper.toEntity(userDTO));
        return "redirect:/";
    }

    @GetMapping("/{id}")
    public String userDataUpdate(Model model, @PathVariable Long id) {
        UserEntity entity = userService.getOne(id);
        model.addAttribute("user", userMapper.fromEntityToUpdateDto(entity));
        model.addAttribute("userId", id);
        return "users/updateUser";
    }

    @PostMapping("/updater")
    public String userUpdateData(@ModelAttribute("userUpdateForm") UserUpdateDto updateDto) {
        UserEntity userEntity = userService.getOne(updateDto.getId());
        userService.create(userMapper.fromUpdateDtoToEntity(userEntity, updateDto));
        return "redirect:/";
    }

    @GetMapping("/remember-password")
    public String rememberPassword() {
        return "users/rememberPassword";
    }

    @PostMapping("/remember-password")
    public String rememberPassword(@ModelAttribute("changePasswordForm") UserDto userDto) {
        UserEntity UserEntity = userService.getUserByEmail(userDto.getEmail());
        if (Objects.isNull(UserEntity)) {
            return "Error!";//TODO: Error!
        } else {
            userService.sendChangePasswordEmail(UserEntity);
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
                                 @ModelAttribute("changePasswordForm") UserDto userDto) {
        userService.changePassword(uuid, userDto.getPassword());
        return "redirect:/login";
    }

    @PostMapping("/update")
    public String updateUserDate(@PathParam(value = "userId") Long userId,
                                 @ModelAttribute("userUpdateForm") UserDto userDto) {
        userService.update(userId, userMapper.toEntity(userDto));
        return "redirect:/";
    }

    @GetMapping("/profile/{id}")
    public String userProfile(@PathVariable(value = "id") Long userId,
                              Model model) {
        UserEntity userEntity = userService.getOne(userId);
        model.addAttribute("user", userMapper.toDto(userEntity));
        model.addAttribute("role", roleMapper.toDto(userEntity.getRole()));
        return "users/viewUser";
    }

    @GetMapping("/viewall")
    public String getAll(@RequestParam(value = "page", defaultValue = "1") int page,
                         @RequestParam(value = "size", defaultValue = "5") int pageSize,
                         Model model) {
        PageRequest pageRequest = PageRequest.of(page - 1, pageSize, Sort.by(Sort.Direction.ASC, "login"));
        Page<UserDto> users = userService.listAll(pageRequest);
        model.addAttribute("users", users);
        return "users/viewAllUsers";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable Long id) {
        userService.deleteSoft(id);
        return "redirect:/users/registration/viewall";
    }

    @GetMapping("/restore/{id}")
    public String restoreUser(@PathVariable Long id) {
        userService.restore(id);
        return "redirect:/users/registration/viewall";
    }
}
