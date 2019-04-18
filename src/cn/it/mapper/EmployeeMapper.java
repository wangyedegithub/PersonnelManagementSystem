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

import cn.it.dynaDql.EmployeeDynaSql;
import cn.it.po.Employee;

public interface EmployeeMapper {
	@SelectProvider(type=EmployeeDynaSql.class,method="findWhitEmployeeParam")
	@Results({
		@Result(id=true,column="id",property="id"),
		@Result(column="CARD_ID",property="cardId"),
		@Result(column="POST_CODE",property="postCode"),
		@Result(column="QQ_NUM",property="qqNum"),
		@Result(column="BIRTHDAY",property="birthday",javaType=Date.class),
		@Result(column="CREATE_DATE",property="createDate",javaType=Date.class),
		@Result(column="DEPT_ID",property="dept",
				one=@One(select="cn.it.mapper.DeptMapper.selectDeptById")),
		@Result(column="JOB_ID",property="job",
				one=@One(select="cn.it.mapper.JobMapper.selectJobById"))
	})
	List<Employee> findWhitEmployeeParam(Map<String, Object> params);
	
	@SelectProvider(type=EmployeeDynaSql.class,method="count")
	Integer count(Employee employee);
	
	@UpdateProvider(type=EmployeeDynaSql.class,method="updateWhitEmployeeParam")
	void updateWhitEmployeeParam(Employee employee);
	
	@InsertProvider(type=EmployeeDynaSql.class,method="insertWhitEmployeeParam")
	void insertWhitEmployeeParam(Employee employee) ;
	
	
	@Delete("delete from  employee_inf where id =#{id}")
	void deleteEmployeeById(int id);
	
	@Select("select * from  employee_inf")
	@Results({
		@Result(id=true,column="id",property="id"),
		@Result(column="CARD_ID",property="cardId"),
		@Result(column="POST_CODE",property="postCode"),
		@Result(column="QQ_NUM",property="qqNum"),
		@Result(column="BIRTHDAY",property="birthday",javaType=Date.class),
		@Result(column="CREATE_DATE",property="createDate",javaType=Date.class),
		@Result(column="DEPT_ID",property="dept",
				one=@One(select="cn.it.mapper.DeptMapper.selectDeptById")),
		@Result(column="JOB_ID",property="job",
			one=@One(select="cn.it.mapper.JobMapper.selectJobById"))
	})
	List<Employee> selectAllEmployee();
	
	@Select("select * from employee_inf where id=#{id}")
	@Results({
		@Result(id=true,column="id",property="id"),
		@Result(column="CARD_ID",property="cardId"),
		@Result(column="POST_CODE",property="postCode"),
		@Result(column="QQ_NUM",property="qqNum"),
		@Result(column="BIRTHDAY",property="birthday",javaType=Date.class),
		@Result(column="CREATE_DATE",property="createDate",javaType=Date.class),
		@Result(column="DEPT_ID",property="dept",
				one=@One(select="cn.it.mapper.DeptMapper.selectDeptById")),
		@Result(column="JOB_ID",property="job",
				one=@One(select="cn.it.mapper.JobMapper.selectJobById"))
	})
	Employee selectEmployeeById(int id);
	
}
