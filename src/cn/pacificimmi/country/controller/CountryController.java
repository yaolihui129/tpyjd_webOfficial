package cn.pacificimmi.country.controller;


import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.pacificimmi.common.DictionaryManager;
import cn.pacificimmi.common.PagesBar;
import cn.pacificimmi.common.ParamsParser;
import cn.pacificimmi.common.interceptor.MenuInterCeptor;
import cn.pacificimmi.common.models.Country;
import cn.pacificimmi.common.models.Dictionary;
import cn.pacificimmi.common.models.Project;
import cn.pacificimmi.common.utils.StringUtils;
import cn.pacificimmi.country.models.view.CounProject;
import cn.pacificimmi.country.models.view.ProjectModel;

import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
import com.jfinal.kit.PropKit;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
@Before({MenuInterCeptor.class,CountryInterCeptor.class})
public class CountryController extends Controller{
	
	private static Logger logger = LoggerFactory.getLogger(CountryController.class);
	/**
	 * 国家列表页
	 */
	public void index(){
		logger.info("进入国家列表页");
		ParamsParser pp = new ParamsParser(this);
		String continent_name = pp.getAllStr("continent_name");
		String hotspot = pp.getAllStr("hotspot");
		if(StringUtils.notEmpty(hotspot)){
			this.setAttr("hotspot", hotspot);
		}else{
			hotspot = "";
		}
		if(StringUtils.notEmpty(continent_name)){
			this.setAttr("continent_name", continent_name);
		}else{
			continent_name = "";
		}
		//移民目的信息
		String hotSpotsSql = "select * from console_dictionary where dict_pcode = '00130001'";
		List<Dictionary> hotspots = Dictionary.dao.find(hotSpotsSql);
		this.setAttr("hotspots", hotspots);
		//查询所有发布国家信息，根据国家信息查询大洲，存到map中，
		String countrysql = "select * from crm_country where delete_flag = '0' and release_status = '1' and continent_name like '%"+continent_name+"%' and hotspots_id like '%"+hotspot+"%'";
		List<Country> countryList = Country.dao.find(countrysql);
		this.setAttr("countryList", countryList);
		String continentsql = "select DISTINCT(continent_name) from crm_country where delete_flag = '0' and release_status = '1'";
		List<Country> continents = Country.dao.find(continentsql);
		this.setAttr("continents", continents);
		this.renderJsp("/views/country/country_list.jsp");
		
	}
	/**
	 * 国家概况
	 */
	
	public void survey(){
		String country_id = this.getPara(0);
		if(StringUtils.notEmpty(country_id)){
			String countrySql = "select * from crm_country where delete_flag = '0' and release_status = '1' and country_id = "+country_id+"";
			Country country = Country.dao.findFirst(countrySql);
			this.setAttr("country", country);
			String projectSql = "select * from crm_project where country = "+ country_id+" and project_level = "+PropKit.use("constants.txt").get("project_level_one")+" and delete_flag = '0' and project_status = "+PropKit.use("constants.txt").get("project_status_sx")+" order by create_time asc";
			List<Project> projects = Project.dao.find(projectSql);
			this.setAttr("projects", projects);
			
			this.renderJsp("/views/country/country_survey.jsp");
		}else{
			this.redirect("/country");
		}
	}
	
	
	/**
	 * 移民项目
	 */
	
	public void project(){
		String country_id = this.getPara(0);
		if(StringUtils.notEmpty(country_id)){
			String countrySql = "select * from crm_country where delete_flag = '0' and release_status = '1' and country_id = "+country_id+"";
			Country country = Country.dao.findFirst(countrySql);
			this.setAttr("country", country);
			//查询移民项目类型
			List<Record> projectTpyes = null;
			List<Record> firstList = null;
			List<Record> secondList = null;
			List<CounProject> counProjects = new ArrayList<CounProject>();
			
			projectTpyes = Db.find("select DISTINCT project_type projectType,cd.name  projectName FROM `crm_project` cp "
					+ "LEFT JOIN console_dictionary cd on cd.dict_code = cp.project_type where country like '%"+country_id+"%'"
					+ "and cp.delete_flag = 0 and cp.project_status = "+PropKit.use("constants.txt").get("project_status_sx")+"");
			for(Record re:projectTpyes){
				CounProject type = new CounProject();
				type.bindingData(type, re);
				//根据类型查询一级项目
				List<ProjectModel> fristProjects = new ArrayList<ProjectModel>();
				firstList = Db.find("select "
						+"p.project_id,p.project_name,p.introduce,p.investments,p.advantage,p.project_img,p.web_project_img,"
						+"(SELECT d.name from console_dictionary d where d.delete_flag=0 and d.dict_code=p.visa_type)visa_type,"
						+"(SELECT d.name from console_dictionary d where d.delete_flag=0 and d.dict_code=p.`language`)language,"
						+"(SELECT d.name from console_dictionary d where d.delete_flag=0 and d.dict_code=p.live_requirement)live_requirement,"
						+"(SELECT d.name from console_dictionary d where d.delete_flag=0 and d.dict_code=p.qualifications)qualifications"
						+ " from crm_project p where p.project_level = "+PropKit.use("constants.txt").get("project_level_one")+" and p.country like '%"+country_id+"%' and p.delete_flag = 0 and p.project_status = "+PropKit.use("constants.txt").get("project_status_sx")+" and p.project_type = "+type.getProjectType());
				for(Record rec:firstList){
					ProjectModel first = new ProjectModel();
					first.bindingData(first, rec);
					fristProjects.add(first);
				}
				type.setFirstLevels(fristProjects);
				
				//根据类型查询二级项目
				List<ProjectModel> secondProjects = new ArrayList<ProjectModel>();
				secondList = Db.find("select "
						+"p.project_id,p.project_name,p.introduce,p.investments,p.advantage,p.project_img,p.web_project_img,"
						+"(SELECT d.name from console_dictionary d where d.delete_flag=0 and d.dict_code=p.visa_type)visa_type,"
						+"(SELECT d.name from console_dictionary d where d.delete_flag=0 and d.dict_code=p.`language`)language,"
						+"(SELECT d.name from console_dictionary d where d.delete_flag=0 and d.dict_code=p.live_requirement)live_requirement,"
						+"(SELECT d.name from console_dictionary d where d.delete_flag=0 and d.dict_code=p.qualifications)qualifications"
						+ " from crm_project p where p.project_level = "+PropKit.use("constants.txt").get("project_level_two")+" and p.country like '%"+country_id+"%' and p.delete_flag = 0 and p.project_status = "+PropKit.use("constants.txt").get("project_status_sx")+" and p.project_type = "+type.getProjectType());
				for(Record rec:secondList){
					ProjectModel second = new ProjectModel();
					second.bindingData(second, rec);
					secondProjects.add(second);
				}
				type.setSecondLevels(secondProjects);
				counProjects.add(type);
			}
			this.setAttr("counProjects", counProjects);
			
			this.renderJsp("/views/country/country_project.jsp");
		}else{
			this.redirect("/country");
		}
	}

	
	/**
	 * 最新政策
	 */
	
