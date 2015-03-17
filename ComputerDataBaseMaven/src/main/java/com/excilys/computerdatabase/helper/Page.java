package com.excilys.computerdatabase.helper;

public class Page {

	public int limit = 50;
	public int offset = 0;
	public int actualPage = 0;
	public int nbPage = 0;
	public String order = "id";
	public String sort = "ASC";
	public String search = "";
	
	public boolean isEmpty() {
		if (this.limit == 0 && this.offset == 0 && this.actualPage == 0) {
			return true;
		}
		return false;
	}
	
	public void reset() {
		this.limit = 0;
		this.offset = 0;
		this.actualPage = 0;
		this.nbPage = 0;
		this.order = "id";
		this.sort = "Asc";
		this.search = "";
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

	public int getActualPage() {
		return actualPage;
	}

	public void setActualPage(int actualPage) {
		this.actualPage = actualPage;
	}

	public int getNbPage() {
		return nbPage;
	}

	public void setNbPage(int nbPage) {
		this.nbPage = nbPage;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}
}
