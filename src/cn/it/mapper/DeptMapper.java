package cn.it.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;

import cn.it.dynaDql.DeptDynaSql;
import cn.it.po.Dept;

public interface DeptMapper {
	
	@SelectProvider(type=DeptDynaSql.class,method="findWhitDeptParam")
	List<Dept> findWhitDeptParam(Map<String, Object> params);
	
	@SelectProvider(type=DeptDynaSql.class,method="count")
	Integer count(Dept depts);
	
	@UpdateProvider(type=DeptDynaSql.class,method="updateWhitDeptParam")
	void updateWhitDeptParam(Dept dept);
	
	@InsertProvider(type=DeptDynaSql.class,method="insertWhitDeptParam")
	void insertWhitDeptParam(Dept dept) ;
	
	
	@Delete("delete from dept_inf where id =#{id}")
	void deleteDeptById(int id);
	
	@Select("select * from dept_inf")
	List<Dept> selectAllDept();
	
	@Select("select * from dept_inf where id=#{id}")
	Dept selectDeptById(int id);
}
