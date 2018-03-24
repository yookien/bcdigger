package com.bcdigger.core.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/** 
 * @author yookien
 *  
 * 在类或方法上添加@AdminAuth就验证管理员登录 
 */
@Target({ElementType.TYPE, ElementType.METHOD}) 
@Retention(RetentionPolicy.RUNTIME) 
@Documented
public @interface AdminAuth {
}
