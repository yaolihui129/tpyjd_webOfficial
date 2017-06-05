package cn.pacificimmi.live.controllers;

import java.net.URLDecoder;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jfinal.aop.Before;
import com.jfinal.aop.Clear;
import com.jfinal.core.Controller;
import com.jfinal.kit.Prop;
import com.jfinal.kit.PropKit;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;

import cn.pacificimmi.common.ParamsParser;
import cn.pacificimmi.common.interceptor.AjaxLoginInterceptor;
import cn.pacificimmi.common.models.Channel;
import cn.pacificimmi.common.models.Custinfo;
import cn.pacificimmi.common.models.InvitationCode;
import cn.pacificimmi.common.models.Live;
import cn.pacificimmi.common.models.LiveApplication;
import cn.pacificimmi.common.models.LiveLike;
import cn.pacificimmi.common.models.LiveMessage;
import cn.pacificimmi.common.utils.ConstantUtil;
import cn.pacificimmi.common.utils.TpyDateUtil;
import cn.pacificimmi.common.utils.WeiXinConst;
/**直播控制器
*@author Gorge
*2017年3月16日
*/
@Before (AjaxLoginInterceptor.class)
public class LiveController extends Controller {
	private static Logger log = LoggerFactory.getLogger(LiveController.class);
	private static final Prop p = PropKit.use("constants.txt");
	
	@Clear
	public void index(){
		this.setAttr("menu_on", "live");//当前选中菜单
		
		Custinfo custinfo = getSessionAttr(WeiXinConst.CUSTINFO);
		this.setAttr("custinfo", custinfo);
		this.setAttr("message_refresh_rate", p.get("message_refresh_rate"));
		ParamsParser pp = new ParamsParser(this);
		//查询直播信息
		List<Record> liveList = Db.find("select * from portal_live where delete_flag = 0 and live_status in ('直播中','已发布') order by live_start_time asc ");
		int i=0;
		for(Record l : liveList){
			Record r = Db.findFirst("select u.* from console_user u,crm_steward_user su where su.delete_flag = 0 and u.user_id = su.user_id and su.steward_id="+l.get("live_steward_id"));
			l.set("presenter", r.get("english_name"));
			l.set("headimg", r.get("head_img"));
			
			if(pp.getId() != null){
				if(pp.getId().equals(l.get("live_id").toString())){
					this.setAttr("live", l);
					reachLimit(l.get("live_id").toString(),l.get("live_limit").toString());//报名已满 true-已满;false-未满;
					if(null != custinfo && custinfo.getCustinfoId() != null){
						checkUserStatus(l.get("live_id").toString());
						getUserLike(l.get("live_id").toString(),custinfo.getCustinfoId());
					}else{
						this.setAttr("apply", false);
						if("私密".equals(l.getStr("live_type"))){
							if(TpyDateUtil.dateToStr(l.getDate("signup_start_time")).compareTo(TpyDateUtil.dateToStr(new Date())) > 0){
								this.setAttr("start", "-1");//未开始
							}else if(TpyDateUtil.dateToStr(l.getDate("signup_end_time")).compareTo(TpyDateUtil.dateToStr(new Date())) < 0){
								this.setAttr("start", "1");//一结束
							}else{
								this.setAttr("start", "0");//可以报名
							}
						}
						
					}
					Channel ch = Channel.dao.findById(l.get("channel_id"));
					this.setAttr("channel", ch);
				}
			}else{
				if(i==0){
					this.setAttr("live", l);
					reachLimit(l.get("live_id").toString(),l.get("live_limit").toString());//报名已满 true-已满;false-未满;
					if(null != custinfo && custinfo.getCustinfoId() != null){
						checkUserStatus(l.get("live_id").toString());
						getUserLike(l.get("live_id").toString(),custinfo.getCustinfoId());
					}else{
						this.setAttr("apply", false);
						if("私密".equals(l.getStr("live_type"))){
							if(TpyDateUtil.dateToStr(l.getDate("signup_start_time")).compareTo(TpyDateUtil.dateToStr(new Date())) > 0){
								this.setAttr("start", "-1");//未开始
							}else if(TpyDateUtil.dateToStr(l.getDate("signup_end_time")).compareTo(TpyDateUtil.dateToStr(new Date())) < 0){
								this.setAttr("start", "1");//一结束
							}else{
								this.setAttr("start", "0");//可以报名
							}
						}
					}
					Channel ch = Channel.dao.findById(l.get("channel_id"));
					this.setAttr("channel", ch);
				}
			}
			
			if(i==0){
				this.setAttr("liveDate", TpyDateUtil.dateToStrMmdd(l.getDate("live_start_time"),false,null)+"开播");
			}
			i++;
		}
		this.setAttr("liveList", liveList);
		
		this.renderJsp("/views/live/live_index.jsp");
	}
	/**
	 * 查看用户对直播的点赞情况
	 */
	public void getUserLike(String liveId,int uid){
		LiveLike ll = LiveLike.dao.findFirst("select * from portal_live_like where live_id="+liveId+" and custinfo_id="+uid);
		this.setAttr("likeInfo", ll);
	}
	
