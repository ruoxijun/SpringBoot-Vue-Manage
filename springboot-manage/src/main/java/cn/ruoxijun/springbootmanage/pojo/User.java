package cn.ruoxijun.springbootmanage.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @program: springboot-manage
 * @description: UserPojo
 * @author: ruoxijun
 * @create: 2021-09-12 15:12
 **/

@TableName("user") // 让mybatis-plus对应数据库表
@Data
public class User {

    /** TableId 绑定主键
     * value与数据库主键名对应，一样时可以省略
     * IdType.AUTO 自增
     */
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;
    private String username;
    private String nickName;
    private String password;
    private Integer age;
    private String sex;
    private String address;

}
