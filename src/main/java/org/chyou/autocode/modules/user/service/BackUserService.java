package org.chyou.autocode.modules.user.service;

import org.durcframework.service.CrudService;
import org.springframework.stereotype.Service;
import org.chyou.autocode.modules.user.dao.BackUserDao;
import org.chyou.autocode.modules.user.entity.BackUser;

/**
 * 后台管理用户Service
 */
@Service
public class BackUserService extends CrudService<BackUser, BackUserDao> {

}
