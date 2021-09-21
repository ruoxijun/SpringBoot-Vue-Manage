package cn.ruoxijun.springbootmanage.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @program: springboot-manage
 * @description: BookPojo
 * @author: ruoxijun
 * @create: 2021-09-12 15:12
 **/

@TableName("book") // 让mybatis-plus对应数据库表
@Data
public class Book {

    /** TableId 绑定主键
     * value与数据库主键名对应，一样时可以省略
     * IdType.AUTO 自增
     */
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;
    private String name;
    private BigDecimal price;
    private String author;
    // 修改时间格式
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date createTime;

}
