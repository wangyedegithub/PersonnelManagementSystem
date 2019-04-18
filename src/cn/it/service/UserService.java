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
		//1.׼����ǰҳ��Ϣ
		int currentPageNum=1;
		if (page.getCurrentPageNum()>0) {
			currentPageNum=page.getCurrentPageNum();
		}
		//2.��ȡ�ܼ�¼����
		int totalRecords=userMapper.count(user);
		if(userMapper.count(user)==0){
			currentPageNum=1;
			totalRecords=1;
		}
		
		// ҳ���¼��
		int pageSize=4;
		if (page.getPageSize()>0) {
			pageSize=page.getPageSize();
		}
		//3.����page
		Page page2=new Page(currentPageNum, totalRecords, pageSize);
		//4.ʹ��page��ѯ���з�ҳ�Ľ����
		//List<User> records=userMapper.selectUserParam(user, page2);
		Map<String, Object> params=new HashMap<String, Object>();
		params.put("page", page2);
		params.put("user", user);
		System.out.println(page2.getStartIndex());
		List<User> records=userMapper.findWhitUserParam(params);
		//5�Ѳ�ѯ�Ľ������װ��page������
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
