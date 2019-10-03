package com.yanfei.springFrame.Pojo;

public abstract class BaseQueryCondition {
	private int pageIndex = 0;
	private int pageSize = 20;
	
	public int getPageIndex() {
		return pageIndex;
	}
	
	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}
	
	public int getPageSize() {
		return pageSize;
	}
	
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
}