	/**当前用户参与当前直播的状态信息
	 * @param liveId
	 */
	private void checkUserStatus(String liveId){
		Live live = Live.dao.findById(liveId);
		Custinfo cust = getSessionAttr(WeiXinConst.CUSTINFO);
		int uid = cust.getCustinfoId();
		this.setAttr("application_method_passed", p.get("application_method_passed"));//通过
		this.setAttr("application_method_disallowance", p.get("application_method_disallowance"));//驳回
		this.setAttr("application_method_cancel", p.get("application_method_cancel"));//取消资格

		if("私密".equals(live.getLiveType())){
			if(TpyDateUtil.dateToStr(live.getSignupStartTime()).compareTo(TpyDateUtil.dateToStr(new Date())) > 0){
				this.setAttr("start", "-1");//未开始
			}else if(TpyDateUtil.dateToStr(live.getSignupEndTime()).compareTo(TpyDateUtil.dateToStr(new Date())) < 0){
				this.setAttr("start", "1");//一结束
			}else{
				this.setAttr("start", "0");//可以报名
			}
		}
		
		if(live.getLiveType().equals(p.get("live_secret_name"))){//私密直播
			LiveApplication la = LiveApplication.dao.findFirst(
					"select * from portal_live_application where live_id="+liveId + " and custinfo_id="+uid);
			if(null != la){
				if("直播中".equals(live.getLiveStatus())){
					if(la.getArticipateFlag() == 1){//已认证
						if(la.getStatus().equals(p.get("application_method_passed"))){
							this.setAttr("verified", true);//认证通过
						}else{
							this.setAttr("verified", false);//认证失败
							if(la.getStatus().equals(p.get("application_method_disallowance"))){
								this.setAttr("apply_status", "申请未成功");//已申请-驳回
							}else if(la.getStatus().equals(p.get("application_method_cancel"))){
								this.setAttr("apply_status", "取消参与资格");//已申请-取消资格
							}
						}
					}else{//需要认证
						if(la.getApplicationMethod().equals(p.get("live_participate_method_code"))){
							if(la.getStatus().equals(p.get("application_method_passed"))){
								this.setAttr("apply_status", "已申请邀请码");//已申请-通过
								this.setAttr("hadApplyCode", true);
							}else if(la.getStatus().equals(p.get("application_method_disallowance"))){
								this.setAttr("apply_status", "申请未成功");//已申请-驳回
							}else if(la.getStatus().equals(p.get("application_method_cancel"))){
								this.setAttr("apply_status", "取消参与资格");//已申请-取消资格
							}
						}else if(la.getApplicationMethod().equals(p.get("live_participate_method_application"))){
							if(la.getStatus().equals(p.get("application_method_passed"))){
								this.setAttr("verified", true);//认证通过
							}else if(la.getStatus().equals(p.get("application_method_disallowance"))){
								this.setAttr("apply_status", "报名未成功");//已报名-驳回
							}else if(la.getStatus().equals(p.get("application_method_cancel"))){
								this.setAttr("apply_status", "取消参与资格");//已报名-取消资格
							}
						}
					}
				}else{
					this.setAttr("apply", true);
					if(la.getApplicationMethod().equals(p.get("live_participate_method_code"))){
						if(la.getStatus().equals(p.get("application_method_passed"))){
							this.setAttr("apply_status", "已申请邀请码");//已申请-通过
						}else if(la.getStatus().equals(p.get("application_method_disallowance"))){
							this.setAttr("apply_status", "申请未成功");//已申请-驳回
							this.setAttr("can_temp", true);
						}else if(la.getStatus().equals(p.get("application_method_cancel"))){
							this.setAttr("apply_status", "您被禁止进入此直播间");//已申请-取消资格
						}
					}else if(la.getApplicationMethod().equals(p.get("live_participate_method_application"))){
						if(la.getStatus().equals(p.get("application_method_passed"))){
							this.setAttr("apply_status", "已报名");//已报名-通过
						}else if(la.getStatus().equals(p.get("application_method_disallowance"))){
							this.setAttr("apply_status", "报名未成功");//已报名-驳回
							this.setAttr("can_temp", true);
						}else if(la.getStatus().equals(p.get("application_method_cancel"))){
							this.setAttr("apply_status", "您被禁止进入此直播间");//已报名-取消资格
						}
					}
				}
			}else{
				if("直播中".equals(live.getLiveStatus())){
					this.setAttr("apply_status", "您未报名本次直播");
				}else{
					this.setAttr("apply", false);
				}
				
			}
		}
		
	}
	
