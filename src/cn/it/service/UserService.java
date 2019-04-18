package cn.it.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.it.commns.Page;
import cn.it.mapper.UserMapper;
import cn.it.po.User;
@Service("userService")
public class UserService {
	@Autowired
	private UserMapper userMapper;
	
	public	Page totalRecords(User user,Page page){
		//1.准备当前页信息
		int currentPageNum=1;
		if (page.getCurrentPageNum()>0) {
			currentPageNum=page.getCurrentPageNum();
		}
		//2.获取总记录条数
		int totalRecords=userMapper.count(user);
		if(userMapper.count(user)==0){
			currentPageNum=1;
			totalRecords=1;
		}
		
		// 页面记录数
		int pageSize=4;
		if (page.getPageSize()>0) {
			pageSize=page.getPageSize();
		}
		//3.创建page
		Page page2=new Page(currentPageNum, totalRecords, pageSize);
		//4.使用page查询带有分页的结果集
		//List<User> records=userMapper.selectUserParam(user, page2);
		Map<String, Object> params=new HashMap<String, Object>();
		params.put("page", page2);
		params.put("user", user);
		System.out.println(page2.getStartIndex());
		List<User> records=userMapper.findWhitUserParam(params);
		//5把查询的结果集封装到page对象中
		page2.setRecords(records);
		return page2;
	}
	
	public User selectEqUser(User user){
		return userMapper.selectEqUser(user);
	}
	
	public void deleteUserById(int id){
		userMapper.deleteUserById(id);
	}
	
	public void insertWhitUserParam(User user){
		userMapper.insertWhitUserParam(user);
	}
	
	public User selectUserById(int id){
		return userMapper.SelectUserById(id);
	}
	
	public void updateWhitUserParam(User user){
		userMapper.updateWhitUserParam(user);
	}
	
	public List<User> selectAllUser(){
		return userMapper.selectAllUser();
	}

}
