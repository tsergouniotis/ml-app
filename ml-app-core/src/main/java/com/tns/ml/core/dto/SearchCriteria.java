package com.tns.ml.core.dto;

import java.io.Serializable;

import javax.ws.rs.QueryParam;

public class SearchCriteria implements Serializable {

	@QueryParam("page")
	private int page;

	@QueryParam("size")
	private int size;

	public int getPage() {
		if (0 == page) {
			this.page = 1;
		}
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getSize() {
		if (0 == size) {
			this.size = 20;
		}
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

}
