/**
 * Project		: BookMarkerWeb
 * FileName		: UserService.java
 * Package		: com.khan.bookmarker.service
 * 
 * @brief		: 
 * @author		: KHAN
 * @date		: 2013. 6. 25. 오전 10:12:09
 */
package com.khan.opensns.api.service.impl;

import java.io.IOException;
import java.util.Date;

import javax.persistence.EntityExistsException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.khan.opensns.api.dao.UserDao;
import com.khan.opensns.api.dao.UserInfoDao;
import com.khan.opensns.model.User;
import com.khan.opensns.model.UserInfo;
import com.khan.opensns.service.FileSaveService;
import com.khan.opensns.service.ImageService;
import com.khan.opensns.service.RoleService;
import com.khan.opensns.service.UserExistsException;
import com.khan.opensns.service.UserNotFoundException;
import com.khan.opensns.service.UserService;
import com.khan.opensns.util.KeyGenerator;
import com.khan.opensns.vo.ImageVo;
import com.khan.opensns.vo.UserVo;

/**
 * <PRE>
 * @class	: com.khan.bookmarker.service.UserService
 * @file	: UserService.java
 * @brief	: 
 * @author	: KHAN
 * @date	: 2013. 6. 25. 오전 10:12:09
 * </PRE>
 */
@Service(value="userService")
public class UserServiceImpl implements UserService {
	
	private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

	@Value(value="#{setting['password.algorithm']}")
	private String passwordAlgorithm;

	@Autowired
	private UserDao userDao;
	
	@Autowired
	private UserInfoDao userInfoDao;
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private ImageService imageService;
	
	@Autowired
	private FileSaveService fileSaveService;

	@Value(value="#{setting['image.profile']}")
	private String PROFILE_DIR;

	@Value(value="#{setting['image.profile.thumb']}")
	private String PROFILE_THUMB_DIR;

	@Value(value="#{setting['image.background']}")
	private String BG_DIR;

	@Value(value="#{setting['image.background.thumb']}")
	private String BG_THUMB_DIR;
	
	public User getUser(Long id) {
		return userDao.getById(id);
	}
	
	/**
	 * @param username
	 * @return
	 * @throws UsernameNotFoundException
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserDetails user = userDao.loadByUsername(username);
		if (user == null) 
			throw new UsernameNotFoundException("User(" + username + ") not registered!");
		
		return user;
	}
	
	public User loadUserByName(String name) throws UserNotFoundException {
		User user = userDao.loadByName(name);
		if (user == null)
			throw new UserNotFoundException("User(" + name + ") not registered!");
		
		return user;
	}
	
	public User loadUserByAuthKey(String authKey) throws UserNotFoundException {
		User user = userDao.loadByAuthKey(authKey);
		if (user == null)
			throw new UserNotFoundException("User(" + authKey + ") not registered!");
		
		return user;
	}

	@Transactional
	public User saveUser(User user) throws UserExistsException {
		try {
			return userDao.save(user);
        } catch (DataIntegrityViolationException e) {
            e.printStackTrace();
            log.warn(e.getMessage());
            throw new UserExistsException("User '" + user.getUsername() + "' already exists!");
        } catch (EntityExistsException e) { // needed for JPA
            e.printStackTrace();
            log.warn(e.getMessage());
            throw new UserExistsException("User '" + user.getUsername() + "' already exists!");
        }
	}

	@Transactional
	public User saveUser(UserVo userVo) throws UserExistsException {
		boolean isNewUser = false;
		User user = null;
		UserInfo userInfo = null;
		try {
			user = (User)loadUserByUsername(userVo.getUsername());
			userInfo = user.getUserInfo();
		} catch (UsernameNotFoundException e) {
			isNewUser = true;
			user = new User();
			String authKey = KeyGenerator.createAuthKey();
			user.setAuthKey(authKey);
			userInfo = new UserInfo();
		}
		
		if (isNewUser) {
			user.setUsername(userVo.getUsername());
			user.addRole(roleService.getRole("ROLE_USER"));
			user.setEnabled(true);
			user.setLocked(false);
			user.setCreateDate(new Date());
		} else {
			user.setUpdateDate(new Date());
		}
		
		user.setPassword(userVo.getPassword());
		user.setName(userVo.getName());
		
		if (isNewUser) { 
			user = saveUser(user);;
			userInfo.setId(user.getId());
			userInfo.setPush(true);
			userInfo.setPause(false);
			userInfo.setSearchEnable(true);
		} else {
			userInfo.setPush(userVo.isPush());
			userInfo.setPause(userVo.isPause());
			userInfo.setSearchEnable(userVo.isSearchEnable());
			userInfo.setUpdateDate(new Date());
		}
		
		userInfo.setBackImage(userVo.getBackImage());
		userInfo.setBackImageThumb(userVo.getBackImageThumb());
		userInfo.setProfileImage(userVo.getProfileImage());
		userInfo.setProfileImageThumb(userVo.getProfileImageThumb());
		userInfo.setUser(user);
		
		user.setUserInfo(userInfoDao.save(userInfo));
		
		return saveUser(user);
	}

	@Override
	@Transactional
	public void removeUser(Long id) throws UserNotFoundException {
		User user = getUser(id);
		if (user != null) {
			user.setEnabled(false);
			userDao.save(user);
		} else {
			throw new UserNotFoundException("User(" + id + ") not registered!");
		}
	}
	
	@Override
	@Transactional
	public void removeUser(String username) throws UserNotFoundException {
		User user = (User)loadUserByUsername(username);
		if (user != null) {
			user.setEnabled(false);
			userDao.save(user);
		} else {
			throw new UserNotFoundException("User(" + username + ") not registered!");
		}
	}
	
	@Override
	@Transactional
	public User saveUserProfile(User user, MultipartFile image) throws IOException {
		UserInfo userInfo = user.getUserInfo();
		
		String filename = user.getId() + "_" + new Date().getTime() + ".png";
		
		ImageVo img = imageService.imageSave(PROFILE_DIR, PROFILE_THUMB_DIR, filename, image);
		
		userInfo.setProfileImage(img.getImageSrc());
		userInfo.setProfileImageThumb(img.getThumbSrc());
		
		userInfoDao.save(userInfo);
		user.setUserInfo(userInfo);
		
		return user;
	}
	
	@Override
	@Transactional
	public User saveUserBackground(User user, MultipartFile image) throws IOException {
		
		UserInfo userInfo = user.getUserInfo();
		
		String filename = user.getId() + "_" + new Date().getTime() + ".png";
		
		ImageVo img = imageService.imageSave(BG_DIR, BG_THUMB_DIR, filename, image);
		
		userInfo.setProfileImage(img.getImageSrc());
		userInfo.setProfileImageThumb(img.getThumbSrc());
		
		userInfoDao.save(userInfo);
		user.setUserInfo(userInfo);
		
		return user;
	}
	
	
}
