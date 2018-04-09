package com.xpizza.electric.anno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 
 * @ClassName: Json
 * @Description: TODO 被此注解表明返回给前端时可处理成Json
 * @author: Xpizza
 * @date: Jul 16, 2017 10:08:32 AM
 */
// @Target({ ElementType.TYPE })
@Target({ ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
public @interface Json {

	String encoding() default "UTF-8";

}