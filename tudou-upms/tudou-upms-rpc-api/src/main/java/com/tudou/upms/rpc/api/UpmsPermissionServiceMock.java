package com.tudou.upms.rpc.api;

import com.tudou.common.base.BaseServiceMock;
import com.tudou.upms.dao.mapper.UpmsPermissionMapper;
import com.tudou.upms.dao.model.UpmsPermission;
import com.tudou.upms.dao.model.UpmsPermissionExample;

/**
* 降级实现UpmsPermissionService接口
* Created by tudou on 2017/5/27.
*/
public class UpmsPermissionServiceMock extends BaseServiceMock<UpmsPermissionMapper, UpmsPermission, UpmsPermissionExample> implements UpmsPermissionService {

}