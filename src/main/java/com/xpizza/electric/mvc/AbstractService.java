package com.xpizza.electric.mvc;

/**
 * 
 * @ClassName: AbstractService
 * @Description: TODO 抽象业务
 * @author: Xpizza
 * @date: Jul 23, 2017 9:52:58 PM
 */
public class AbstractService {

	/**
	 * 
	 * @Title: getPropertyLike 
	 * @Description: TODO 获取字符串属性的模糊形式
	 * @param property 字符串屬性
	 * @return
	 * @return: String
	 */
	protected String getPropertyLike(String property) {
		if (property == null) {
			property = "";
		}
		return "%" + property + "%";
	}

}
