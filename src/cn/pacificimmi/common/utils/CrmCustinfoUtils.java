package cn.pacificimmi.common.utils;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.jfinal.kit.Prop;
import com.jfinal.kit.PropKit;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;

import cn.pacificimmi.common.models.Custinfo;

/**客户信息和CRM相关接口调用处理类
 * @author Gorge
 *
 */
public class CrmCustinfoUtils {
	private static Logger log = LoggerFactory.getLogger(CrmCustinfoUtils.class);
	private static Prop dp = PropKit.use("debug_config.txt");
	/**同步客户信息到crm 并反写crm_id到custinfo表
	 * @param custinfo
	 * @param wllymx
	 * @throws Exception
	 */
	public static void saveCustinfoToCrm(Custinfo custinfo){
		try{
			Record record = Db.findFirst("select name from console_dictionary where delete_flag = 0 and dict_code = '"+custinfo.getCustSource()+"'");
			
			Map[] dataMap = new Map[1];
			Map<String,Object> paramData = new HashMap<String,Object>();
			paramData.put("qzkhly", "在线注册");//客户来源 - crm来源类型
			paramData.put("wllymx", (null == record ? "":record.getStr("name")));//明细 - 权限字典来源
			paramData.put("name", (null == custinfo.getName() || custinfo.getName().isEmpty())?"网络客户":custinfo.getName());
			paramData.put("createdate", custinfo.getCreateTime());
			paramData.put("phone1", custinfo.getPhoneNum());//手机
			dataMap[0] = paramData;

			Map<String,String> postParam = new HashMap<String,String>();
			postParam.put("serviceName", "insert");
			postParam.put("objectApiName", "Lead");
			postParam.put("data", JSON.toJSONString(dataMap));
			postParam.put("binding", CrmUtils.getBinding());

			//解析CRM返回值，存储客户的CRM id值
			String crmReturn = Client.doPost(dp.get("CRM_API_URL"), postParam);
			JSONObject jsonResult = JSONObject.parseObject(crmReturn);
			if(jsonResult.getBoolean("result") && "1".equals(jsonResult.getString("returnCode"))){
				JSONObject dataResult = JSONObject.parseObject(jsonResult.get("data").toString());
				com.alibaba.fastjson.JSONArray jsonArray = dataResult.getJSONArray("ids");

		        Iterator<Object> it = jsonArray.iterator();
		        while (it.hasNext()) {
		            JSONObject ob = (JSONObject) it.next();
		            custinfo.setCrmId(ob.getString("id"));
		            custinfo.update();
		        }
			}else{
				log.error("同步客户信息到Crm系统时出错:[id,name]:["+custinfo.getCustinfoId()+","+custinfo.getName()+"]");
				log.error("调用结果:"+JSON.toJSONString(jsonResult));
			}
			
		}catch(Exception e){
			log.error("同步客户信息到Crm系统时出错:[id,name]:["+custinfo.getCustinfoId()+","+custinfo.getName()+"]");
			log.error(e.getMessage(), e);
		}
		
	}
	
	public static void main(String[] args){
		
	}
}
