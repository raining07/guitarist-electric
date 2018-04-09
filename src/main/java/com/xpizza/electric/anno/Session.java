package com.xpizza.electric.anno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 
 * @ClassName: Session 
 * @Description: TODO Session注解,被此注解标注的参数,会从session中取值
 * @author: Xpizza
 * @date: Jul 16, 2017 10:08:46 AM
 */
@Target({ ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface Session {

	/** 存在Session中的名称 */
	String value();

}