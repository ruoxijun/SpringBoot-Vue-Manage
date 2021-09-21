package cn.ruoxijun.springbootmanage.controller;

import cn.ruoxijun.springbootmanage.pojo.Book;
import cn.ruoxijun.springbootmanage.service.BookService;
import cn.ruoxijun.springbootmanage.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @program: springboot-manage
 * @description: BookController
 * @author: ruoxijun
 * @create: 2021-09-12 15:08
 **/

@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    private BookService bookService;

    @PostMapping
    public Result<?> save(@RequestBody Book book){
        return bookService.insert(book);
    }

    @PutMapping
    public Result<?> update(@RequestBody Book book) {
        return bookService.update(book);
    }

    @DeleteMapping
    public Result<?> delete(@RequestParam Integer id){
        return bookService.delete(id);
    }

    @GetMapping
    public Result<?> findPage(@RequestParam(defaultValue = "1") Integer pageNum
                            ,@RequestParam(defaultValue = "10") Integer pageSize
                            ,@RequestParam(defaultValue = "") String search){
        return bookService.findPage(pageNum,pageSize,search);
    }

}
