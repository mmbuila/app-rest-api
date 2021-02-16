package org.chembotula.app.services;

import java.util.List;

import org.chembotula.app.dto.AppUserBean;

public interface AppUserService {

	List<AppUserBean> getAppUsers();
	AppUserBean getAppUserById(Long userId);
	AppUserBean create(AppUserBean appUserBean);
	void delete(Long id);
	AppUserBean update(AppUserBean appUserBean);
}
