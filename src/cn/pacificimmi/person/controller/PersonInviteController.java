package cn.pacificimmi.person.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
import com.jfinal.kit.PropKit;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;

import cn.pacificimmi.common.ParamsParser;
import cn.pacificimmi.common.interceptor.BindingInterceptor;
import cn.pacificimmi.common.models.Custinfo;
import cn.pacificimmi.common.utils.WeiXinConst;
import cn.pacificimmi.person.models.view.PersionInviteInfo;


@Before(BindingInterceptor.class)
public class PersonInviteController extends Controller {
	
	private static Logger log = LoggerFactory.getLogger(PersonInviteController.class);
	
	/**
	 * 我的邀请
	 */
	public void index() {
		Custinfo custinfo = this.getSessionAttr(WeiXinConst.CUSTINFO);
		Integer custinfoId = custinfo.getCustinfoId();
		
		custinfo = Custinfo.dao.findById(custinfoId);
		
		if(null != custinfo.getProxyStatus() && custinfo.getProxyStatus().equals(PropKit.use("constants.txt").get("proxy_status_disabled"))) {
			this.setAttr("status", true);
		}
		
		Long sub_custinfo_count = Db.queryLong("select count(ccu.custinfo_id) from crm_custinfo ccu where delete_flag=0 and ccu.proxy="+custinfoId);
		this.setAttr("sub_custinfo_count", sub_custinfo_count);
		
		if(null != custinfo.getProxyLevel() && custinfo.getProxyLevel().equals(PropKit.use("constants.txt").get("proxy_level_one"))) {
			Long sub_proxy_count = Db.queryLong("select count(ccu.custinfo_id) from crm_custinfo ccu where delete_flag=0 and ccu.proxy_pid="+custinfoId+" AND ccu.audit_status = "+PropKit.use("constants.txt").get("audit_status_passed"));
			this.setAttr("sub_proxy_count", sub_proxy_count);
			String proxy_pid=this.getPara("proxy_pid");
			String proxy=this.getPara("proxy");
			if((null == proxy_pid || "".equals(proxy_pid)) && (null == proxy || "".equals(proxy))) {
				this.setAttr("proxy_pid", custinfoId);
			} else if(null != proxy_pid && !"".equals(proxy_pid)) {
				this.setAttr("proxy_pid", proxy_pid);
			} else if(null != proxy && !"".equals(proxy)) {
				this.setAttr("proxy", proxy);
			}
			this.setAttr("custinfoId", custinfoId);
			this.renderJsp("/WEB-INF/jsp/person/person-invite-one.jsp");
		} else if(null != custinfo.getProxyLevel() && custinfo.getProxyLevel().equals(PropKit.use("constants.txt").get("proxy_level_two"))) {
			this.setAttr("status_disabled", PropKit.use("constants.txt").get("proxy_status_disabled"));
			this.setAttr("custinfoId", custinfoId);
			this.renderJsp("/WEB-INF/jsp/person/person-invite-two.jsp");
		}
	}
	
	
	/**
	 * 获取下级代理或直属客户
	 */
	public void getCustinfoDate() {
		Map<String, Object> map = new HashMap<String, Object>();
		
		PersionInviteInfoParams pp = new PersionInviteInfoParams(this);
		pp.setPageSize(20);
		
		Page<Record> page = Db.paginate(pp.getPageNum(), pp.getPageSize(), pp.getSelectStr(), pp.getFromStr());
		
		List<Record> list = page.getList();
		List<PersionInviteInfo> result = new ArrayList<PersionInviteInfo>();
		for(Record rd : list) {
			PersionInviteInfo pi = new PersionInviteInfo();
			pi.bindingData(pi, rd);
			pi.setPhone_num(pi.getPhone_num().substring(0, 3)+"****"+pi.getPhone_num().substring(7, 11));
			pi.setSub_custinfo_count(rd.getLong("sub_custinfo_count"));
			result.add(pi);
		}
		
		map.put("page", pp.getPageNum());
		map.put("totalPage", page.getTotalPage());
		map.put("list", result);
		
		this.renderJson(map);
	}
	
	
	
	/**
	 * 查询
	 * @author yang
	 */
	private class PersionInviteInfoParams extends ParamsParser {

		public PersionInviteInfoParams(Controller ct) {
			super(ct);
			// TODO Auto-generated constructor stub
			this.setSelectStr("SELECT "+
					"ccu.custinfo_id, ccu.name, ccu.phone_num,	ccu.head_img, "+
					"( "+
						"SELECT "+
							"count(cu.custinfo_id) "+
						"FROM "+
							"crm_custinfo cu where cu.proxy=ccu.custinfo_id and cu.audit_status = "+PropKit.use("constants.txt").get("audit_status_passed")+
					") sub_custinfo_count");
			this.setFromStr(" FROM crm_custinfo ccu");
			this.addWhereSegmentByAnd("ccu.delete_flag=0");
			
			String custinfo_id = this.getId();
			String proxy_pid=this.getFieldName("proxy_pid");
			String proxy=this.getFieldName("proxy");
			
			if((null == proxy_pid || "".equals(proxy_pid.trim())) && (null == proxy || "".equals(proxy.trim()))) {
				this.addWhereSegmentByAnd("ccu.proxy_pid="+custinfo_id);
				this.addWhereSegmentByAnd("ccu.audit_status ="+PropKit.use("constants.txt").get("audit_status_passed"));
				this.setDefaultOrderStr("order by ccu.bind_proxy_time desc");
			} else if(null != proxy_pid && !"".equals(proxy_pid.trim())) {
				this.addWhereSegmentByAnd("ccu.proxy_pid="+proxy_pid);
				this.addWhereSegmentByAnd("ccu.audit_status ="+PropKit.use("constants.txt").get("audit_status_passed"));
				this.setDefaultOrderStr("order by ccu.passed_time desc");
			} else if(null != proxy && !"".equals(proxy.trim())) {
				this.addWhereSegmentByAnd("ccu.proxy="+proxy);
				this.setDefaultOrderStr("order by ccu.bind_proxy_time desc");
			}
			
		}
		
	}
	
	
}