	/**
	 * 判断直播报名是否达到限制
	 */
	public void reachLimit(String liveId,String limit){
		List<Record> list = Db.find(
				"select application_id from portal_live_application where live_id = "+liveId+
						" and application_method = '"+
						p.get("live_participate_method_application") + "' " +
						"and status = '" + p.get("application_method_passed") + "'");
		int total = list==null?0:list.size();
		int lim = (null == limit || limit.isEmpty())?0:Integer.parseInt(limit);
		this.setAttr("limit", lim>total?false:true);
	}
	
	/**
	 * 直播报名
	 */
	public void apply(){
		ParamsParser pp = new ParamsParser(this);
		Map<String,Object> rlt = new HashMap<String,Object>();
		try{
			String liveId = pp.getId();
			Custinfo cust = getSessionAttr(WeiXinConst.CUSTINFO);
			int uid = cust.getCustinfoId();
			Live live = Live.dao.findById(liveId);
			if(null != live){
				if("公开".equals(live.getLiveType()) && TpyDateUtil.dateToStr(live.getSignupStartTime()).compareTo(TpyDateUtil.dateToStr(new Date())) > 0){
					rlt.put("status", 100);
					rlt.put("msg", "报名未开始");
				}else if("公开".equals(live.getLiveType()) && TpyDateUtil.dateToStr(live.getSignupEndTime()).compareTo(TpyDateUtil.dateToStr(new Date())) < 0){
					rlt.put("status", 100);
					rlt.put("msg", "报名已结束");
				}else{
					//统计已报名人数
					List<Record> list = Db.find(
							"select application_id from portal_live_application where live_id = "+liveId+
									" and application_method = '"+
									p.get("live_participate_method_application") + "' " +
									"and status = '" + p.get("application_method_passed") + "'");
					int applicationAmount = 0;
					if(list != null){
						applicationAmount = list.size();
					}
					if(applicationAmount >= live.getLiveLimit()){
						rlt.put("status", 100);
						rlt.put("msg", "报名人数已满");
					}else{
						LiveApplication la = LiveApplication.dao.findFirst(
								"select * from portal_live_application where live_id ="+liveId + " and custinfo_id ="+uid);
						if(null != la){
							rlt.put("status", 100);
							if(la.getStatus().equals(p.get("application_method_cancel"))){
								rlt.put("msg", "您被禁止进入此直播间");
							}else if(la.getStatus().equals(p.get("application_method_passed"))){
								rlt.put("msg", "已报名");
							}else if(la.getStatus().equals(p.get("application_method_disallowance"))){
								rlt.put("msg", "报名未成功");
							}
						}else{
							la = new LiveApplication();
							la.setApplicationMethod(p.get("live_participate_method_application"));
							la.setCreateTime(new Date());
							la.setCustinfoId(uid);
							la.setLiveId(Integer.parseInt(liveId));
							la.setStatus(p.get("application_method_passed"));
							la.save();
							
							live.setSignupNum(live.getSignupNum() + 1);
							live.update();
							
							rlt.put("status", 0);
							rlt.put("msg", "您已报名成功");
						}
					}
				}
			}else{
				rlt.put("status", 100);
				rlt.put("msg", "直播信息不存在");
			}
		}catch(Exception e){
			log.error(e.getMessage(),e);
			rlt.put("status", 100);
			rlt.put("msg", "报名失败");
		}finally {
			this.renderJson(rlt);
		}
	}
	
