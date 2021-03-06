package com.tudou.pcweb.rpc.service.impl;

import com.tudou.common.annotation.BaseService;
import com.tudou.common.base.BaseServiceImpl;
import com.tudou.pcweb.dao.mapper.UcenterOauthMapper;
import com.tudou.pcweb.dao.model.UcenterOauth;
import com.tudou.pcweb.dao.model.UcenterOauthExample;
import com.tudou.pcweb.rpc.api.UcenterOauthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
* UcenterOauthService实现
* Created by tudou on 2017/9/12.
*/
@Service
@Transactional
@BaseService
public class UcenterOauthServiceImpl extends BaseServiceImpl<UcenterOauthMapper, UcenterOauth, UcenterOauthExample> implements UcenterOauthService {

    private static Logger _log = LoggerFactory.getLogger(UcenterOauthServiceImpl.class);

    @Autowired
    UcenterOauthMapper ucenterOauthMapper;

}