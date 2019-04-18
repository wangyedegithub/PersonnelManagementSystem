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

import cn.it.dynaDql.DocumentDynaSql;
import cn.it.po.Document;



public interface DocumentMapper {
	@SelectProvider(type=DocumentDynaSql.class,method="findWhitDocumentParam")
	@Results({
		@Result(id=true,column="id",property="id"),
		@Result(column="CREATE_DATE",property="createDate",javaType=Date.class),
		@Result(column="USER_ID",property="user",
				one=@One(select="cn.it.mapper.UserMapper.SelectUserById")),
	})
	List<Document> findWhitDocumentParam(Map<String, Object> params);
	
	@SelectProvider(type=DocumentDynaSql.class,method="count")
	Integer count(Document documents);
	
	@UpdateProvider(type=DocumentDynaSql.class,method="updateWhitDocumentParam")
	void updateWhitDocumentParam(Document document);
	
	@InsertProvider(type=DocumentDynaSql.class,method="insertWhitDocumentParam")
	void insertWhitDocumentParam(Document document) ;
	
	
	@Delete("delete from document_inf where id =#{id}")
	void deleteDocumentById(int id);
	
	@Select("select * from document_inf")
	@Results({
		@Result(id=true,column="id",property="id"),
		@Result(column="CREATE_DATE",property="createDate",javaType=Date.class),
		@Result(column="USER_ID",property="user",
				one=@One(select="cn.it.mapper.UserMapper.SelectUserById")),
	})
	List<Document> selectAllDocument();
	
	@Select("select * from document_inf where id=#{id}")
	@Results({
		@Result(id=true,column="id",property="id"),
		@Result(column="CREATE_DATE",property="createDate",javaType=Date.class),
		@Result(column="USER_ID",property="user",
				one=@One(select="cn.it.mapper.UserMapper.SelectUserById")),
	})
	Document selectDocumentById(int id);
}