	/**
	 * 申请听课码
	 */
	public void applyCode(){
		ParamsParser pp = new ParamsParser(this);
		Map<String,Object> rlt = new HashMap<String,Object>();
		try{
			String liveId = pp.getId();
			Custinfo cust = getSessionAttr(WeiXinConst.CUSTINFO);
			int uid = cust.getCustinfoId();
			Live live = Live.dao.findById(liveId);
			if(null != live){
				LiveApplication la = LiveApplication.dao.findFirst(
						"select * from portal_live_application where live_id ="+liveId + " and custinfo_id ="+uid);
				if(null != la){
					rlt.put("status", 100);
					if(la.getStatus().equals(p.get("application_method_cancel"))){
						rlt.put("msg", "您被禁止进入此直播间");
					}else if(la.getStatus().equals(p.get("application_method_passed"))){
						rlt.put("msg", "已申请邀请码");
//						if(null == la.getInvitationCode() || la.getInvitationCode().isEmpty()){
//							rlt.put("msg", "已申请，听课码稍后会以短信方式发送给您");
//						}else{
//							rlt.put("msg", "已申请，听课码已以短信方式发送给您，如未收到请联系我们");
//						}
					}else if(la.getStatus().equals(p.get("application_method_disallowance"))){
						rlt.put("msg", "申请未成功");
					}
				}else{
					la = new LiveApplication();
					la.setApplicationMethod(p.get("live_participate_method_code"));
					la.setCreateTime(new Date());
					la.setCustinfoId(uid);
					la.setLiveId(Integer.parseInt(liveId));
					la.setStatus(p.get("application_method_passed"));
					la.save();
					
					live.setTempSignupNum(live.getTempSignupNum() + 1);
					live.update();
					
					rlt.put("status", 0);
					rlt.put("msg", "已申请邀请码");
				}
			}
		}catch(Exception e){
			log.error(e.getMessage(),e);
			rlt.put("status", 100);
			rlt.put("msg", "申请失败");
		}finally {
			this.renderJson(rlt);
		}
	}
	
