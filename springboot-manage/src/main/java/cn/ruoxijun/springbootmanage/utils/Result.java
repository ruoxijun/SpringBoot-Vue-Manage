package cn.ruoxijun.springbootmanage.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @program: springboot-manage
 * @description: Result
 * @author: ruoxijun
 * @create: 2021-09-12 14:49
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result<T> {

    private String code;
    private String msg;
    private T data;

    public Result(T data) {
        this.data = data;
    }

    public static Result success(){
        Result result = new Result<>();
        result.setCode("0");
        result.setMsg("成功");
        return result;
    }

    public static <T> Result<T> success(T data){
        Result<T> result = new Result<>(data);
        result.setCode("0");
        result.setMsg("成功");
        return result;
    }

    public static Result error(String code,String msg){
        Result result = new Result<>();
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }

}
