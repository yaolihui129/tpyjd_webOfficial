package cn.pacificimmi.about_us.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
import com.jfinal.kit.Prop;
import com.jfinal.kit.PropKit;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;

import cn.pacificimmi.common.PagesBar;
import cn.pacificimmi.common.ParamsParser;
import cn.pacificimmi.common.interceptor.AboutUsInterceptor;
import cn.pacificimmi.common.interceptor.MenuInterCeptor;

/**关于我们-成功案例
 * @author Gorge
 * 2017年5月17日
 */
@Before({MenuInterCeptor.class,AboutUsInterceptor.class})
public class SuccessCasesController extends Controller{
	private static Logger log = LoggerFactory.getLogger(SuccessCasesController.class);
	Prop p = PropKit.use("constants.txt");
	public void index(){
		log.debug("forward to page jobs.jsp");
		CasesParams sp = new CasesParams(this);
		/***
		 * 获取数据列表
		 */
		sp.setPageSize(20);
		Page<Record> page = Db.paginate(sp.getPageNum(), sp.getPageSize(), sp.getSelectStr(), sp.getFromStr());
		List<Record> list = page.getList();

		/***
		 * 保存数据列表
		 */
		this.setAttr("list", list);
		
		/***
		 * 保存翻页
		 */
		String pagesView = PagesBar.getShortPageBar(sp.getPageNum(), page.getTotalPage(), page.getPageSize(), page.getTotalRow(), 5);
		setAttr("pageBar",pagesView);
		
		this.renderJsp("/views/aboutus/Successful-case.jsp");
	}
	
	/**
	 * 公司动态详情
	 */
	public void detail(){
		ParamsParser pp = new ParamsParser(this);
		Record r = Db.findById("crm_information_article", "info_id", pp.getId());
		this.setAttr("article", r);
		this.setAttr("pos_nav_name", "成功案例");
		this.setAttr("pos_nav_url", "/about-us/success-cases");
		Db.update("update crm_information_article set click_count = click_count + 1 where info_id = " + pp.getId());
		
		this.renderJsp("/views/policy/detail.jsp");
	}
	
	/**
	 * 查询参数解析类
	 * @author Gorge
	 */
	private class CasesParams extends ParamsParser{

		public CasesParams(Controller ctr) {
			super(ctr);
			
			this.setSelectStr("select a.*,d.name statusZN ");
			this.setFromStr("from crm_information_article a left join console_dictionary d on a.status = d.dict_code and d.delete_flag = 0");
			
			//查找有效数据
			this.addWhereSegmentByAnd("a.delete_flag='0' and a.status = '"+p.get("publish_status_yfb")+"' and push_location like '%00240004%' and a.section_name like '%成功案例%'");
					
			
			this.setDefaultOrderStr("order by a.sort desc");
		}
	}
}