	/**
	 * 观看直播
	 */
	public void watch(){
		ParamsParser pp = new ParamsParser(this);
		Map<String,Object> rlt = new HashMap<String,Object>();
		try{
			String liveId = pp.getAllStr("live_id");
			
			Custinfo cust = getSessionAttr(WeiXinConst.CUSTINFO);
			int uid = cust.getCustinfoId();
			
			Live live = Live.dao.findById(liveId);
			if(live == null){
				rlt.put("status", 100);
				rlt.put("msg", "直播不存在");
			}else{
				if(!live.getLiveStatus().equals("直播中")){
					rlt.put("status", 100);
					rlt.put("msg", "直播未开始");
				}else{
					if(live.getLiveType().equals(p.get("live_secret_name"))){//私密
						String code =  pp.getAllStr("code");
						if(code != null && !code.isEmpty()){
							code = URLDecoder.decode(code,"UTF-8").trim();
						}
						rlt = valiCode(liveId, code, uid);
					}else if(live.getLiveType().equals(p.get("live_public_name"))){//公开
						rlt = join(liveId, uid);
					}
				}
			}
		}catch(Exception e){
			log.error(e.getMessage(),e);
			rlt.put("status", 100);
			rlt.put("msg", "报名失败,请稍后再试");
		}finally {
			this.renderJson(rlt);
		}
	}
	/**私密直播,验证身份
	 * @return
	 */
	private Map<String,Object> valiCode(String liveId,String code,int uid){
		Map<String,Object> map = new HashMap<String,Object>();
		LiveApplication la = LiveApplication.dao.findFirst("select * from portal_live_application where live_id ="+liveId + " and custinfo_id ="+uid);
		if(la == null){//未报名
			InvitationCode ic = InvitationCode.dao.findFirst("select * from portal_invitation_code where live_id ="+liveId + " and code = '"+code+"'");
			if(ic == null){
				map.put("status", 100);
				map.put("msg", "听课码不存在或和本直播不匹配");
			}else{
				if(ic.getUsed() == 1){
					map.put("status", 100);
					map.put("msg", "该听课码已被使用");
				}else{
					la = new LiveApplication();
					la.setApplicationMethod(p.get("live_participate_method_code"));
					la.setCreateTime(new Date());
					la.setCustinfoId(uid);
					la.setLiveId(Integer.parseInt(liveId));
					la.setStatus(p.get("application_method_passed"));
					la.setOnlineStatus("1");
					la.setOnlineTime(new Date());
					la.setInvitationCode(code);
					la.setArticipateFlag(1);
					la.save();
					ic.setUsed(1);
					ic.update();
					map.put("status", 0);
					map.put("msg", ConstantUtil.SUCCESS);
				}
				
			}
		}else{//报名或已申请邀请码
			if(la.getStatus().equals(p.get("application_method_disallowance"))){
				map.put("status", 100);
				map.put("msg", "报名未成功");
			}else if(la.getStatus().equals(p.get("application_method_cancel"))){
				map.put("status", 100);
				map.put("msg", "您被禁止进入此直播间");
			}else if(la.getStatus().equals(p.get("application_method_passed"))){
				if(la.getApplicationMethod().equals(p.get("live_participate_method_application"))){//报名,直接进入直播间
					la.setOnlineStatus("1");
					la.setOnlineTime(new Date());
					la.setArticipateFlag(1);
					la.update();
					map.put("status", 0);
					map.put("msg", ConstantUtil.SUCCESS);
				}else if(la.getApplicationMethod().equals(p.get("live_participate_method_code"))){//听课码
					if(la.getArticipateFlag() ==0 && (null == la.getInvitationCode() || la.getInvitationCode().isEmpty() || !code.equals(la.getInvitationCode()))){//未参与：听课码未生成，或听课码不正确
						//校验是否顾问邀请用户的听课码
						InvitationCode ic = InvitationCode.dao.findFirst("select * from portal_invitation_code where live_id ="+liveId + " and code='"+code+"'");
						if(null == ic || ic.getId() == null){
							map.put("status", 100);
							map.put("msg", "听课码不正确");
						}else if(ic.getUsed() == 1){
							map.put("status", 100);
							map.put("msg", "听课码已被使用");
						}else{
							la.setOnlineStatus("1");
							la.setOnlineTime(new Date());
							la.setArticipateFlag(1);
							la.setInvitationCode(code);
							la.update();
							ic.setUsed(1);
							ic.update();
							map.put("status", 0);
							map.put("msg", ConstantUtil.SUCCESS);
						}
					}else{
						la.setOnlineStatus("1");
						la.setOnlineTime(new Date());
						la.setArticipateFlag(1);
						la.update();
						map.put("status", 0);
						map.put("msg", ConstantUtil.SUCCESS);
					}
				}else{
					map.put("status", 1);
					map.put("msg", "参数错误：无法识别的申请方式");
					log.error("无效的申请方式：ID["+la.getApplicationId()+"]");
				}
			}
			
		}
		return map;
	}
	
