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

/**关于我们-诚聘英才
 * @author Gorge
 * 2017年5月17日
 */
@Before({MenuInterCeptor.class,AboutUsInterceptor.class})
public class JobController extends Controller{
	private static Logger log = LoggerFactory.getLogger(JobController.class);
	Prop p = PropKit.use("constants.txt");
	public void index(){
		log.debug("forward to page jobs.jsp");
		JobParams sp = new JobParams(this);
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
		
		//查询公司
		List<Record> coms = Db.find("select * from console_dictionary where delete_flag = 0 and dict_pcode = '"+p.get("dict_company")+"' order by sort asc");
		this.setAttr("coms", coms);
		
		//查询已发布职位的城市
		List<Record> cityList = Db.find("select DISTINCT d.* from official_recruitment r " +
										" left join console_dictionary d on r.city = d.dict_code and d.delete_flag = 0" +
										" where r.delete_flag = 0 and r.city is not null");
		this.setAttr("cityList", cityList);
		
		/***
		 * 保存翻页
		 */
		String pagesView = PagesBar.getShortPageBar(sp.getPageNum(), page.getTotalPage(), page.getPageSize(), page.getTotalRow(), 5);
		setAttr("pageBar",pagesView);
		
		this.renderJsp("/views/aboutus/jobs.jsp");
	}
	
	/**
	 * 招聘岗位详情
	 */
	public void detail(){
		ParamsParser pp = new ParamsParser(this);
		Record job = Db.findFirst("select r.*,d1.name comName,d2.name workExp,d3.name edu,d4.name sheng,d5.name shi" +
								" from official_recruitment r" +
								" LEFT JOIN console_dictionary  d1 on r.com_id = d1.dict_code and d1.delete_flag = 0" +
								" LEFT JOIN console_dictionary  d2 on r.work_exp = d2.dict_code and d2.delete_flag = 0" +
								" LEFT JOIN console_dictionary  d3 on r.education = d3.dict_code and d3.delete_flag = 0" +
								" LEFT JOIN console_dictionary  d4 on r.province = d4.dict_code and d4.delete_flag = 0" +
								" LEFT JOIN console_dictionary  d5 on r.city = d5.dict_code and d5.delete_flag = 0" +
								" where r.delete_flag = 0 and r.or_id = " + pp.getId());
		//任职要求
		if(null != job.getStr("demand") && !job.getStr("demand").isEmpty()){
			String[] s = job.getStr("demand").split("\n");
			StringBuffer demand = new StringBuffer();
			demand.append("<ul>");
			for(int i=0;i<s.length;i++){
				demand.append("<li>").append(s[i]).append("</li>");
			}
			demand.append("</ul>");
			job.set("demand", demand.toString());
		}
		
		//职位描述
		if(null != job.getStr("description") && !job.getStr("description").isEmpty()){
			String[] s = job.getStr("description").split("\n");
			StringBuffer description = new StringBuffer();
			description.append("<ul>");
			for(int i=0;i<s.length;i++){
				description.append("<li>").append(s[i]).append("</li>");
			}
			description.append("</ul>");
			job.set("description", description.toString());
		}
		
		this.setAttr("job", job);
		
		Record jobSet = Db.findFirst("select value from official_configs where code = '诚聘英才设置' ");
		if(jobSet != null && !jobSet.getStr("value").isEmpty()){
			String[] s = jobSet.getStr("value").split("\n");
			StringBuffer description = new StringBuffer();
			description.append("<ul>");
			for(int i=0;i<s.length;i++){
				description.append("<li>").append(s[i]).append("</li>");
			}
			description.append("</ul>");
			job.set("job_set", description.toString());
		}
		this.renderJsp("/views/aboutus/job_detail.jsp");
	}
	/**
	 * 查询参数解析类
	 * @author Gorge
	 */
	private class JobParams extends ParamsParser{

		public JobParams(Controller ctr) {
			super(ctr);
			
			this.setSelectStr("select r.*,d1.name comName,d2.name workExp,d3.name edu,d4.name sheng,d5.name shi ");
			this.setFromStr("from official_recruitment r " +
							" LEFT JOIN console_dictionary  d1 on r.com_id = d1.dict_code and d1.delete_flag = 0" +
							" LEFT JOIN console_dictionary  d2 on r.work_exp = d2.dict_code and d2.delete_flag = 0" +
							" LEFT JOIN console_dictionary  d3 on r.education = d3.dict_code and d3.delete_flag = 0" +
							" LEFT JOIN console_dictionary  d4 on r.province = d4.dict_code and d4.delete_flag = 0" + 
							" LEFT JOIN console_dictionary  d5 on r.city = d5.dict_code and d5.delete_flag = 0");
			
			//查找有效数据
			this.addWhereSegmentByAnd("r.delete_flag= 0");
					
			//关键字
			String keys = this.getNormStr("keys");
			if(keys!=null && !keys.trim().isEmpty()){
				this.addWhereSegmentByAnd("r.position like '%"+keys.trim()+"%'");
				this.ctr.setAttr("keys", keys);
			}
			
			//所属公司
			String company = this.getAllStr("company");
			if(company != null && !company.isEmpty()){
				this.addWhereSegmentByAnd("r.com_id = '"+company+"'");
				this.ctr.setAttr("company", company);
			}
			
			//工作地点(市)
			String city = this.getAllStr("city");
			if(city != null && !city.isEmpty()){
				this.addWhereSegmentByAnd("r.city = '"+city+"'");
				this.ctr.setAttr("city", city);
			}
			
			this.setDefaultOrderStr("order by r.sort desc");
		}
	}
}
