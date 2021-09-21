package cn.ruoxijun.springbootmanage.service;

import cn.ruoxijun.springbootmanage.mapper.UserMapper;
import cn.ruoxijun.springbootmanage.pojo.User;
import cn.ruoxijun.springbootmanage.utils.Result;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.UUID;

/**
 * @program: springboot-manage
 * @description: UserService
 * @author: ruoxijun
 * @create: 2021-09-12 15:28
 **/

@Service
public class UserService {

    @Resource
    private UserMapper userMapper;

    public Result<?> register(User user){
        User res = userMapper.selectOne(Wrappers.<User>lambdaQuery()
                .eq(User::getUsername, user.getUsername()));
        if (res!=null) {
            return Result.error("-1","用户名已存在");
        }
        if (user!=null&&(user.getPassword()==null||user.getPassword().equals(""))) {
            user.setPassword("123456");
        }
        userMapper.insert(user);
        return Result.success();
    }

    public Result<?> login(User user){
        User res = userMapper.selectOne(Wrappers.<User>lambdaQuery()
                .eq(User::getUsername, user.getUsername())
                .eq(User::getPassword, user.getPassword()));
        if (res==null) {
            return Result.error("-1","用户名或密码有误");
        }
        return Result.success(res);
    }

    public Result<?> insert(User user){
        if (user.getPassword()==null){
            long uuid = UUID.randomUUID().getLeastSignificantBits();
            user.setPassword(String.valueOf(uuid).substring(0,9));
        }
        userMapper.insert(user);
        return Result.success();
    }

    public Result<?> update(User user){
        userMapper.updateById(user);
        return Result.success();
    }

    public Result<?> delete(Integer id){
        userMapper.deleteById(id);
        return Result.success();
    }

    /**
     * 分页模糊查询
     * @param pageNum 第几页
     * @param pageSize 一页有多少个
     * @param search 查询字符串
     * @return
     */
    public Result<?> findPage(Integer pageNum,Integer pageSize,String search){
        // 查询器
        LambdaQueryWrapper<User> wrapper = Wrappers.<User>lambdaQuery();
        if (!("".equals(search))&&search!=null){ // 当 search 不为空时进行模糊查询
            wrapper.like(User::getNickName, search);
        }
        Page<User> userPage = userMapper.selectPage(new Page<>(pageNum, pageSize)
                ,wrapper);
        return Result.success(userPage);
    }

}
