package cn.ruoxijun.springbootmanage.mapper;

import cn.ruoxijun.springbootmanage.pojo.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * mybatis-plus中mapper接口需要去继承
 * BaseMapper 接口并加上对应基类的泛型这里是User
 **/

public interface UserMapper extends BaseMapper<User> {}
