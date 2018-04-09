package com.xpizza.electric;

/**
 * 恒量
 */
public class Constant {

	/* 特殊页面,不做拦截 */
	/** 登录界面 */
	public static final String PAGE_SIGN_IN = "sign-in.page";
	/** 注册界面 */
	public static final String PAGE_SIGN_UP = "sign-up.page";
	/** 重置密码界面 */
	public static final String PAGE_RESET_PASSWORD = "reset-pwd.page";

	/* SESEION KEY BEGIN */
	public static final String SESSION_USER = "user";

	public static final String SESSION_USERNAME = "userName";

	public static final String SESSION_USERID = "userId";
	/* SESEION KEY END */

	public static final String[] PAGE_ACCOUNT = null;

	/** 用户有效 */
	public static final int USER_EFFECTIVE = 1;

	/** 用户有效 */
	public static final int USER_UNEFFECTIVE = 0;

}
