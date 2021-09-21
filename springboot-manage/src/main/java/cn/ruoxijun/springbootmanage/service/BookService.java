package cn.ruoxijun.springbootmanage.service;

import cn.ruoxijun.springbootmanage.mapper.BookMapper;
import cn.ruoxijun.springbootmanage.pojo.Book;
import cn.ruoxijun.springbootmanage.utils.Result;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @program: springboot-manage
 * @description: BookService
 * @author: ruoxijun
 * @create: 2021-09-12 15:28
 **/

@Service
public class BookService {

    @Resource
    private BookMapper bookMapper;

    public Result<?> insert(Book book){
        bookMapper.insert(book);
        return Result.success();
    }

    public Result<?> update(Book book){
        bookMapper.updateById(book);
        return Result.success();
    }

    public Result<?> delete(Integer id){
        bookMapper.deleteById(id);
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
        LambdaQueryWrapper<Book> wrapper = Wrappers.<Book>lambdaQuery();
        if (!("".equals(search))&&search!=null){ // 当 search 不为空时进行模糊查询
            wrapper.like(Book::getName, search);
        }
        Page<Book> bookPage = bookMapper.selectPage(new Page<>(pageNum, pageSize)
                ,wrapper);
        return Result.success(bookPage);
    }

}
