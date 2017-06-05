package cn.pacificimmi.project.controller;

import java.util.ArrayList;
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
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;

import cn.pacificimmi.common.PagesBar;
import cn.pacificimmi.common.ParamsParser;
import cn.pacificimmi.common.interceptor.MenuInterCeptor;
import cn.pacificimmi.common.models.Custinfo;
import cn.pacificimmi.common.models.Dictionary;
import cn.pacificimmi.common.models.Project;
import cn.pacificimmi.common.models.ProjectApplyProcess;
import cn.pacificimmi.common.models.ProjectResource;
import cn.pacificimmi.common.models.Topics;
import cn.pacificimmi.common.utils.StringUtils;
import cn.pacificimmi.project.models.view.ProjectByCountryInfo;
import cn.pacificimmi.project.models.view.ProjectDetailInfo;
import cn.pacificimmi.project.models.view.ProjectInfo;


@Before({MenuInterCeptor.class, ProjectInterCeptor.class})
public class ProjectController extends Controller{
	
	private static Logger log = LoggerFactory.getLogger(ProjectController.class);
	
	/**
	 * 项目列表
	 */
	public void index() {
		ProjectParams pp = new ProjectParams(this);
		pp.setPageSize(15);
		Page<Record> page = Db.paginate(pp.getPageNum(), pp.getPageSize(), pp.getSelectStr(), pp.getFromStr());
		List<Record> list = page.getList();
		List<ProjectInfo> result = new ArrayList<ProjectInfo>();
		for(Record rd : list) {
			ProjectInfo pi = new ProjectInfo();
			pi.bindingData(pi, rd);
			
			if(StringUtils.notEmpty(rd.getStr("visa_type_description"))) {
				pi.setVisa_type(rd.getStr("visa_type_description"));
			}
			if(StringUtils.notEmpty(rd.getStr("language_description"))) {
				pi.setLanguage(rd.getStr("language_description"));
			}
			if(StringUtils.notEmpty(rd.getStr("live_requirement_description"))) {
				pi.setLive_requirement(rd.getStr("live_requirement_description"));
			}
			result.add(pi);
		}

		List<Dictionary> typeList = Dictionary.dao.find("SELECT "+
				"d.dict_code, "+
				"d.`name` "+
			"FROM "+
				"console_dictionary d "+
			"JOIN ( "+
				"SELECT DISTINCT "+
					"(p.project_type) "+
				"FROM "+
					"crm_project p "+
				"WHERE "+
					"p.delete_flag = 0 "+
				"AND p.project_level = "+PropKit.use("constants.txt").get("project_level_one")+
				" AND p.project_status = "+PropKit.use("constants.txt").get("project_status_sx")+
			") pt ON d.dict_code = pt.project_type "+
			"WHERE "+
				"d.delete_flag = 0 "+
			"ORDER BY "+
				"d.sort ASC");
		
		List<Dictionary> countryList = null;
		
		String continent = pp.getAllStr("continent");
		if(StringUtils.notEmpty(continent)) {
			countryList = Dictionary.dao.find("SELECT "+
					"d.dict_code, "+
					"d.`name` "+
				"FROM "+
					"console_dictionary d "+
				"JOIN ( "+
					"SELECT DISTINCT "+
						"(p.country) "+
					"FROM "+
						"crm_project p "+
					"WHERE "+
						"p.delete_flag = 0 "+
					"AND p.project_level = "+PropKit.use("constants.txt").get("project_level_one")+
					" AND p.project_status = "+PropKit.use("constants.txt").get("project_status_sx")+
				") pt ON d.dict_code = pt.country "+
				"WHERE "+
					"d.delete_flag = 0 and d.dict_code like '"+continent+"0%'");
			this.setAttr("continent", continent);
		} else {
			countryList = Dictionary.dao.find("SELECT "+
					"d.dict_code, "+
					"d.`name` "+
				"FROM "+
					"console_dictionary d "+
				"JOIN ( "+
					"SELECT DISTINCT "+
						"(p.country) "+
					"FROM "+
						"crm_project p "+
					"WHERE "+
						"p.delete_flag = 0 "+
					"AND p.project_level = "+PropKit.use("constants.txt").get("project_level_one")+
					" AND p.project_status = "+PropKit.use("constants.txt").get("project_status_sx")+
				") pt ON d.dict_code = pt.country "+
				"WHERE "+
					"d.delete_flag = 0");
		}
		
		
		List<Dictionary> continentList = Dictionary.dao.find("SELECT DISTINCT "+
				"(cd.dict_code), "+
				"cd.`name` "+
			"FROM "+
				"console_dictionary cd "+
			"WHERE "+
				"cd.dict_code IN ( "+
					"SELECT DISTINCT "+
						"(d.dict_pcode) "+
					"FROM "+
						"console_dictionary d "+
					"JOIN ( "+
						"SELECT DISTINCT "+
							"(p.country) "+
						"FROM "+
							"crm_project p "+
						"WHERE "+
							"p.delete_flag = 0 "+
						"AND p.project_level = "+PropKit.use("constants.txt").get("project_level_one")+
						" AND p.project_status = "+PropKit.use("constants.txt").get("project_status_sx")+
					") pt ON d.dict_code = pt.country "+
					"WHERE "+
						"d.delete_flag = 0 "+
				") "+
			"AND cd.delete_flag = 0");
		
		String type = pp.getAllStr("type");
		if(StringUtils.notEmpty(type)) {
			this.setAttr("type", type);
		}
		String country = pp.getAllStr("country");
		if(StringUtils.notEmpty(country)) {
			this.setAttr("country", country);
		}
		
		String pagesView = PagesBar.getShortPageBar(pp.getPageNum(), page.getTotalPage(), page.getPageSize(), page.getTotalRow(), 5);
		this.setAttr("pageBar",pagesView);
		
		this.setAttr("continentList", continentList);
		this.setAttr("typeList", typeList);
		this.setAttr("countryList", countryList);
		this.setAttr("list", result);
		this.renderJsp("/views/project/list.jsp");
	}
	
