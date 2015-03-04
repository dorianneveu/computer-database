package com.excilys.computerdatabase.helper;

public class Page {
//	INSTANCE;

	public int limit = 0;
	public int offset = 0;
	public int actualPage = 0;
	public int nbPage = 0;
	public String order = "id";
	public String sort = "ASC";
	public String search = "";
	
	public boolean isEmpty() {
//		if (INSTANCE.limit == 0 && INSTANCE.offset == 0 && INSTANCE.actualPage == 0) {
		if (this.limit == 0 && this.offset == 0 && this.actualPage == 0) {
			return true;
		}
		return false;
	}
	
//	public boolean isSearch() {
//		if (this.search.equals("")) {
//			return false;
//		}
//		return true;
//	}
	
	public void reset() {
//		Page.INSTANCE.limit = 0;
//		Page.INSTANCE.offset = 0;
//		Page.INSTANCE.actualPage = 0;
//		Page.INSTANCE.nbPage = 0;
//		Page.INSTANCE.order = "id";
//		Page.INSTANCE.sort = "Asc";
//		Page.INSTANCE.search = "";
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
