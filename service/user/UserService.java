package com.atpl.kvk.service.user;

import java.util.List;

import com.atpl.kvk.model.user.UserModel;
import com.atpl.kvk.response.Response;

public interface UserService {
	public Response saveUser(UserModel userModel) throws Exception;
	public List<UserModel> getUserList() throws Exception;
	

}
