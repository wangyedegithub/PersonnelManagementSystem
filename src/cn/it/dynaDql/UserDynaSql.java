package cn.it.dynaDql;

import java.util.Map;

import org.apache.ibatis.jdbc.SQL;

import cn.it.commns.Page;
import cn.it.po.User;

public class UserDynaSql {
	/*public String selectUserParam(final User user,Page page){
		 return new SQL(){{
			SELECT ("*");
			FROM ("user_inf");
			if(user.getUsername()!=null && user.getUsername()!=""){
				WHERE ("username LIKE CONCAT ('%',#{user.username},'%')");
			}
			if(user.getStatus()!=null){
				WHERE ("status = #{user.status}");
			}
		}
		}.toString();
		if(page!=null){
		sql +="limit #{page.startIndex},#{page.pageSize}" ;
	}
	}*/
	 public String findWhitUserParam(final Map<String, Object> params){
		 String sql=new SQL(){{
			SELECT ("*");
			FROM ("user_inf");
			if(params.get("user")!=null){
				User user=(User)params.get("user");
			if(user.getUsername()!=null && user.getUsername()!=""){
				WHERE ("username LIKE CONCAT ('%',#{user.username},'%')");
			}
			if(user.getStatus()!=null){
				WHERE ("status = #{user.status}");
			}}
		}}.toString();
		if(params.get("page")!=null){
			sql += " limit #{page.startIndex},#{page.pageSize}";
		}
		return sql;
	}
	
	public String count(final User user){
		return new SQL(){{
			SELECT ("count(*)");
			FROM ("user_inf");
			if(user.getUsername()!=null && user.getUsername()!=""){
				WHERE ("username LIKE CONCAT ('%',#{username},'%')");
			}
			if(user.getStatus()!=null){
				WHERE ("status = #{status}");
			}
		}
		}.toString();
	}
	
	
	public String updateWhitUserParam(final User user){
		return new SQL(){{
			UPDATE ("user_inf");
			if(user.getStatus()!=null&&!user.getStatus().equals("")){
				SET ("status=#{status}");
			}
			if(user.getLoginname()!=null&&user.getLoginname()!=""){
				SET ("loginname = #{loginname}");
			}
			if(user.getPassword()!=null&&user.getPassword()!=""){
				SET ("password = #{password}");
			}
			if(user.getUsername()!=null&&user.getUsername()!="")
			{
				SET ("username = #{username}");
			}
			WHERE("id=#{id}");
		}
		}.toString();
	}
	
	public String insertWhitUserParam(final User user){
		return new SQL(){{
			INSERT_INTO("user_inf");
			if(user.getStatus()!=null&&!user.getStatus().equals("")){
				VALUES("status", "#{status}");
			}
			if(user.getLoginname()!=null&&user.getLoginname()!=""){
				VALUES("loginname","#{loginname}");
			}
			if(user.getPassword()!=null&&user.getPassword()!=""){
				VALUES ("password","#{password}");
			}
			if(user.getUsername()!=null&&user.getUsername()!="")
			{
				VALUES ("username","#{username}");
			}
		}
		}.toString();
	}
	
	

}
