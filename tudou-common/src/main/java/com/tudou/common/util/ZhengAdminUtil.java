package com.tudou.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.web.context.ServletContextAware;

import javax.servlet.ServletContext;

/**
 * 启动解压tudouAdmin-x.x.x.jar到resources目录
 */
public class ZhengAdminUtil implements InitializingBean, ServletContextAware {

    private static Logger _log = LoggerFactory.getLogger(ZhengAdminUtil.class);

    @Override
    public void afterPropertiesSet() throws Exception {

    }

    @Override
    public void setServletContext(ServletContext servletContext) {
        _log.info("===== 开始解压tudou-admin =====");
        String version = PropertiesFileUtil.getInstance().get("tudou-admin.version");
        _log.info("tudou-admin.jar 版本: {}", version);
        String jarPath = servletContext.getRealPath("/WEB-INF/lib/tudou-admin-" + version + ".jar");
        _log.info("tudou-admin.jar 包路径: {}", jarPath);
        String resources = servletContext.getRealPath("/") + "/resources/tudou-admin";
        _log.info("tudou-admin.jar 解压到: {}", resources);
        JarUtil.decompress(jarPath, resources);
        _log.info("===== 解压tudou-admin完成 =====");
    }

}
