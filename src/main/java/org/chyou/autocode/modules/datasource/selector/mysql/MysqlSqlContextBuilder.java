package org.chyou.autocode.modules.datasource.selector.mysql;

import org.springframework.util.StringUtils;
import org.chyou.autocode.modules.datasource.entity.ColumnDefinition;
import org.chyou.autocode.modules.datasource.entity.DataBaseConfig;
import org.chyou.autocode.modules.datasource.selector.SqlContextBuilder;

import java.util.Map;
import java.util.Set;

/**
 * Mysql sqlContext生成器
 * Created by Administrator on 2016/9/22.
 */
public class MysqlSqlContextBuilder extends SqlContextBuilder {

    public MysqlSqlContextBuilder(DataBaseConfig dataBaseConfig) {
        super(dataBaseConfig);
    }

    /**
     * 要执行的sql语句，表结构语句
     * @param tableName
     * @return
     */
    @Override
    protected String getTableDetailSQL(String tableName) {
        return "DESC " + tableName;
    }

    /*
     * 封装字段定义信息
     * [{FIELD=dc_id, EXTRA=auto_increment, KEY=PRI, NULL=NO, DEFAULT=null, TYPE=int(11)}
     * , {FIELD=name, EXTRA=, KEY=, NULL=YES, DEFAULT=null, TYPE=varchar(20)}
     * , {FIELD=driver_class, EXTRA=, KEY=, NULL=YES, DEFAULT=null, TYPE=varchar(50)}
     * , {FIELD=jdbc_url, EXTRA=, KEY=, NULL=YES, DEFAULT=null, TYPE=varchar(100)}
     * , {FIELD=username, EXTRA=, KEY=MUL, NULL=YES, DEFAULT=null, TYPE=varchar(50)}
     * , {FIELD=password, EXTRA=, KEY=, NULL=YES, DEFAULT=null, TYPE=varchar(50)}
     * , {FIELD=back_user, EXTRA=, KEY=, NULL=YES, DEFAULT=null, TYPE=varchar(20)}]
     */
    @Override
    protected ColumnDefinition buildColumnDefinition(Map<String, Object> rowMap){
        Set<String> columnSet = rowMap.keySet();

        for (String columnInfo : columnSet) {
            rowMap.put(columnInfo.toUpperCase(), rowMap.get(columnInfo));
        }

        ColumnDefinition columnDefinition = new ColumnDefinition();

        columnDefinition.setColumnName((String)rowMap.get("FIELD"));

        boolean isIdentity = "auto_increment".equalsIgnoreCase((String)rowMap.get("EXTRA"));
        columnDefinition.setIsIdentity(isIdentity);

        boolean isPk = "PRI".equalsIgnoreCase((String)rowMap.get("KEY"));
        columnDefinition.setIsPk(isPk);

        String type = buildType((String)rowMap.get("TYPE"));
        columnDefinition.setType(type);

        return columnDefinition;
    }

    private String buildType(String type){
        if (StringUtils.hasText(type)) {
            int index = type.indexOf("(");
            if (index > 0) {
                return type.substring(0, index).toUpperCase();
            }
            return type;
        }
        return "varchar";
    }
}
