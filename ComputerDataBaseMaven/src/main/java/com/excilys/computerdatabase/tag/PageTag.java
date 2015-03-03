package com.excilys.computerdatabase.tag;

import java.io.IOException;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import com.excilys.computerdatabase.helper.Page;

public class PageTag extends SimpleTagSupport {
	
	private int page;
	private int nbEachPage;
	private int offset;

	public void setPage(int page) {
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
		  
//		  out.println("<ul class=\"pagination\">");
//		  out.println("<li>");
//		  out.println("<a href=\"Dashboard?page=" + nbEachPage + "&offset=" + (offset-1) + "\" aria-label=\"Previous\">");
//		  out.println("<span aria-hidden=\"true\">&laquo;</span>");
//		  out.println("</a>");
//		  out.println("</li>");
//		  if (page > 15) {
//			  for (int i=0;i<page;i+=5) {
//				  out.println("<li><a href=\"Dashboard?page="+nbEachPage+"&offset="+i+"\">" + (i+1) + "</a></li>");
//			  }
//		  } else {
//			  for (int i=0;i<=page;i++) {
//				  if(offset == i) {
//					  out.println("<li><a href=\"Dashboard?page="+nbEachPage+"&offset="+i+"\"><b>" + (i+1) + "</b></a></li>");
//				  } else {
//					  out.println("<li><a href=\"Dashboard?page="+nbEachPage+"&offset="+i+"\">" + (i+1) + "</a></li>");
//				  }
//			  }
//		  }
//		  out.println("<li>");
//		  out.println("<a href=\"Dashboard?page=" + nbEachPage + "&offset=" + (offset+1) + "\" aria-label=\"Next\">");
//		  out.println("<span aria-hidden=\"true\">&raquo;</span>");
//		  out.println("</a>");
//		  out.println("</li>");
//		  out.println("</ul>");
		  out.println("<ul class=\"pagination\">");
		  out.println("<li>");
		  out.println("<a href=\"Dashboard?page=" + Page.INSTANCE.limit + "&offset=" + (Page.INSTANCE.offset-1) + "\" aria-label=\"Previous\">");
		  out.println("<span aria-hidden=\"true\">&laquo;</span>");
		  out.println("</a>");
		  out.println("</li>");
		  if (Page.INSTANCE.nbPage > 15) {
			  for (int i=0;i<Page.INSTANCE.nbPage;i+=5) {
				  out.println("<li><a href=\"Dashboard?page="+Page.INSTANCE.limit+"&offset="+i+"\">" + (i+1) + "</a></li>");
			  }
		  } else {
			  for (int i=0;i<=Page.INSTANCE.nbPage;i++) {
				  if(Page.INSTANCE.offset == i) {
					  out.println("<li><a href=\"Dashboard?page="+Page.INSTANCE.limit+"&offset="+i+"\"><b>" + (i+1) + "</b></a></li>");
				  } else {
					  out.println("<li><a href=\"Dashboard?page="+Page.INSTANCE.limit+"&offset="+i+"\">" + (i+1) + "</a></li>");
				  }
			  }
		  }
		  out.println("<li>");
		  out.println("<a href=\"Dashboard?page=" + Page.INSTANCE.limit + "&offset=" + (Page.INSTANCE.offset+1) + "\" aria-label=\"Next\">");
		  out.println("<span aria-hidden=\"true\">&raquo;</span>");
		  out.println("</a>");
		  out.println("</li>");
		  out.println("</ul>");
		  
	  } 

}
