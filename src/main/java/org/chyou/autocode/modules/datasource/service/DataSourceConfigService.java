package org.chyou.autocode.modules.datasource.service;

import org.chyou.autocode.modules.datasource.dao.DataSourceConfigDao;
import org.durcframework.service.CrudService;
import org.springframework.stereotype.Service;
import org.chyou.autocode.modules.datasource.entity.DataSourceConfig;

@Service
public class DataSourceConfigService extends CrudService<DataSourceConfig, DataSourceConfigDao> {

}