	public void policy(){
		String country_id = this.getPara(0);
		if(StringUtils.notEmpty(country_id)){
			String countrySql = "select * from crm_country where delete_flag = '0' and release_status = '1' and country_id = "+country_id+"";
			Country country = Country.dao.findFirst(countrySql);
			this.setAttr("country", country);
			
			InfotmationParams sp = new InfotmationParams(this);
			sp.setPageSize(20);
			/***
			 * 获取数据列表
			 */
			Page<Record> page = Db.paginate(sp.getPageNum(), sp.getPageSize(), sp.getSelectStr(), sp.getFromStr());
			List<Record> list = page.getList();

			/***
			 * 保存数据列表
			 */
			this.setAttr("list", list);
			
			//资讯状态
			this.setAttr("statusList", DictionaryManager.getInstance().getSubDictionariesByDegree(PropKit.use("constants.txt").get("publish_status"), 1).getSubDictionaries());
			
			/***
			 * 保存翻页
			 */
			String pagesView = PagesBar.getShortPageBar(sp.getPageNum(), page.getTotalPage(), page.getPageSize(), page.getTotalRow(), 5);
			this.setAttr("pageBar",pagesView);
			
			this.renderJsp("/views/country/country_policy.jsp");
		}else{
			this.redirect("/country");
		}
	}
	
	/**
	 * 查询参数解析类(资讯发布)
	 * @author Gorge
	 */
	private class InfotmationParams extends ParamsParser{

		public InfotmationParams(Controller ctr) {
			super(ctr);
			this.setSelectStr("select * ");
			this.setFromStr("from (" +
			" select a.info_id,a.section_id,section_name,a.title,a.country,a.status,d.name statusZN,a.list_img,a.contents,a.click_count,a.publish_user,a.publish_time,a.create_time,a.sort,\"文章\" section_type" +
			" from crm_information_article a left join console_dictionary d on a.status = d.dict_code and d.delete_flag = 0" + 
			" where a.delete_flag = 0 " +
			") t");
					
			//资讯名称
			String title = this.getAllStr("title");
			if(title !=null && !title.isEmpty()){
				this.addWhereSegmentByAnd("title like '%"+title.trim()+"%'");
				setAttr("title", title.trim());
			}
			
			//发布状态
			String pubStatus = PropKit.use("constants.txt").get("publish_status_yfb");
			if(pubStatus != null){
				this.addWhereSegmentByAnd(" status = '" + pubStatus +"'");
			}
			
			//国家
			String coun_id = ctr.getPara(0);
			if(coun_id != null && !"".equals(coun_id)){
				this.addWhereSegmentByAnd(" country = '" + coun_id +"'");
			}
			
			//栏目 固定为移民政策
			//String secId = "0003";
			
			//String ifParent = this.getNormStr("ifParent");
			//if("1".equals(ifParent)){
			//	this.addWhereSegmentByAnd("section_id in (select section_id from crm_section where pcode = '"+secId+"')");
			//}else{
			//	if(secId!=null && !secId.isEmpty())
					//this.addWhereSegmentByAnd("section_id in (select section_id from crm_section where code = '"+secId+"')");
			//栏目 固定为移民政策
			this.addWhereSegmentByAnd("find_in_set((select section_id from crm_section where delete_flag = 0 and name = '移民政策'), section_id)");
			//}
			
			this.setDefaultOrderStr("order by sort desc,publish_time desc");
		}
	}
}
