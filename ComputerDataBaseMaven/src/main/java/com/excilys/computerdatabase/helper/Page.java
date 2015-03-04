package com.excilys.computerdatabase.helper;

public enum Page {
	INSTANCE;

	public int limit = 0;
	public int offset = 0;
	public int actualPage = 0;
	public int nbPage = 0;
	public String order = "id";
	public String sort = "Asc";
	public String search = "";
	
	public static boolean isEmpty() {
		if (INSTANCE.limit == 0 && INSTANCE.offset == 0 && INSTANCE.actualPage == 0) {
			return true;
		}
		return false;
	}
	
	public static boolean isSearch() {
		if (INSTANCE.search.equals("")) {
			return false;
		}
		return true;
	}
	
	public static void reset() {
		Page.INSTANCE.limit = 0;
		Page.INSTANCE.offset = 0;
		Page.INSTANCE.actualPage = 0;
		Page.INSTANCE.nbPage = 0;
		Page.INSTANCE.order = "id";
		Page.INSTANCE.sort = "Asc";
		Page.INSTANCE.search = "";
	}
}
