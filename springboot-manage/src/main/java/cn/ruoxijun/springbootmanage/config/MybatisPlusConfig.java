package cn.ruoxijun.springbootmanage.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @program: springboot-manage
 * @description: MybatisPlusConfig 分页插件配置类
 * @author: ruoxijun
 * @create: 2021-09-12 14:43
 **/
@Configuration
@MapperScan("cn.ruoxijun.springbootmanage.mapper") // 扫描包
public class MybatisPlusConfig {

    /**
     * 分页插件配置
     * DbType.MYSQL 表示数据库类型
     * @return
     */
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
        return interceptor;
    }

}
