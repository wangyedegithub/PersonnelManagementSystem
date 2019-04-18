package cn.it.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;

import cn.it.dynaDql.JobDynaSql;
import cn.it.po.Job;

public interface JobMapper {
	@SelectProvider(type=JobDynaSql.class,method="findWhitJobParam")
	List<Job> findWhitJobParam(Map<String, Object> params);
	
	@SelectProvider(type=JobDynaSql.class,method="count")
	Integer count(Job job);
	
	@UpdateProvider(type=JobDynaSql.class,method="updateWhitJobParam")
	void updateWhitJobParam(Job job);
	
	@InsertProvider(type=JobDynaSql.class,method="insertWhitJobParam")
	void insertWhitJobParam(Job job) ;
	
	@Delete("delete from job_inf where id =#{id}")
	void deleteJobById(int id);
	
	@Select("select * from job_inf")
	List<Job> selectAllJob();
	
	@Select("select * from job_inf where id=#{id}")
	Job selectJobById(int id);
}
