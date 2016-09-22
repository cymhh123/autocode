package org.chyou.autocode.modules.tpl.service;

import org.durcframework.service.CrudService;
import org.springframework.stereotype.Service;
import org.chyou.autocode.modules.tpl.dao.TemplateConfigDao;
import org.chyou.autocode.modules.tpl.entity.TemplateConfig;

@Service
public class TemplateConfigService extends CrudService<TemplateConfig, TemplateConfigDao> {

}
