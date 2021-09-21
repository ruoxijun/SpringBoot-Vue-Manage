package cn.ruoxijun.springbootmanage.controller;

import cn.ruoxijun.springbootmanage.pojo.User;
import cn.ruoxijun.springbootmanage.service.UserService;
import cn.ruoxijun.springbootmanage.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @program: springboot-manage
 * @description: UserController
 * @author: ruoxijun
 * @create: 2021-09-12 15:08
 **/

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public Result<?> register(@RequestBody User user){
        return userService.register(user);
    }

    @PostMapping("/login")
    public Result<?> login(@RequestBody User user){
        return userService.login(user);
    }

    @PostMapping
    public Result<?> save(@RequestBody User user){
        return userService.insert(user);
    }

    @PutMapping
    public Result<?> update(@RequestBody User user) {
        return userService.update(user);
    }

    @DeleteMapping
    public Result<?> delete(@RequestParam Integer id){
        return userService.delete(id);
    }

    @GetMapping
    public Result<?> findPage(@RequestParam(defaultValue = "1") Integer pageNum
                            ,@RequestParam(defaultValue = "10") Integer pageSize
                            ,@RequestParam(defaultValue = "") String search){
        return userService.findPage(pageNum,pageSize,search);
    }

}
