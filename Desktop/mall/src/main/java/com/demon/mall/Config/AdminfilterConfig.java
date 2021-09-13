package com.demon.mall.Config;

import com.demon.mall.Filter.CheckoutFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AdminfilterConfig {
    @Bean
    public CheckoutFilter checkoutFilter(){
        return  new CheckoutFilter();
    }
    @Bean(name = "FilterConfig")
    public FilterRegistrationBean adminFilterConfig(){
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(checkoutFilter());
        //设置拦截的URL
        filterRegistrationBean.addUrlPatterns("/admin/category/*");
        filterRegistrationBean.addUrlPatterns("/admin/product/*");
        filterRegistrationBean.addUrlPatterns("/admin/order/*");
        //给过滤器配置设置名字，以便于区分不同的名字
        filterRegistrationBean.setName("adminFilterConfig");
        return filterRegistrationBean;
    }

}