	/**公开直播，直接观看
	 * @return
	 */
	private Map<String,Object> join(String liveId,int uid){
		Map<String,Object> map = new HashMap<String,Object>();
		LiveApplication la = LiveApplication.dao.findFirst("select * from portal_live_application where live_id ="+liveId + " and custinfo_id ="+uid);
		if(null == la){
			la = new LiveApplication();
			la.setApplicationMethod(p.get("live_participate_method_code"));
			la.setCreateTime(new Date());
			la.setCustinfoId(uid);
			la.setLiveId(Integer.parseInt(liveId));
			la.setStatus(p.get("application_method_passed"));
			la.setOnlineStatus("1");
			la.setOnlineTime(new Date());
			la.setArticipateFlag(1);
			la.save();
			map.put("status", 0);
			map.put("msg", ConstantUtil.SUCCESS);
		}else{
			la.setOnlineStatus("1");
			la.setOnlineTime(new Date());
			la.setArticipateFlag(1);
			la.update();
			map.put("status", 0);
			map.put("msg", ConstantUtil.SUCCESS);
		}
		return map;
	}
	
	
	/**
	 * 直播评论
	 */
	public void comment(){
		Map<String,Object> map = new HashMap<String,Object>();
		try{
			ParamsParser pp = new ParamsParser(this);
			String liveId = pp.getAllStr("live_id");
			String contents = pp.getAllStr("contents");
			if(null != contents && !"null".equalsIgnoreCase(contents) && !contents.isEmpty()){
				Custinfo cust = getSessionAttr(WeiXinConst.CUSTINFO);
				int uid = cust.getCustinfoId();
				LiveMessage lm = new LiveMessage();
				lm.setCustinfoId(uid);
				lm.setContents(contents);
				lm.setLiveId(Integer.parseInt(liveId));
				lm.setDeleteFlag(0);
				lm.setCreateTime(new Date());
				lm.save();
			}
			
			map.put("status", 0);
			map.put("msg", "评论成功");
			this.renderJson(map);
		}catch(Exception e){
			log.error(e.getMessage(),e);
		}
	}
	
