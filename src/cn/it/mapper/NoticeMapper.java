package cn.it.mapper;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;

import cn.it.dynaDql.NoticeDynaSql;
import cn.it.po.Notice;

public interface NoticeMapper {
	@SelectProvider(type=NoticeDynaSql.class,method="findWhitNoticeParam")
	@Results({
		@Result(id=true,column="id",property="id"),
		@Result(column="CREATE_DATE",property="createDate",javaType=Date.class),
		@Result(column="USER_ID",property="user",
				one=@One(select="cn.it.mapper.UserMapper.SelectUserById")),
	})
	List<Notice> findWhitNoticeParam(Map<String, Object> params);
	
	@SelectProvider(type=NoticeDynaSql.class,method="count")
	Integer count(Notice notices);
	
	@UpdateProvider(type=NoticeDynaSql.class,method="updateWhitNoticeParam")
	void updateWhitNoticeParam(Notice notice);
	
	@InsertProvider(type=NoticeDynaSql.class,method="insertWhitNoticeParam")
	void insertWhitNoticeParam(Notice notice) ;
	
	
	@Delete("delete from Notice_inf where id =#{id}")
	void deleteNoticeById(int id);
	
	@Select("select * from Notice_inf")
	@Results({
		@Result(id=true,column="id",property="id"),
		@Result(column="CREATE_DATE",property="createDate",javaType=Date.class),
		@Result(column="USER_ID",property="user",
				one=@One(select="cn.it.mapper.UserMapper.SelectUserById")),
	})
	List<Notice> selectAllNotice();
	
	@Select("select * from Notice_inf where id=#{id}")
	@Results({
		@Result(id=true,column="id",property="id"),
		@Result(column="CREATE_DATE",property="createDate",javaType=Date.class),
		@Result(column="USER_ID",property="user",
				one=@One(select="cn.it.mapper.UserMapper.SelectUserById")),
	})
	Notice selectNoticeById(int id);
}
