package ru.danilov.onlineshop1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.danilov.onlineshop1.exception.UserNotFoundException;
import ru.danilov.onlineshop1.model.Order;
import ru.danilov.onlineshop1.model.User;
import ru.danilov.onlineshop1.repository.OrderRepository;
import ru.danilov.onlineshop1.repository.UserRepository;


import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class UserService {
    private final UserRepository userRepository;
    private final OrderRepository orderRepository;

    @Autowired
    public UserService(UserRepository userRepository, OrderRepository orderRepository) {
        this.userRepository = userRepository;
        this.orderRepository = orderRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(int id) {
        User user = userRepository.findById(id).orElseThrow(UserNotFoundException::new);
        user.setOrderList(orderRepository.findAllByOwnerId(id));
        return user;
    }

    public Optional<User> getUserByName(String name) {
        return userRepository.findByName(name);
    }

    @Transactional
    public void createUser(User user) {
        userRepository.save(user);
    }

    @Transactional
    public void updateUser(int id, User updateUser) {
        updateUser.setId(id);
        userRepository.save(updateUser);
    }

    @Transactional
    public void delete(int id) {
        userRepository.deleteById(id);
    }

    @Transactional
    public void addOrderToUser(int id, Order order){
        order.setOwner(userRepository.findById(id).orElseThrow(UserNotFoundException::new));
        orderRepository.save(order);
    }
}
