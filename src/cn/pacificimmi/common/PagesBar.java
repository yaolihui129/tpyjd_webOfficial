package cn.pacificimmi.common;

public class PagesBar {
	public static String getShortPageBar(int currentPage,int totlePage,int pageSize,int rows,int pages){
		int start=0,end=0;
		if(rows>0){
			start = (currentPage-1)*pageSize +1;
			end = currentPage*pageSize;
			if(end>rows)
				end = rows;
		}
		
		StringBuffer pageBar = new StringBuffer();
		pageBar.append("<div class=\"country-new-page container\">")
		.append("<span class=\"pr-20\">共").append(rows).append("条</span>");
		
		if(currentPage==1){
			//pageBar.append("<a class=\"page-pre\" disabled href=\"#\"><<</a>");
			pageBar.append("<a class=\"page-pre\" disabled href=\"#\"><</a>");
		}
		else{
			//pageBar.append("<a class=\"page-pre\" data-page=\"1\" href=\"#\"><<</a>");
			pageBar.append("<a class=\"page-pre\" data-page=\""+(currentPage-1)+"\" href=\"#\"><</a>");
		}
		
		//只显示pages个页码
		if(pages>=totlePage){
			for(int i=1;i<=totlePage;i++){
				if(i==currentPage)
					pageBar.append("<a class=\"pagebar page-current\">"+i+"</a>");
				else
					pageBar.append("<a data-page=\""+i+"\">"+i+"</a>");
			}
		}
		else{
			int half = pages/2;
			//显示省略号
			if(currentPage-half<=1){
				for(int i=1;i<=pages;i++){
					if(i==currentPage)
						pageBar.append("<a class=\"pagebar page-current\" >"+i+"</a>");
					else
						pageBar.append("<a  data-page=\""+i+"\">"+i+"</a>");
				}
				pageBar.append("<a data-page=\""+(pages+1)+"\"><a>...</a>");
			}
			else if(currentPage+half>=totlePage){
				pageBar.append("<a data-page=\""+(totlePage-pages)+"\"><a>...</a>");
				for(int i=(totlePage-pages+1);i<=totlePage;i++){
					if(i==currentPage)
						pageBar.append("<a class=\"pagebar page-current\">"+i+"</a>");
					else
						pageBar.append("<a  data-page=\""+i+"\"><a>"+i+"</a>");
				}
			}
			else{
				pageBar.append("<a data-page=\""+(currentPage-half-1)+"\"><a>...</a>");
				for(int i=currentPage-half;i<=currentPage+half;i++){
					if(i==currentPage)
						pageBar.append("<a class=\"pagebar page-current\">"+i+"</a>");
					else
						pageBar.append("<a data-page=\""+i+"\"><a>"+i+"</a>");
				}
				pageBar.append("<a data-page=\""+(currentPage+half+1)+"\"><a>...</a>");
			}
			
		}
		
		if(currentPage==totlePage){
			pageBar.append("<a class=\"page-next\" disabled href=\"#\">></a>");
			//pageBar.append("<a class=\"page-next\" disabled href=\"#\">>></a>");
		}else{
			pageBar.append("<a class=\"page-next\" data-page=\""+(currentPage+1)+"\" href=\"#\">></a>");
			//pageBar.append("<a class=\"page-next\" data-page=\""+totlePage+"\" href=\"#\">>></a>");
		}
		
		
		pageBar.append("</div>");
		
		return pageBar.toString();
	}
}
