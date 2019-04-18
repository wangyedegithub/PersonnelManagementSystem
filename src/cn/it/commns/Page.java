package cn.it.commns;

import java.util.List;


/**
 * @author ��ү
 *���ڷ�װ���ݶ���
 */
public class Page {
	private int currentPageNum;//当前页
	private int pageSize;//每页尺寸
	private int totalRecords;//总记录数
	private int startIndex;//当前页的数据处于多少条记录
	private int totalPageNum;//总页数 
	private int nextPageNum;//下一页
	private int prePageNum;//上一页
	private List records;//记录数
	private int beginPageNum;//页面显示开始数
	private int endPageNum;//页面
	private int i=1;
	
	public List getRecords() {
		return records;
	}
	public void setRecords(List records) {
		this.records = records;
	}

	/**
	 * @author ��ү
	 *Page����Ҫ��õ�ǰҳ�����ܼ�¼����
	 */
	public Page(){
		
	}
	
	public Page(int currentPageNum,int totalRecords,int pageSize)
	{
		this.currentPageNum=currentPageNum;
		this.totalRecords=totalRecords;
		this.pageSize=pageSize;
		
		//���㿪ʼ����
		startIndex=(currentPageNum-1)*pageSize;
		
		//������ҳ��
		totalPageNum=totalRecords%pageSize==0 ? totalRecords/pageSize : totalRecords/pageSize+1;
		
		//
		if(totalPageNum < 9){
			beginPageNum = 1;
			endPageNum = totalPageNum;
		}
		else{
			beginPageNum = currentPageNum - 4;
			endPageNum = currentPageNum + 4;
			if(beginPageNum < 1){
				beginPageNum = 1;
				endPageNum = beginPageNum + 8;
			}
			if(endPageNum > totalPageNum){
				endPageNum = totalPageNum;
				beginPageNum = endPageNum - 8;
			}
		}
		if(i<totalPageNum){
			i=i+1;
		}
	}
	

	
	//������һҳ
	public int getNextPageNum() {
		nextPageNum=currentPageNum+1;
		if(nextPageNum>totalPageNum)
		{
			nextPageNum=totalPageNum;
		}
		return nextPageNum;
	}
	//������һҳ
	public int getPrePageNum() {
		prePageNum=currentPageNum-1;
		if (prePageNum<1) {
			prePageNum=1;
		}
		return prePageNum;
	}


	public int getCurrentPageNum() {
		return currentPageNum;
	}


	public void setCurrentPageNum(int currentPageNum) {
		this.currentPageNum = currentPageNum;
	}


	public int getPageSize() {
		return pageSize;
	}


	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}


	public int getTotalRecords() {
		return totalRecords;
	}


	public void setTotalRecords(int totalRecords) {
		this.totalRecords = totalRecords;
	}


	public int getStartIndex() {
		return startIndex;
	}


	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}


	public int getTotalPageNum() {
		return totalPageNum;
	}


	public void setTotalPageNum(int totalPageNum) {
		this.totalPageNum = totalPageNum;
	}


	public void setNextPageNum(int nextPageNum) {
		this.nextPageNum = nextPageNum;
	}


	public void setPrePageNum(int prePageNum) {
		this.prePageNum = prePageNum;
	}
	public int getBeginPageNum() {
		return beginPageNum;
	}
	public void setBeginPageNum(int beginPageNum) {
		this.beginPageNum = beginPageNum;
	}
	public int getEndPageNum() {
		return endPageNum;
	}
	public void setEndPageNum(int endPageNum) {
		this.endPageNum = endPageNum;
	}
	
	
}
