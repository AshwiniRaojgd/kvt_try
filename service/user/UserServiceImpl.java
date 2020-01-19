package com.atpl.kvk.service.user;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atpl.kvk.dao.session.SessionDAO;
import com.atpl.kvk.dao.user.UserDAO;
import com.atpl.kvk.domain.user.UserDomain;
import com.atpl.kvk.mapper.user.UserMapper;
import com.atpl.kvk.model.user.UserModel;
import com.atpl.kvk.response.Response;

@Service("UserService")
public class UserServiceImpl implements UserService{
	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	@Autowired
	UserDAO userDAO;

	@Autowired
	UserMapper userMapper;

	@Autowired
	SessionDAO sessionDAO;
	

	public UserServiceImpl() {
		// Constructor
	}
	@Override
	public Response saveUser(UserModel userModel) throws Exception {
		try {
			UserDomain userDomain = new UserDomain();
			BeanUtils.copyProperties(userModel, userDomain);

			Response response = userDAO.saveUser(userDomain);
			return response;

		} catch (Exception e) {
			logger.info("Exception Service" + e.getMessage());
			return null;
		}

		
	}
	
		
		
	
	public List<UserModel> getUserList() throws Exception {
		try {
			List<UserDomain> userDomain= userDAO.getUserList();
			return userMapper.entityList(userDomain);
		} catch (Exception ex) {
			logger.info("Exception get insurance plan info:", ex);
			return null;
		}
	

}
}
