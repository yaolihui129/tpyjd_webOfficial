package cn.pacificimmi.policy.controller;

import java.util.List;

import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
import com.jfinal.kit.Prop;
import com.jfinal.kit.PropKit;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;

import cn.pacificimmi.common.PagesBar;
import cn.pacificimmi.common.ParamsParser;
import cn.pacificimmi.common.interceptor.MenuInterCeptor;
import cn.pacificimmi.common.models.Topics;

/**移民新政
 * @author Gorge
 * 2017年5月17日
 */
@Before(MenuInterCeptor.class)
public class PolicyController extends Controller{
	Prop p = PropKit.use("constants.txt");
	public void index(){
		PolicyParams sp = new PolicyParams(this);
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
		
		List<Topics> bannerImgs = Topics.dao.find("SELECT DISTINCT	t.topic_id,	t.topic_image, t.topic_url FROM 	crm_topics t "+
				"where t.delete_flag=0 and t.topic_status='已发布'	and t.send_position like '%官网-移民政策%' 	ORDER BY t.topic_sort desc");
		this.setAttr("bannerImgs", bannerImgs);
		
		this.renderJsp("/views/policy/index.jsp");
	}
	
	/**
	 * 移民新政搜索
	 */
	public void search(){
		PolicySearchParams sp = new PolicySearchParams(this);
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
		
		List<Topics> bannerImgs = Topics.dao.find("SELECT DISTINCT	t.topic_id,	t.topic_image, t.topic_url FROM 	crm_topics t "+
				"where t.delete_flag=0 and t.topic_status='已发布'	and t.send_position like '%官网-移民政策%' 	ORDER BY t.topic_sort desc");
		this.setAttr("bannerImgs", bannerImgs);
		
		this.renderJsp("/views/policy/search_result.jsp");
	}
	
	/**
	 * 政策详情
	 */
	public void detail(){
		ParamsParser pp = new ParamsParser(this);
		Record r = Db.findById("crm_information_article", "info_id", pp.getId());
		this.setAttr("article", r);
		this.setAttr("pos_nav_name", "移民政策");
		this.setAttr("pos_nav_url", "/policy");
		
		Db.update("update crm_information_article set click_count = click_count + 1 where info_id = " + pp.getId());
		
		List<Topics> bannerImgs = Topics.dao.find("SELECT DISTINCT	t.topic_id,	t.topic_image, t.topic_url FROM 	crm_topics t "+
				"where t.delete_flag=0 and t.topic_status='已发布'	and t.send_position like '%官网-移民政策%' 	ORDER BY t.topic_sort desc");
		this.setAttr("bannerImgs", bannerImgs);
		
		this.renderJsp("/views/policy/detail.jsp");
	}

	/**
	 * 查询参数解析类
	 * @author Gorge
	 */
	private class PolicyParams extends ParamsParser{

		public PolicyParams(Controller ctr) {
			super(ctr);
			
			this.setSelectStr("select a.*,d.name statusZN ");
			this.setFromStr("from crm_information_article a left join console_dictionary d on a.status = d.dict_code and d.delete_flag = 0");
			
			//查找有效数据
			this.addWhereSegmentByAnd("a.delete_flag='0' and a.status = '"+ p.get("publish_status_yfb") +"' and push_location like '%00240004%' and a.section_name like '%移民政策%'");
					
			//查询名称
			String name = this.getNormStr("name");
			if(name!=null){
				this.addWhereSegmentByAnd("a.name like '%"+name+"%'");
			}
			
			this.setDefaultOrderStr("order by a.sort desc , a.publish_time desc");
		}
	}
	
	/**
	 * 查询参数解析类
	 * @author Gorge
	 */
	private class PolicySearchParams extends ParamsParser{

		public PolicySearchParams(Controller ctr) {
			super(ctr);
			
			this.setSelectStr("select a.*,d.name statusZN ");
			this.setFromStr("from crm_information_article a left join console_dictionary d on a.status = d.dict_code and d.delete_flag = 0");
			
			//查找有效数据
			this.addWhereSegmentByAnd("a.delete_flag='0' and a.status = '"+ p.get("publish_status_yfb") +"' and push_location like '%00240004%' and (a.section_name like '%移民政策%' or a.section_name like '%成功案例%')");
					
			//查询名称
			String keys = this.getAllStr("keys");
			String sk = this.getAllStr("search_key");
			
			if(sk != null && !sk.isEmpty()){
				keys = sk;
			}
			
			if(keys!=null){
				this.addWhereSegmentByAnd("a.title like '%"+keys+"%'");
				this.ctr.setAttr("keys", keys);
			}
			
			this.setDefaultOrderStr("order by a.sort desc , a.publish_time desc");
		}
	}
}