	/**
	 * 项目详情
	 */
	public void info() {
		String id = this.getPara(0);
		String code = this.getPara(1);
		String in = "";
		if(StringUtils.notEmpty(code) && code.equals("preview")) {
			in = "";
		} else {
			in=" and p.project_status in("+PropKit.use("constants.txt").get("project_status_sx")+", "+PropKit.use("constants.txt").get("project_status_sq")+")";
		}
		Record rd = Db.findFirst("SELECT "+
				"p.project_id, "+
				"p.project_name, "+
				"p.project_status, "+
				"p.introduce, "+
				"p.web_project_img, "+
				"p.web_introduce, "+
				"p.advantage, "+
				"p.recommended, "+
				"p.apply_condition, "+
				"p.visa_type_description, "+
				"p.live_requirement_description, "+
				"p.language_description, "+
				"p.qualifications_description, "+
				"p.manage_description, "+
				"(SELECT d. NAME FROM	console_dictionary d WHERE d.delete_flag = 0 AND d.dict_code = p.project_type)project_type, "+
				"(SELECT d. NAME FROM	console_dictionary d WHERE d.delete_flag = 0 AND d.dict_code = p.visa_type)visa_type, "+
				"(SELECT d. NAME FROM	console_dictionary d WHERE d.delete_flag = 0 AND d.dict_code = p.live_requirement)live_requirement, "+
				"(SELECT d. NAME FROM	console_dictionary d WHERE d.delete_flag = 0 AND d.dict_code = p.language)language, "+
				"(SELECT d. NAME FROM	console_dictionary d WHERE d.delete_flag = 0 AND d.dict_code = p.qualifications)qualifications, "+
				"(SELECT d. NAME FROM	console_dictionary d WHERE d.delete_flag = 0 AND d.dict_code = p.manage)manage "+
			"FROM crm_project p "+
			"WHERE p.delete_flag=0 and p.project_id= "+id+in+
			" ORDER BY p.priority DESC");

		ProjectDetailInfo pi = new ProjectDetailInfo();
		pi.bindingData(pi, rd);
		
		if(StringUtils.notEmpty(rd.getStr("visa_type_description"))) {
			pi.setVisa_type(rd.getStr("visa_type_description"));
		}
		
		if(StringUtils.notEmpty(rd.getStr("live_requirement_description"))) {
			pi.setLive_requirement(rd.getStr("live_requirement_description"));
		}

		if(StringUtils.notEmpty(rd.getStr("language_description"))) {
			pi.setLanguage(rd.getStr("language_description"));
		}
		
		if(StringUtils.notEmpty(rd.getStr("qualifications_description"))) {
			pi.setQualifications(rd.getStr("qualifications_description"));
		}
		
		if(StringUtils.notEmpty(rd.getStr("manage_description"))) {
			pi.setManage(rd.getStr("manage_description"));
		}
		
		//项目优势
		String advantage = rd.getStr("advantage");
		if(StringUtils.notEmpty(advantage)) {
			advantage = advantage.replaceAll("；", ";");
			String[] adary = advantage.split(";");
			pi.setAdvantage(adary);
		}
		
		//子项目
		List<Project> subProjectList = Project.dao.find("SELECT "+
					"p.project_id, "+
					"p.project_name, "+
					"p.web_project_img, "+
					"p.introduce "+
				"FROM "+
					"crm_project p "+
				"WHERE "+
					"p.delete_flag = 0 "+
				"AND p.project_status ="+PropKit.use("constants.txt").get("project_status_sx")+
				" AND p.project_pid = "+id+
				" ORDER BY p.priority DESC");
		pi.setSubProjectList(subProjectList);

		//项目流程
		List<ProjectApplyProcess> projectApList = ProjectApplyProcess.dao.find("SELECT "+
					"ap.project_apply_process_id, "+
					"ap.name "+
				"FROM "+
					"crm_project_apply_process ap "+
				"WHERE "+
					"ap.delete_flag = 0 "+
				"AND ap.project_id = "+id+
				" ORDER BY "+
					"ap.priority asc");
		pi.setProjectApList(projectApList);
		
		this.setAttr("projectStatus", PropKit.use("constants.txt").get("project_status_sq"));
		this.setAttr("project", pi);
		this.renderJsp("/views/project/project.jsp");
	}
	
