package cn.ruoxijun.springbootmanage.mapper;

import cn.ruoxijun.springbootmanage.pojo.Book;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * mybatis-plus中mapper接口需要去继承
 * BaseMapper 接口并加上对应基类的泛型这里是Book
 **/

public interface BookMapper extends BaseMapper<Book> {}
