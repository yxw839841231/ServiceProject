package com.tudou.gen.server.util;

import com.tudou.common.util.StringUtil;
import com.tudou.gen.dao.model.GenTableColumn;
import com.tudou.gen.server.modelvalid.GenTableColumnValid;
import org.apache.commons.lang.StringUtils;

public class GenUtils {

	/**
	 * 初始化列属性字段
	 * @param genTable
	 */
	public static void initColumnField(GenTableColumnValid genTable){
		for (GenTableColumn column : genTable.getGenTableColumns()){

			// 如果是不是新增列，则跳过。
			if (StringUtils.isNotBlank(column.getId())){
				continue;
			}

			// 设置字段说明
			if (StringUtils.isBlank(column.getComments())){
				column.setComments(column.getName());
			}

			// 设置java类型
			if (StringUtils.startsWithIgnoreCase(column.getJdbcType(), "CHAR")
					|| StringUtils.startsWithIgnoreCase(column.getJdbcType(), "VARCHAR")
					|| StringUtils.startsWithIgnoreCase(column.getJdbcType(), "NARCHAR")){
				column.setJavaType("String");
			}else if (StringUtils.startsWithIgnoreCase(column.getJdbcType(), "DATETIME")
					|| StringUtils.startsWithIgnoreCase(column.getJdbcType(), "DATE")
					|| StringUtils.startsWithIgnoreCase(column.getJdbcType(), "TIMESTAMP")){
				column.setJavaType("java.util.Date");
				column.setShowType("dateselect");
			}else if (StringUtils.startsWithIgnoreCase(column.getJdbcType(), "BIGINT")
					|| StringUtils.startsWithIgnoreCase(column.getJdbcType(), "NUMBER")){
				// 如果是浮点型
				String[] ss = StringUtils.split(StringUtils.substringBetween(column.getJdbcType(), "(", ")"), ",");
				if (ss != null && ss.length == 2 && Integer.parseInt(ss[1])>0){
					column.setJavaType("Double");
				}
				// 如果是整形
				else if (ss != null && ss.length == 1 && Integer.parseInt(ss[0])<=10){
					column.setJavaType("Integer");
				}
				// 长整形
				else{
					column.setJavaType("Long");
				}
			}

			// 设置java字段名
			column.setJavaField(StringUtil.toCamelCase(column.getName()));

			// 是否是主键
			column.setIsPk(genTable.getPkList().contains(column.getName())?"1":"0");

			// 插入字段
			if (StringUtils.isBlank(column.getIsInsert())){
				column.setIsInsert("1");
			}

			if (StringUtils.isBlank(column.getQueryType())){
				column.setQueryType("like");
			}

			if (StringUtils.isBlank(column.getShowType())){
				column.setShowType("input");
			}

			// 查询字段
			if (StringUtils.isBlank(column.getIsQuery())){
				column.setIsQuery("1");
			}

			// 编辑字段
			if (!StringUtils.equalsIgnoreCase(column.getName(), "id")
					&& !StringUtils.equalsIgnoreCase(column.getName(), "create_by")
					&& !StringUtils.equalsIgnoreCase(column.getName(), "create_date")
					&& !StringUtils.equalsIgnoreCase(column.getName(), "del_flag")){
				column.setIsEdit("1");
			}

//			// 列表字段
//			if (StringUtils.equalsIgnoreCase(column.getName(), "name")
//					|| StringUtils.equalsIgnoreCase(column.getName(), "title")
//					|| StringUtils.equalsIgnoreCase(column.getName(), "remarks")
//					|| StringUtils.equalsIgnoreCase(column.getName(), "update_date")){
//				column.setIsList("1");
//			}
//
//			// 查询字段
//			if (StringUtils.equalsIgnoreCase(column.getName(), "name")
//					|| StringUtils.equalsIgnoreCase(column.getName(), "title")){
//				column.setIsQuery("1");
//			}
//
//			// 查询字段类型
//			if (StringUtils.equalsIgnoreCase(column.getName(), "name")
//					|| StringUtils.equalsIgnoreCase(column.getName(), "title")){
//				column.setQueryType("like");
//			}

			// 设置特定类型和字段名
			// 用户
//			if (StringUtils.startsWithIgnoreCase(column.getName(), "user_id")){
//				column.setJavaType(User.class.getName());
//				column.setJavaField(column.getJavaField().replaceAll("Id", ".id|name"));
//				column.setShowType("userselect");
//			}
//			// 部门
//			else if (StringUtils.startsWithIgnoreCase(column.getName(), "office_id")){
//				column.setJavaType(Office.class.getName());
//				column.setJavaField(column.getJavaField().replaceAll("Id", ".id|name"));
//				column.setShowType("officeselect");
//			}
//			// 区域
//			else if (StringUtils.startsWithIgnoreCase(column.getName(), "area_id")){
//				column.setJavaType(Area.class.getName());
//				column.setJavaField(column.getJavaField().replaceAll("Id", ".id|name"));
//				column.setShowType("areaselect");
//			}
//			// 创建者、更新者
//			else if (StringUtils.startsWithIgnoreCase(column.getName(), "create_by")
//					|| StringUtils.startsWithIgnoreCase(column.getName(), "update_by")){
//				column.setJavaType(User.class.getName());
//				column.setJavaField(column.getJavaField());
//			}
			// 创建时间、更新时间
			else if (StringUtils.startsWithIgnoreCase(column.getName(), "create_date")
					|| StringUtils.startsWithIgnoreCase(column.getName(), "update_date")){
				column.setShowType("dateselect");
			}
			// 备注、内容
			else if (StringUtils.equalsIgnoreCase(column.getName(), "remarks")
					|| StringUtils.equalsIgnoreCase(column.getName(), "content")){
				column.setShowType("textarea");
			}
			// 父级ID
			else if (StringUtils.equalsIgnoreCase(column.getName(), "parent_id")){
				column.setJavaType("This");
				column.setJavaField("parent.id|name");
				column.setShowType("treeselect");
			}
			// 所有父级ID
			else if (StringUtils.equalsIgnoreCase(column.getName(), "parent_ids")){
				column.setQueryType("like");
			}
			// 删除标记
			else if (StringUtils.equalsIgnoreCase(column.getName(), "del_flag")){
				column.setShowType("radiobox");
			}
		}
	}




}
