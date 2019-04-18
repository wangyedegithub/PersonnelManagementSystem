package cn.it.po;

import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

public class Document {
	private Integer id;		//编号	
	private String title;	//标题
	private String fileName;	//文件名
	private MultipartFile file;	//文件
	private String remark;	//备注
	private Date createDate;	//上传时间
	private User user;		//上传人
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public MultipartFile getFile() {
		return file;
	}
	public void setFile(MultipartFile file) {
		this.file = file;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	@Override
	public String toString() {
		return "Document [id=" + id + ", title=" + title + ", fileName="
				+ fileName + ", file=" + file + ", remark=" + remark
				+ ", createDate=" + createDate + "]";
	}
	
}
