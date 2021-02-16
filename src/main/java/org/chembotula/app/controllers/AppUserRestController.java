package org.chembotula.app.controllers;

import java.util.List;

import org.chembotula.app.dto.AppUserBean;
import org.chembotula.app.services.AppUserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class AppUserRestController {
	
	private final AppUserService appUserService;

	public AppUserRestController(AppUserService appUserService) {
		this.appUserService = appUserService;
	}
	
	@GetMapping("/")
	public ResponseEntity<List<AppUserBean>> getAppUsersBean() {
		return ResponseEntity.ok().body(appUserService.getAppUsers());
	}
	
	@GetMapping("/{userId}")
	public ResponseEntity<AppUserBean> getAppUserBean(@PathVariable Long userId) {
		return ResponseEntity.ok().body(appUserService.getAppUserById(userId));
	}
	
	@PostMapping("/")
	public ResponseEntity<AppUserBean> create(@RequestBody AppUserBean appUserBean) {
		return ResponseEntity.ok().body(appUserService.create(appUserBean));
	}
	
	@DeleteMapping("/{userId}")
	public void delete(@PathVariable Long userId) {
		appUserService.delete(userId);
	}
}
