package com.excilys.computerdatabase.tag;

import java.io.IOException;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import com.excilys.computerdatabase.helper.Page;

public class PageTag extends SimpleTagSupport {
	
	private Page page;
	private int nbEachPage;
	private int offset;

	public void setPage(Page page) {
		this.page = page;
	}

	public void setNbEachPage(int nbEachPage) {
		this.nbEachPage = nbEachPage;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

	public void doTag() throws IOException {
		  JspWriter out = getJspContext().getOut();
		  
		  out.println("<ul class=\"pagination\">");
		  out.println("<li>");
		  out.println("<a href=\"Dashboard?page=" + page.limit + "&offset=" + (page.offset-1) + "&order=" + page.order + "&search="+ page.search +"&sort=" + page.sort + "\" aria-label=\"Previous\">");
		  out.println("<span aria-hidden=\"true\">&laquo;</span>");
		  out.println("</a>");
		  out.println("</li>");
		  if (page.nbPage > 15) {
			  for (int i=0;i<page.nbPage;i+=5) {
				  out.println("<li><a href=\"Dashboard?page="+page.limit+"&offset="+i+"&order=" + page.order + "&search="+ page.search +"&sort=" + page.sort + "\">" + (i+1) + "</a></li>");
			  }
		  } else {
			  for (int i=0;i<=page.nbPage;i++) {
				  if(page.offset == i) {
					  out.println("<li><a href=\"Dashboard?page="+page.limit+"&offset="+i+"&order=" + page.order + "&search="+ page.search +"&sort=" + page.sort + "\"><b>" + (i+1) + "</b></a></li>");
				  } else {
					  out.println("<li><a href=\"Dashboard?page="+page.limit+"&offset="+i+"&order=" + page.order + "&search="+ page.search +"&sort=" + page.sort + "\">" + (i+1) + "</a></li>");
				  }
			  }
		  }
		  out.println("<li>");
		  out.println("<a href=\"Dashboard?page=" + page.limit + "&offset=" + (page.offset+1) + "&order=" + page.order + "&search="+ page.search +"&sort=" + page.sort + "\" aria-label=\"Next\">");
		  out.println("<span aria-hidden=\"true\">&raquo;</span>");
		  out.println("</a>");
		  out.println("</li>");
		  out.println("</ul>");
		  
	  } 

}
