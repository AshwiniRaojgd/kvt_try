package com.atpl.kvk.service.profile;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atpl.kvk.constant.StatusCode;
import com.atpl.kvk.dao.profile.ProfileDAO;
import com.atpl.kvk.dao.session.SessionDAO;
import com.atpl.kvk.domain.profile.ProfileDomain;
import com.atpl.kvk.mapper.profile.ProfileMapper;
import com.atpl.kvk.model.profile.ProfileModel;
import com.atpl.kvk.response.Response;
import com.atpl.kvk.utils.Validation;

@Service("ProfileService")
public class ProfileServiceImpl implements ProfileService {
	private static final Logger logger = LoggerFactory.getLogger(ProfileServiceImpl.class);

	@Autowired
	ProfileDAO profileDAO;

	@Autowired
	ProfileMapper profileMapper;

	@Autowired
	SessionDAO sessionDAO;
	

	public ProfileServiceImpl() {
		// Constructor
	}
	
	@Override
	public Response saveProfile(ProfileModel profileModel) throws Exception {
		try {
			ProfileDomain profileDomain = new ProfileDomain();
			
			Validation validation = new Validation();
			
			Response response =new Response();
			
			boolean userNa =validation.isUserNameValidate(profileModel.getUserName());
			boolean mobileNo = validation.isMobileNoValidate(profileModel.getMobileNumber());
			boolean email = validation.isEmailValidate(profileModel.getEmailId());
			
			if (email == true && userNa == true && mobileNo == true) {
				BeanUtils.copyProperties(profileModel, profileDomain);
				response = profileDAO.saveProfile(profileDomain);
			}else if (email == false) {
				logger.info("please enter proper Email");
				response.setStatus(StatusCode.ERROR.name());
				response.setMessage("please enter proper Email");
			}
			
			else if (userNa == false) {
				logger.info("please enter proper UserName");
				response.setStatus(StatusCode.ERROR.name());
				response.setMessage("please enter proper UserName");
			}

			else if (mobileNo == false) {
				logger.info("please enter proper MobileNo");
				response.setStatus(StatusCode.ERROR.name());
				response.setMessage("please enter proper Mobileno");
			}
			
			return response;
		} catch (Exception e) {
			logger.info("Exception Service" + e.getMessage());
			return null;
		}
		
	}	

	@Override
	public List<ProfileModel> getProfileList() throws Exception {
		try {
			List<ProfileDomain> profileDomain= profileDAO.getProfileList();
			return profileMapper.entityList(profileDomain);
		} catch (Exception e) {
			logger.info("Exception get profile info:", e);
			return null;
		}
		
	}

	@Override
	public Response updateProfile(ProfileModel profileModel) throws Exception {
		try
		{
			ProfileDomain profileDomain = new ProfileDomain();
		BeanUtils.copyProperties(profileModel, profileDomain);
		Response response = profileDAO.updateProfile(profileDomain);
		return response;
	} catch (Exception e) {
		logger.info("Exception Service:" + e.getMessage());
		return null;
	}
		
	}

	@Override
	public Response deleteProfile(int profileId) throws Exception {
		try
		{
			ProfileDomain profileDomain = new ProfileDomain();
			ProfileModel profileModel = new ProfileModel();
		BeanUtils.copyProperties(profileModel, profileDomain);
		Response response = profileDAO.deleteProfile(profileId);
		return response;
	} catch (Exception e) {
		logger.info("Exception Service:" + e.getMessage());
		return null;
	}
		
	}

	@Override
	public ProfileModel getProfile(int profileId) throws Exception {
		try {
			ProfileDomain profileDomain = profileDAO.getProfile(profileId);
			ProfileModel profileModel = new ProfileModel();
			if (profileDomain == null)
				return null;
			BeanUtils.copyProperties(profileDomain, profileModel);
			return profileModel;
		} catch (Exception e) {
			logger.info("Exception get Profile:", e);
			return null;
		}
	}

	@Override
	public ProfileModel getEmailId(ProfileModel profileModel) throws Exception {
		try {
			ProfileDomain profileDomain = new ProfileDomain();
			BeanUtils.copyProperties(profileModel, profileDomain);
			profileDomain = profileDAO.getEmailId(profileDomain);
			if (profileDomain == null)
				return null;
			BeanUtils.copyProperties(profileDomain, profileModel);
			return profileModel;
		} catch (Exception e) {
			logger.info("Exception getEmailId in ProfileServiceImpl:"+ e.getMessage());
			return null;
		}
	}
	@Override
	public Response updatePassword(ProfileModel profileModel) throws Exception {
		try
		{
			ProfileDomain profileDomain = new ProfileDomain();
		BeanUtils.copyProperties(profileModel, profileDomain);
		Response response = profileDAO.updatePassword(profileDomain);
		return response;
	} catch (Exception e) {
		logger.info("Exception Service:" + e.getMessage());
		return null;
	}
		
	}
}