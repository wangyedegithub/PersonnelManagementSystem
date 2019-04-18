package cn.it.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;
import org.springframework.transaction.annotation.Transactional;


import cn.it.commns.Page;
import cn.it.dynaDql.UserDynaSql;
import cn.it.po.User;


public interface UserMapper {
	
	@Select("select * from user_inf where loginname = #{loginname} and password =#{password}")
	User selectEqUser(User user);
	
	@Delete("delete from user_inf where id = #{id}")
	void deleteUserById(int id);
	
	@Select("select * from user_inf where id =#{id}")
	User SelectUserById(int id);
	
	@InsertProvider(type=UserDynaSql.class,method="insertWhitUserParam")
	void insertWhitUserParam(User user);
	
	@UpdateProvider(type=UserDynaSql.class,method="updateWhitUserParam")
	void updateWhitUserParam(User user);
	
	
	
	@Select("select * from user_inf")
	List<User> selectAllUser();
	
	@SelectProvider(type=UserDynaSql.class,method="count")
	Integer count(User user);
	//∂ØÃ¨≤È’“
	/*@Transactional
	@SelectProvider(type=UserDynaSql.class,method="selectUserParam")
	List<User> selectUserParam(User user,Page page);*/
	
	@SelectProvider(type=UserDynaSql.class,method="findWhitUserParam")
	List<User> findWhitUserParam(Map<String, Object> params);
		
	
	
}
