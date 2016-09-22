package org.chyou.autocode.modules.user.controller;

import org.durcframework.controller.CrudController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.chyou.autocode.modules.user.entity.BackUser;
import org.chyou.autocode.modules.user.entity.BackUserSch;
import org.chyou.autocode.modules.user.service.BackUserService;

/**
 * 后台管理用户Controller
 */
@Controller
public class BackUserController extends
        CrudController<BackUser, BackUserService> {

    /**
     * 添加用户
     * @param entity
     * @return
     */
    @RequestMapping("/addBackUser.do")
    public ModelAndView addBackUser(BackUser entity) {
        return this.save(entity);
    }

    /**
     * 用户列表
     * @param searchEntity
     * @return
     */
    @RequestMapping("/listBackUser.do")
    public ModelAndView listBackUser(BackUserSch searchEntity) {
        return this.queryByEntity(searchEntity);
    }

    /**
     * 更新用户
     * @param enity
     * @return
     */
    @RequestMapping("/updateBackUser.do")
    public ModelAndView updateBackUser(BackUser enity) {
        return this.update(enity);
    }

    /**
     * 删除用户
     * @param enity
     * @return
     */
    @RequestMapping("/delBackUser.do")
    public ModelAndView delDataSource(BackUser enity) {
        return this.delete(enity);
    }
    
}
