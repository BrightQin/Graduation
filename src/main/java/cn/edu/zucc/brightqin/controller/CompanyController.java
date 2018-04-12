package cn.edu.zucc.brightqin.controller;

import cn.edu.zucc.brightqin.entity.Company;
import cn.edu.zucc.brightqin.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: brightqin
 * Date: 2018-04-03
 *
 * @author brightqin
 */
@Controller
@RequestMapping(value = "/company")
public class CompanyController {
    @Autowired
    public CompanyService companyService;

    /**
     * 保存Company
     * @param company company
     * @param result  result
     * @param map     map
     * @return
     */
    @RequestMapping(value = "/saveCompany", method = RequestMethod.POST)
    public String saveCompany(@Valid Company company, BindingResult result, ModelMap map) {
        if (result.hasErrors()) {
            List<FieldError> errors = result.getFieldErrors();
            for (FieldError error : errors) {
                map.put("ERROR_" + error.getField(), error.getDefaultMessage());
                System.out.println(error.getField() + "*" + error.getDefaultMessage());
            }
            return "personSavePage";
        } else {
            companyService.addCompany(company);
            return "redirect:main";
        }
    }

    /**
     * 跳转到添加页面
     * personSavePage.jsp
     *
     * @return
     */
    @RequestMapping(value = "/addCompany")
    public String addCompany() {
        return "companySavePage";
    }

    /**
     * 删除一条数据
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/deleteCompanyById")
    public String deleteCompanyById(@RequestParam(value = "id") String id) {
        companyService.deleteCompanyById(id);
        return "redirect:main";
    }

    /**
     * 跳转到更新页面，回显数据
     * personEditPage.jsp
     *
     * @param id
     * @param model 使用的Model保存回显数据
     * @return
     */
    @RequestMapping(value = "/doUpdate")
    public String doUpdate(@RequestParam(value = "id") String id, Model model) {
        model.addAttribute("company", companyService.getCompanyById(id));
        return "companyEditPage";
    }


    /**
     * 更新数据
     * @param company company
     * @param result  result
     * @param map     map
     * @return
     */
    @RequestMapping(value = "/updateCompany")
    public String updatePerson(@Valid Company company, BindingResult result, ModelMap map) {
        if (result.hasErrors()) {
            List<FieldError> errors = result.getFieldErrors();
            for (FieldError error : errors) {
                map.put("ERROR_" + error.getField(), error.getDefaultMessage());
                System.out.println(error.getField() + "*" + error.getDefaultMessage());
            }
            return "companyEditPage";
        } else {
            companyService.updateCompany(company);
            return "redirect:main";
        }
    }

    /**
     * 查询所有人员信息
     *
     * @param map 使用的是map保存回显数据
     * @return
     */
    @RequestMapping(value = "/main")
    public String main(Map<String, Object> map) {
        map.put("companyList", companyService.getCompanys());
        return "companyMain";
    }
}