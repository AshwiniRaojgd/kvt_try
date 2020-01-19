package com.atpl.kvk.service.image;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.atpl.kvk.model.image.ImageModel;
import com.atpl.kvk.response.Response;

public interface ImageService {
	public Response saveImages(ImageModel imageModel, MultipartFile file) throws Exception;
	List<ImageModel> getImageList(String articleId) throws Exception;
    public Response updateImage(ImageModel  imageModel)throws Exception;
}
