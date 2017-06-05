package cn.pacificimmi.activity.controller;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
import com.jfinal.kit.PropKit;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;

import cn.pacificimmi.common.ParamsParser;
import cn.pacificimmi.common.controllers.CustinfoController;
import cn.pacificimmi.common.interceptor.MenuInterCeptor;
import cn.pacificimmi.common.models.Activity;
import cn.pacificimmi.common.models.ActivitySign;
import cn.pacificimmi.common.models.Custinfo;
import cn.pacificimmi.common.models.Topics;

/**活动
 * @author Gorge
 * 2017年5月17日
 */
@Before(MenuInterCeptor.class)
public class ActivityController extends Controller{
	private static Logger log = LoggerFactory.getLogger(ActivityController.class);
	
	public void index(){
		//查询即将开始的活动
		List<Activity> comingList = Activity.dao.find("select * from crm_activity where delete_flag = 0 and position like '%00240004%'" +
												" and activity_status = '已发布' and ((end_time is null and NOW() < start_time) or (end_time is not null and NOW() < end_time)) order by start_time desc");
		this.setAttr("comingActivity", comingList);
		
		//已结束的活动
		List<Record> endList = Db.find("select DISTINCT DATE_FORMAT(start_time,'%Y') year from crm_activity where delete_flag = 0 and position like '%00240004%'" +
										" and activity_status = '已发布' " +
										" and ( (end_time is not null and end_time < NOW()) or (end_time is null and TIMESTAMPDIFF(DAY,start_time,NOW()) >= 1 ))" + 
										" order by start_time desc");
		
		for(Record r : endList){
			//年份
			List<Record> monthList = Db.find("select DISTINCT DATE_FORMAT(start_time,'%m') month from crm_activity where delete_flag = 0 and position like '%00240004%'" +
											" and activity_status = '已发布' " +
											" and ((end_time is not null and end_time < NOW()) or (end_time is null and TIMESTAMPDIFF(DAY,start_time,NOW()) >= 1 ))" +
											" and DATE_FORMAT(start_time,'%Y') = '"+ r.getStr("year") +"' ORDER BY start_time desc");
			//年份下月份
			for(Record m : monthList){
				//年月下活动列表
				List<Record> list = Db.find("select * from crm_activity where delete_flag = 0 and position like '%00240004%'" +
						" and activity_status = '已发布' " +
						" and ((end_time is not null and end_time < NOW()) or (end_time is null and TIMESTAMPDIFF(DAY,start_time,NOW()) >= 1 ))" +
						" and DATE_FORMAT(start_time,'%Y%m') = '"+ r.getStr("year")+m.getStr("month") +"' ORDER BY start_time desc");
				m.set("activities", list);
				m.set("month", Integer.parseInt(m.getStr("month")));
			}
			r.set("months", monthList);
		}
		this.setAttr("endList", endList);
		
		this.renderJsp("/views/activity/index.jsp");
	}
	
	/**
	 * 详情
	 */
	public void detail(){
		ParamsParser pp = new ParamsParser(this);
		Record r = Db.findById("crm_activity", "activity_id", pp.getId());
		this.setAttr("article", r);
		this.setAttr("pos_nav_name", "活动列表");
		this.setAttr("pos_nav_url", "/activity");
		
		List<Topics> bannerImgs = Topics.dao.find("SELECT DISTINCT	t.topic_id,	t.topic_image, t.topic_url FROM 	crm_topics t "+
				"where t.delete_flag=0 and t.topic_status='已发布'	and t.send_position like '%官网-活动列表%' 	ORDER BY t.topic_sort desc");
		this.setAttr("bannerImgs", bannerImgs);

		
		this.renderJsp("/views/activity/detail.jsp");
	}

	/**
	 * 活动报名
	 */
	public void join(){
		ParamsParser pp = new ParamsParser(this);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("code", 0);
		map.put("msg", "报名成功");
		try{
			String name = pp.getAllStr("uname");
			String phone = pp.getAllStr("phone");
			String activityId = pp.getId();
			
			if(!isActivityEnd(activityId)){
				//判断是否已经报名
				List<Record> rlist = Db.find("select sign_id from crm_activity_sign where activity_id = '"+activityId+"' and sign_mobile = '" + phone + "'");
				if(rlist != null && rlist.size() > 0){
					map.put("status", 100);
					map.put("msg", "对不起!您已报名该活动.");
				}else{
					//获取登录用户信息
					Custinfo custinfo = this.getSessionAttr("Custinfo");
					
					ActivitySign entity = new ActivitySign();
					entity.setSignMobile(phone);
					entity.setSignName(name);
					entity.setSignNum(1);
					entity.setActivityId(Integer.parseInt(activityId));
					if(custinfo != null){
						entity.setSignUser(custinfo.getPhoneNum());
					}
					
					entity.save();
					
					//统计活动报名人数
					Record r = Db.findFirst("select IFNULL(SUM(sign_num),0) totalNum from crm_activity_sign where activity_id = " + activityId);
					BigDecimal bd = r.getBigDecimal("totalNum");
					
					Activity activity = Activity.dao.findById(activityId);
					if(activity != null){
						activity.setJoinUsers(bd.intValue());
						activity.update();
					}
					
					//数据入线索池
					Custinfo cust = new Custinfo();
					cust.setName(name);
					cust.setPhoneNum(phone);
					cust.setDeleteFlag(0);
					cust.setCustStatus(PropKit.use("constants.txt").get("cust_status_tofollow"));//线索状态-待跟进
					cust.setCustSource(PropKit.use("constants.txt").get("cust_source_hdbm"));//来源-活动报名
					cust.setCreateTime(new Date());
					cust.setCustType(PropKit.use("constants.txt").get("cust_type_qz"));//客户类型-潜在客户
					cust.setDirection(0);//线索池
					CustinfoController.addCust(cust);
						
					map.put("status", 0);
					map.put("msg", "恭喜您!报名成功.");
				}
			}else{
				map.put("status", 100);
				map.put("msg", "对不起!该活动已结束,不能报名");
			}
			
			/*List<Record> list = Db.find("select * from crm_activity_sign where sign_mobile = '"+phone+"' and activity_id = '"+activityId+"'");
			if(list != null && list.size() >= 1){
				map.put("code", 1);
				map.put("msg", "您已成功报名");
			}else{
				Custinfo custinfo = getSessionAttr(WeiXinConst.CUSTINFO);
				ActivitySign sign = new ActivitySign();
				sign.setActivityId(Integer.parseInt(activityId));
				sign.setSignMobile(phone);
				sign.setSignName(name);
				sign.setSignNum(1);
				sign.setSignTime(new Date());
				sign.setSignUser((custinfo != null && !custinfo.getPhoneNum().isEmpty())?custinfo.getPhoneNum():null);
				sign.save();
			}*/
		}catch(Exception e){
			log.error(e.getMessage(),e);
			map.put("code", 1);
			map.put("msg", "报名失败");
		}
		this.renderJson(map);
	}
	
	
	/**判断活动是否可以报名
	 * @param activityId
	 * @return
	 */
	private boolean isActivityEnd(String activityId){
		boolean endFlag = true;
		List<Record> list = Db.find("select activity_id from crm_activity where delete_flag = 0 and activity_status = '已发布' and activity_id = " + activityId +
									" and start_time  BETWEEN NOW() and '2100-01-01 01:00:00'");
		if(list != null && list.size() > 0){
			endFlag = false;
		}
		return endFlag;
	}
}
