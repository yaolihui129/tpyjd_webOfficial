package cn.pacificimmi.about_us.controllers;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;

import cn.pacificimmi.common.ParamsParser;
import cn.pacificimmi.common.interceptor.MenuInterCeptor;
import cn.pacificimmi.common.interceptor.AboutUsInterceptor;
import cn.pacificimmi.common.models.Dictionary;
import cn.pacificimmi.common.models.StewardEvaluate;
import cn.pacificimmi.common.models.User;

@Before({MenuInterCeptor.class,AboutUsInterceptor.class})
public class ProfessionalTeamController extends Controller {
	public void index(){
		List<Map<String,Object>> result = new ArrayList<Map<String,Object>>();
		
		List<Record> teamlist = Db.find("select * from console_dictionary where dict_pcode='0033' order by sort asc");
		for(Record team:teamlist){
			Map<String,Object> teamMap = new HashMap<String,Object>();
			String name = team.getStr("name");
			String short_name = team.getStr("short_name");
			
			String sql = "select cpt.team_id,cu.user_name,cu.head_img,cu.english_name,cu.job_title,cu.introduce from crm_professional_team as cpt left join console_user as cu on cpt.user_id = cu.user_id where cpt.team_type='"+name+"' and cpt.release_mark='已发布' order by cpt.sort desc limit 0,10";
			List<Record> experts = Db.find(sql);
			if(experts!=null && experts.size()>0){
				teamMap.put("team_name", name);
				teamMap.put("short_name", short_name);
				teamMap.put("experts", experts);
				result.add(teamMap);
			}
		}
		
		setAttr("result",result);
		this.renderJsp("/views/aboutus/Professional-team.jsp");
	}
	
	public void expert(){
		ParamsParser pp = new ParamsParser(this);
		String tid = pp.getId();
		if(tid!=null){
			Record team = Db.findFirst("select * from crm_professional_team where team_id="+tid+" and delete_flag='0'");
			if(team!=null){
				Integer userid = team.getInt("user_id");
				User user = User.dao.findById(userid);
				setAttr("user",user);
				
				if(user.getCityId()!=null){
					Dictionary city = Dictionary.dao.findFirst("select * from console_dictionary where dict_code='"+user.getCityId()+"'");
					setAttr("city",city.getName());
				}
				
				String video = team.getStr("video");
				if(video!=null){
					setAttr("video",video);
				}
				
				/**
				 * 擅长的国家
				 */
				String countrys = team.getStr("expert_country");
				if(countrys!=null){
					String[] countries = countrys.split(",");
					List<String> expert_countries = new ArrayList<String>();
					for(int i=0;i<countries.length;i++){
						expert_countries.add(countries[i]);
					}
					
					setAttr("expert_countries",expert_countries);
				}
				
				/**
				 * 是否为移民管家
				 */
				Record steward = Db.findFirst("select * from crm_steward_user where user_id="+userid);
				if(steward!=null){
					Integer steward_id = steward.getInt("steward_id");
					
					Record liked = Db.findFirst("select count(*) as c from crm_steward_like where steward_id="+steward_id);
					setAttr("liked",liked.getLong("c"));
					
					float level = getDynamicScore(steward_id);
					int stars = (new BigDecimal(String.valueOf(level)).setScale(0, BigDecimal.ROUND_HALF_UP)).intValue();
					
					setAttr("stars",stars);
				}
				this.renderJsp("/views/aboutus/Professional-content.jsp");
			}
			else{
				this.renderHtml("/404.html");
			}
		}
		else{
			this.renderHtml("/404.html");
		}
	}
	
	private float getDynamicScore(Integer steward_id){
		
		//统计该管家有多少个参与评价的客户
		float countStar=0.0f;
		//统计该管家所有参与评价的客户的总的评分
		Float sumStar=0.0f; 
		
		float result=5.00f;
		List<StewardEvaluate> find = StewardEvaluate.dao.find("select se.evaluate_id as evaluate_id, "
				+ "se.star_level as star_level FROM crm_steward_evaluate AS se WHERE  se.delete_flag = '0' AND se.hidden=0 and se.steward_id='"+steward_id+"' order by se.create_time  desc");
		if(find.size()>0){
			for (int i = 0; i < find.size(); i++) {
				StewardEvaluate stewardEvaluate = find.get(i); 
				Float star_level=stewardEvaluate.getStarLevel(); 
				if(star_level==null)
					star_level=0f;
				countStar++;
				sumStar=sumStar+star_level;
			}
		}
		
		if(countStar!=0){
			result=sumStar/countStar; 
		}
		
		return result;
	}
	
	
}