	/**
	 * 获取用户留言
	 */
	public void liveMessages(){
		Map<String,Object> map = new HashMap<String,Object>();
		ParamsParser pp = new ParamsParser(this);
		try{
			List<Record> lmList = Db.find("select lm.*,c.wx_name,c.name,c.head_img from portal_live_message lm,crm_custinfo c "+
								" where lm.delete_flag=0 and lm.custinfo_id = c.custinfo_id and lm.live_id ="+pp.getId()+
								" order by lm.create_time desc limit 0,"+p.get("message_rows"));
			StringBuffer sb = new StringBuffer();
			for(Record lm : lmList){
				sb.append("<li>")
				.append("<p class=\"live-message-header alignItem\"><span><img src=\""+lm.getStr("head_img")+"\" alt=\"\" onerror=\"javascript:this.src='/resource/images/gj-headico.jpg';\" /></span>"+
				((null==lm.getStr("wx_name")||lm.getStr("wx_name").isEmpty())?"匿名用户":URLDecoder.decode(lm.getStr("wx_name"), "UTF-8"))+"</p>")
				.append("<p class=\"live-message-text\">"+URLDecoder.decode(lm.getStr("contents"), "UTF-8")+"</p>")
				.append("<p class=\"live-message-time\">"+TpyDateUtil.dateFormat(lm.getDate("create_time"),"yyyy-MM-dd hh:MM:ss")+"</p>")
				.append("</li>");
			}
			map.put("messages", sb.toString());
			
			//检测用户参与状态是否正常
			Custinfo cust = getSessionAttr(WeiXinConst.CUSTINFO);
			int uid = cust.getCustinfoId();
			LiveApplication la = LiveApplication.dao.findFirst("select * from portal_live_application where live_id ="+pp.getId() + " and custinfo_id ="+uid);
			if(la != null && la.getStatus().equals(p.get("application_method_cancel"))){
				map.put("status", 2);//禁止参与
			}
		}catch(Exception e){
			log.error(e.getMessage());
		}finally {
			this.renderJson(map);
		}
	}
	
	/**
	 * 用户点赞
	 */
	public void praise(){
		ParamsParser pp = new ParamsParser(this);
		Map<String,Object> map = new HashMap<String,Object>();
		Custinfo cust = getSessionAttr(WeiXinConst.CUSTINFO);
		int uid = cust.getCustinfoId();
		try{
			LiveLike ll = LiveLike.dao.findFirst("select * from portal_live_like where live_id="+pp.getId()+" and custinfo_id="+uid);
			if(ll != null){
				ll.deleteById(ll.getId());
			}
			ll = new LiveLike();
			ll.setLiveId(Integer.parseInt(pp.getId()));
			ll.setCustinfoId(uid);
			ll.setLike(ConstantUtil.LIVE_PRAISE);
			ll.save();
			map.put("status", 0);
			map.put("msg", ConstantUtil.SUCCESS);
		}catch(Exception e){
			log.error(e.getMessage(),e);
			map.put("status", 100);
			map.put("msg", "点赞失败");
		}finally {
			this.renderJson(map);
		}
	}
	
	/**
	 * 用户拍砖
	 */
	public void tread(){
		ParamsParser pp = new ParamsParser(this);
		Map<String,Object> map = new HashMap<String,Object>();
		Custinfo cust = getSessionAttr(WeiXinConst.CUSTINFO);
		int uid = cust.getCustinfoId();
		try{
			LiveLike ll = LiveLike.dao.findFirst("select * from portal_live_like where live_id="+pp.getId()+" and custinfo_id="+uid);
			if(ll != null){
				ll.deleteById(ll.getId());
			}
			ll = new LiveLike();
			ll.setLiveId(Integer.parseInt(pp.getId()));
			ll.setCustinfoId(uid);
			ll.setLike(ConstantUtil.LIVE_TREAD);
			ll.save();
			map.put("status", 0);
			map.put("msg", ConstantUtil.SUCCESS);
		}catch(Exception e){
			log.error(e.getMessage(),e);
			map.put("status", 100);
			map.put("msg", "点赞失败");
		}finally {
			this.renderJson(map);
		}
	}
	
	/**
	 *  页面刷新时更新在线状态
	 */
	public void offLine(){
		ParamsParser pp = new ParamsParser(this);
		Map<String,Object> map = new HashMap<String,Object>();
		Custinfo cust = getSessionAttr(WeiXinConst.CUSTINFO);
		int uid = cust.getCustinfoId();
		try{
			LiveApplication la = LiveApplication.dao.findFirst("select * from portal_live_application where live_id="+pp.getId()+" and custinfo_id="+uid);
			if(null != la){
				la.setOnlineStatus("0");
				la.update();
			}
		}catch(Exception e){
			log.error(e.getMessage(),e);
		}finally {
			this.renderJson(map);
		}
	}
}
