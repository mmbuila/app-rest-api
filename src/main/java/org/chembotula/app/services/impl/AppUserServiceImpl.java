package org.chembotula.app.services.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.chembotula.app.dao.AppUserRepository;
import org.chembotula.app.dto.AppUserBean;
import org.chembotula.app.exception.ServiceException;
import org.chembotula.app.models.AppUser;
import org.chembotula.app.services.AppUserService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor=ServiceException.class)
public class AppUserServiceImpl implements AppUserService {
	
	private final AppUserRepository appUserRepository;
	private final ModelMapper modelMapper;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;

	public AppUserServiceImpl(AppUserRepository appUserRepository, ModelMapper modelMapper,
			BCryptPasswordEncoder bCryptPasswordEncoder) {
		this.appUserRepository = appUserRepository;
		this.modelMapper = modelMapper;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}

	@Override
	public List<AppUserBean> getAppUsers() {
		List<AppUserBean> appUsersBean = new ArrayList<>();
		Iterator<AppUser> iterator = appUserRepository.findAll().iterator();
		while(iterator.hasNext()) {
			appUsersBean.add(convertToDto(iterator.next()));
		}
		return appUsersBean;
	}

	@Override
	public AppUserBean getAppUserById(Long userId) {
		return convertToDto(appUserRepository.findById(userId)
				.orElseThrow(() -> new ServiceException(HttpStatus.NOT_FOUND.value(),"Not found User for ID [" + userId +"]")));
	}

	@Override
	public AppUserBean create(AppUserBean appUserBean) {
		appUserBean.setPassword(bCryptPasswordEncoder.encode(appUserBean.getPassword()));
		AppUser pAppUser = appUserRepository.save(convertToEntity(appUserBean));
		return convertToDto(pAppUser);
	}

	@Override
	public void delete(Long id) {
		AppUser appUser = appUserRepository.findById(id)
				.orElseThrow(() -> new ServiceException(HttpStatus.NOT_FOUND.value(),"Not found User for ID [" + id +"]"));
		appUserRepository.delete(appUser);
	}
	
	private AppUserBean convertToDto(AppUser appUser) {
		return modelMapper.map(appUser, AppUserBean.class);
	}
	
	private AppUser convertToEntity(AppUserBean appUserBean) {
		return modelMapper.map(appUserBean, AppUser.class);
	}

	@Override
	public AppUserBean update(AppUserBean appUserBean) {
		AppUser appUser = appUserRepository.findById(appUserBean.getId())
				.orElseThrow(() -> new ServiceException(HttpStatus.NOT_FOUND.value(),"Not found User for ID [" + appUserBean.getId() +"]"));
		
		String oldPassword = appUser.getPassword();
		String currentPassword = appUserBean.getPassword();
		if(!oldPassword.equals(currentPassword)) {
			appUserBean.setPassword(bCryptPasswordEncoder.encode(appUserBean.getPassword()));
		}
		AppUser pAppUser = appUserRepository.save(convertToEntity(appUserBean));
		return convertToDto(pAppUser);
	}
}
