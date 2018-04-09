package com.xpizza.electric.anno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.xpizza.bass.util.DateUtil;

/**
 * 
 * @ClassName: Comment
 * @Description: TODO 字段说明
 * @author: Xpizza
 * @date: Jul 23, 2017 11:00:25 AM
 */
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Comment {

	/** 说明内容 */
	String text();

	/** 当属性是关联对象时,指定对象的属性 */
	String property() default "";

	/** 如果属性是日期类型,指定日期的格式 */
	String format() default DateUtil.FORMAT_BAR3;

}
