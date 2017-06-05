package cn.pacificimmi.common.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.pacificimmi.common.interceptor.MenuInterCeptor;
import cn.pacificimmi.common.models.Custinfo;
import cn.pacificimmi.common.models.Live;
import cn.pacificimmi.common.models.Topics;
import cn.pacificimmi.common.utils.WeiXinConst;

import com.alibaba.fastjson.JSONObject;
import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
import com.jfinal.kit.Prop;
import com.jfinal.kit.PropKit;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;

@Before(MenuInterCeptor.class)
public class IndexController extends Controller {
	private static Logger log = LoggerFactory.getLogger(IndexController.class);
	private Prop c = PropKit.use("constants.txt");
	/**
	 * 首页
	 */
	public void index(){
		log.debug("page to index.jsp");
		
		Prop dp = PropKit.use("debug_config.txt");
		
		Custinfo custinfo = this.getSessionAttr(WeiXinConst.CUSTINFO);
		this.setAttr("custinfo", custinfo);
		
		
		//首页轮播图
		List<Topics> bannerImgs = Topics.dao.find("SELECT DISTINCT	t.topic_id,	t.topic_image, t.topic_url FROM 	crm_topics t "+
				"where t.delete_flag=0 and t.topic_status='已发布'	and t.send_position like '%官网-首页%' 	ORDER BY t.topic_sort desc");
		this.setAttr("bannerImgs", bannerImgs);
		
		//移民评估-意向国家
		this.setAttr("intentCountry", intentionCountry());
		//移民评估-移民目的
		this.setAttr("immigPurpose", immigrationPurpose());
		//移民评估-评估人数
		this.setAttr("estimateAccount", estimateAccount());
		
		//成功案例(跑马灯)
		successCaseInfo();
		
		//政策动态标题
		indexPolicyTitles();
		
		//最近活动
		this.setAttr("latestActivities", latestActivities());
		
		//最新政策
		this.setAttr("latestPolicy", latestPolicy());
		
		//最新直播
		this.setAttr("latestLive", latestLive());
		
		//优选国家
		preferredCountry();
		
		//热门项目
		this.setAttr("hotProjects", hotProjects());
		
		//公司大事件
		this.setAttr("bigEvent", bigEvent());
		
		//专业团队
		this.setAttr("professionalTeam", professionalTeam());
		
		
		this.renderJsp(dp.get("TPYJD_OFFICIAL_JSP_BASE_PATH")+"index.jsp");
	}
	
	/**意向国家-评估问卷设置
	 * @return
	 */
	private List<Record> intentionCountry(){
		List<Record> list = null;
		String sql = "select s.content,s.estimate_select_id " +
					" from wx_estimate_question_version qv " +
					" INNER JOIN wx_estimate_question q on qv.version_id = q.version_id and q.delete_flag = 0 " +
					" INNER JOIN wx_estimate_select s on s.estimate_question_id = q.estimate_question_id and s.delete_flag = 0 " +
					" where qv.status = '002300020001' and qv.delete_flag = 0 and q.title like '%意向国家%'"	;
		list = Db.find(sql);
		return list;
	}
	
	/**移民目的-评估问卷设置
	 * @return
	 */
	private List<Record> immigrationPurpose(){
		List<Record> list = null;
		String sql = "select s.content,s.estimate_select_id " +
				" from wx_estimate_question_version qv " +
				" INNER JOIN wx_estimate_question q on qv.version_id = q.version_id and q.delete_flag = 0 " +
				" INNER JOIN wx_estimate_select s on s.estimate_question_id = q.estimate_question_id and s.delete_flag = 0 " +
				" where qv.status = '002300020001' and qv.delete_flag = 0 and q.title like '%移民目的%'"	;
		list = Db.find(sql);
		return list;
	}
	
	/**移民评估-评估人数
	 * @return
	 */
	private int estimateAccount(){
		//已评估人数
		Record r = Db.findFirst("select count(custinfo_id) account from (select custinfo_id from wx_estimate_record group by custinfo_id) t");
		
		//评估基数
		int baseAccount = 0;
		Record r1 = Db.findFirst("select value from official_configs where code = '移民评估人数基数'");
		if(null != r1 && !r1.getStr("value").isEmpty()){
			baseAccount = Integer.parseInt(r1.getStr("value"));
		}
		
		return Integer.parseInt(r.getLong("account").toString()) + baseAccount;
	}
	