	/**
	 * 查询
	 * @author yang
	 * @date 2017年5月17日 下午3:29:01
	 */
	private class ProjectParams extends ParamsParser{

		public ProjectParams(Controller ct) {
			super(ct);
			// TODO Auto-generated constructor stub
			this.setSelectStr("SELECT "+
					"p.project_id, "+
					"p.web_project_img, "+
					"p.project_name, "+
					"p.investments, "+
					"p.immigration_budget, "+
					"p.visa_type_description, "+
					"p.language_description, "+
					"p.live_requirement_description, "+
					"( "+
						"SELECT "+
							"d. NAME "+
						"FROM "+
							"console_dictionary d "+
						"WHERE "+
							"d.delete_flag = 0 "+
						"AND d.dict_code = p.visa_type "+
					") visa_type, "+
					"( "+
						"SELECT "+
							"d. NAME "+
						"FROM "+
							"console_dictionary d "+
						"WHERE "+
							"d.delete_flag = 0 "+
						"AND d.dict_code = p.`language` "+
					") language, "+
					"( "+
						"SELECT "+
							"d. NAME "+
						"FROM "+
							"console_dictionary d "+
						"WHERE "+
							"d.delete_flag = 0 "+
						"AND d.dict_code = p.live_requirement "+
					") live_requirement");
			
			this.setFromStr("FROM crm_project p");
			this.addWhereSegmentByAnd("p.delete_flag=0");
			this.addWhereSegmentByAnd("p.project_status="+PropKit.use("constants.txt").get("project_status_sx"));
			this.addWhereSegmentByAnd("p.project_level="+PropKit.use("constants.txt").get("project_level_one"));
			
			String continent = this.getAllStr("continent");
			if(StringUtils.notEmpty(continent)) {
				this.addWhereSegmentByAnd("p.country like '"+continent+"%'");
			}
			
			String type = this.getAllStr("type");
			if(StringUtils.notEmpty(type)) {
				this.addWhereSegmentByAnd("p.project_type ="+type);
			}
			
			String country = this.getAllStr("country");
			if(StringUtils.notEmpty(country)) {
				this.addWhereSegmentByAnd("p.country = "+country);
			}
			
			this.setDefaultOrderStr("ORDER BY p.priority DESC");
		}
		
	}
	
}
