package com.atpl.kvk.service.profile;

import java.util.List;

import com.atpl.kvk.model.profile.ProfileModel;
import com.atpl.kvk.response.Response;

public interface ProfileService {
	public Response saveProfile(ProfileModel profileModel) throws Exception;
	public List<ProfileModel> getProfileList() throws Exception;
    public Response updateProfile(ProfileModel  profileModel)throws Exception;
	public Response deleteProfile(int profileId)throws Exception;
	public ProfileModel getProfile(int profileId) throws Exception;
	public ProfileModel getEmailId(ProfileModel profileModel) throws Exception;
	public Response updatePassword(ProfileModel  profileModel)throws Exception;
	


}