	/**
	 * 成功案例
	 */
	private void successCaseInfo(){
		//查询成功案例设置
		int num = 0;
		Record r1 = Db.findFirst("select value from official_configs where code = '成功案例设置'");
		if(null != r1 && !r1.getStr("value").isEmpty()){
			JSONObject j = JSONObject.parseObject(r1.getStr("value"));
			this.setAttr("scSet", j);
			num = Integer.parseInt(j.getString("successful_case_num"));
		}
		
		//成功案例栏目下最新的资讯
		List<Record> rl = Db.find("select cia.* from crm_information_article cia " +
								" INNER JOIN crm_section cs on cia.section_id = cs.section_id and cs.name = '成功案例' and cs.delete_flag = 0 " +
								" where cia.delete_flag = 0 and cia.status = '" + c.get("publish_status_yfb") + "' and cia.push_location like '%00240004%'" +
								" order by cia.publish_time desc limit 0," + num);
		this.setAttr("successCases", rl);
	}
	
	/**
	 * 首页政策动态模板 标题
	 */
	private void indexPolicyTitles(){
		Record r1 = Db.findFirst("select value from official_configs where code = '政策动态'");
		if(r1 != null && !r1.getStr("value").isEmpty()){
			String[] titles = r1.getStr("value").split(",");
			this.setAttr("titles", titles);
		}
	}
	
	/**最近活动
	 * @return
	 */
	private List<Record> latestActivities(){
		List<Record> list = Db.find("select * , case when end_time is null and start_time > now() then 1 " +
									" when end_time is null and start_time < now() then 0 " +
									" when end_time is not null and end_time < now() then 0 " +
									" when end_time is not null and end_time > now() then 1 " +
									" end as join_enable " +
									" from crm_activity where delete_flag = 0 and activity_status = '已发布' and position like '%00240004%' order by create_time desc limit 0,6");
		return list;
	}
	
	/**最新政策
	 * @return
	 */
	private List<Record> latestPolicy(){
		return Db.find("select cia.* from crm_information_article cia " +
						" INNER JOIN crm_section cs on cia.section_id = cs.section_id and cs.name = '移民政策' and cs.delete_flag = 0 " +
						" where cia.delete_flag = 0 and cia.status = '00140002' and cia.push_location like '%00240004%' " +
						" order by sort desc, cia.publish_time desc  LIMIT 0 ,6");
	}
	
	/**最新直播
	 * @return
	 */
	private List<Live> latestLive(){
		return Live.dao.find("select * from portal_live where delete_flag = 0 and live_status in ('直播中','已发布') order by live_start_time asc limit 0 ,2");
	}
	
	
	/**优选国家
	 * @return
	 */
	private void preferredCountry(){
		List<Record> list = Db.find("select * from crm_country where delete_flag = 0 and preferred = 1 order by sort desc");
		int i = 1;
		for(Record r : list){
			r.set("project_type", Db.find("select DISTINCT d.dict_code,d.name from crm_project p LEFT JOIN console_dictionary d " +
											" on p.project_type = d.dict_code and p.delete_flag = 0 and d.delete_flag = 0 " +
											" where p.country = '" + r.getStr("country_id") + "' and p.project_status = '00070001'"));
			this.setAttr("preferredCountry"+i, r);
			i++;
		}
	}
	
	/**热门项目
	 * @return
	 */
	private List<Record> hotProjects(){
		List<Record> list = Db.find("select * from crm_project where delete_flag = 0 and preferred = 1 order by hot_sort desc");
		for(Record r : list){
			r.set("projs", (r.getStr("advantage") == null || r.getStr("advantage").isEmpty()) ? null : r.getStr("advantage").split(";"));
		}
		return list;
	}
	
	/**公司大事件
	 * @return
	 */
	private List<Record> bigEvent(){
		List<Record> screenList = Db.find("select DISTINCT screen from official_memorabilia where publish_status = '已发布' ORDER BY screen asc");
		for(Record r : screenList){
			List<Record> imgList = Db.find("select position,picture_url,`describe` " +
					" from official_memorabilia where publish_status = '已发布' and screen='"+r.getInt("screen")+"' order by position asc");
			int i = 1;
			for(Record img : imgList){
				r.set("img"+i, img);
				i++;
			}
		}
		return screenList;
	}
	
	
	/**
	 * 专业团队
	 */
	private List<Record> professionalTeam(){
		List<Record> list = Db.find("select * from console_dictionary where dict_pcode = '0033' and delete_flag = 0 order by sort asc");
		for(Record r : list){
			List<Record> pts = Db.find("select u.*,cpt.team_id from crm_professional_team cpt " +
										" LEFT JOIN console_user u on cpt.user_id = u.user_id and u.delete_flag = 0 " +
										" where cpt.team_type = '"+r.getStr("name")+"' and cpt.delete_flag = 0 and cpt.release_mark = '已发布' and cpt.index_show = 1 ");
			r.set("teamList", pts);
		}
		return list;
	}
	
	/**
	 * 退出登录
	 */
	public void logout(){
		this.removeSessionAttr(WeiXinConst.CUSTINFO);
		this.redirect("/");
	}
	
}
