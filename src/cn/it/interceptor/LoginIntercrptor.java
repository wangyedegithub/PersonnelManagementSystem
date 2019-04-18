package cn.it.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import cn.it.po.User;

public class LoginIntercrptor implements HandlerInterceptor {

	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handel, Exception respones)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	public void postHandle(HttpServletRequest request, HttpServletResponse response,
			Object handel, ModelAndView respones) throws Exception {
		// TODO Auto-generated method stub
		
	}
	//提交给handel之前,常用于身份验证
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
			Object handel) throws Exception {
		// TODO Auto-generated method stub
		String url=request.getRequestURI();
		//对login.action放行
		if(url.indexOf("login.action")>=0){
			return true;
		}
		if(url.indexOf("logout.action")>=0){
			return true;
		}
		HttpSession session=request.getSession();
		User user=(User) session.getAttribute("user");
		if (user!=null) {
			return true;
		}
		//没有跳转到登录页面
		//request.getRequestDispatcher("logout.action").forward(request, response);
		return false;
	}

}
