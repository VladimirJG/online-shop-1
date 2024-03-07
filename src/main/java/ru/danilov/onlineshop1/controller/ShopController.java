package ru.danilov.onlineshop1.controller;

import com.fasterxml.jackson.annotation.JsonView;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.danilov.onlineshop1.model.Order;
import ru.danilov.onlineshop1.model.User;
import ru.danilov.onlineshop1.model.Views;
import ru.danilov.onlineshop1.service.UserService;
import ru.danilov.onlineshop1.util.UserValidator;

import java.util.List;

import static ru.danilov.onlineshop1.util.ErrorsUtil.returnErrors;

@RestController
@RequestMapping("/shop")
public class ShopController {
    private final UserService userService;
    private final UserValidator userValidator;

    @Autowired
    public ShopController(UserService userService, UserValidator userValidator) {
        this.userService = userService;
        this.userValidator = userValidator;
    }

    @JsonView(Views.UserSummary.class)
    @GetMapping()
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @JsonView(Views.UserDetails.class)
    @GetMapping("/{id}")
    public User getUserBuId(@PathVariable("id") int id) {
        return userService.getUserById(id);
    }

    @PostMapping()
    public ResponseEntity<User> createUser(@RequestBody @Valid User user, BindingResult bindingResult) {
        userValidator.validate(user, bindingResult);
        if (bindingResult.hasErrors()) {
            returnErrors(bindingResult);
        }
        userService.createUser(user);
        return ResponseEntity.ok(user);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<User> update(@RequestBody @Valid User user, BindingResult bindingResult, @PathVariable("id") int id) {
        userValidator.validate(user, bindingResult);
        if (bindingResult.hasErrors()) {
            returnErrors(bindingResult);
        }
        userService.updateUser(id, user);
        return ResponseEntity.ok(user);
    }

    @DeleteMapping("/{id}")
    public HttpStatus deleteUser(@PathVariable("id") int id) {
        userService.delete(id);
        return HttpStatus.OK;
    }

    @PatchMapping("/add/{id}")
    public HttpStatus addNewOrder(@PathVariable("id") int id, @RequestBody Order order) {
        userService.addOrderToUser(id, order);
        return HttpStatus.OK;
    }
}