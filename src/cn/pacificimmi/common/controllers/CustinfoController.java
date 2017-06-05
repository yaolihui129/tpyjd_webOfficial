package cn.pacificimmi.common.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.jfinal.core.Controller;

import cn.pacificimmi.common.models.Custinfo;
import cn.pacificimmi.common.utils.CrmCustinfoUtils;
/**
 * 客户信息控制器，前台会员、获客的公共接口
 */
public  class CustinfoController extends Controller{
	private static Logger log = LoggerFactory.getLogger(CustinfoController.class);
	
	public static void addCust(Custinfo cust){
		log.debug("保存客户信息");
		if(null != cust){
			Custinfo custinfo = Custinfo.dao.findFirst("select * from crm_custinfo where delete_flag=0 and phone_num="+cust.getPhoneNum());
			if(null == custinfo || custinfo.getCustinfoId() == null){
				cust.save();
				
				//CrmCustinfoUtils.saveCustinfoToCrm(cust);
			}else{
				if(null == custinfo.getCrmId() || custinfo.getCrmId().isEmpty()){
					//CrmCustinfoUtils.saveCustinfoToCrm(custinfo);
				}
			}
		}
	}
}
