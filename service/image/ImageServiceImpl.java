package com.atpl.kvk.service.image;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.atpl.kvk.config.KVKProperties;
import com.atpl.kvk.constant.StatusCode;
import com.atpl.kvk.dao.image.ImageDAO;
import com.atpl.kvk.dao.session.SessionDAO;
import com.atpl.kvk.domain.image.ImageDomain;
import com.atpl.kvk.mapper.image.ImageMapper;
import com.atpl.kvk.model.image.ImageModel;
import com.atpl.kvk.response.Response;
import com.atpl.kvk.utils.FileUtils;

@Service("ImageService")
public class ImageServiceImpl implements ImageService {
	private static final Logger logger = LoggerFactory.getLogger(ImageServiceImpl.class);

	@Autowired
	ImageDAO imageDao;

	@Autowired
	ImageMapper imageMapper;

	@Autowired
	SessionDAO sessionDAO;
	
	@Autowired
    KVKProperties kvkProperties;
	
	
	@Override
	public Response saveImages(ImageModel imageModel, MultipartFile file) throws Exception {
		Response response = new Response();
		try {
			ImageDomain imageDomain = new ImageDomain();
			BeanUtils.copyProperties(imageModel, imageDomain);
			imageDomain.setImageName(file.getOriginalFilename());
			imageDomain.setImageType(file.getContentType());
			
			imageDomain.setImagePath(kvkProperties.getImagePath() + "/" + file.getOriginalFilename());
			FileUtils.createDir(kvkProperties.getImagePath());
			try {
				byte[] bytes = file.getBytes();
				Path path = Paths.get(kvkProperties.getImagePath() + "/" + file.getOriginalFilename());
				Files.write(path, bytes);
				
			} catch (FileNotFoundException e) {
				logger.error("FileNotFoundException saveFranchise in ImageServiceImpl" + e.getMessage());
				response.setStatus(StatusCode.ERROR.name());
			} catch (Exception e) {
				logger.error("Exception saveFranchise in ImageServiceImpl" + e.getMessage());
				response.setStatus(StatusCode.ERROR.name());
			}
			response = imageDao.saveImage(imageDomain);
		} catch (IOException e) {
			logger.error("IOException saveFranchise in ImageServiceImpl" + e.getMessage());
			response.setStatus(StatusCode.ERROR.name());
		}  catch (Exception ex) {
			logger.error("Exception saveFranchise in ImageServiceImpl" + ex.getMessage());
			response.setStatus(StatusCode.ERROR.name());
		}
		return response;
	}

	@Override
	public List<ImageModel> getImageList(String articleId) throws Exception {
		try {
			List<ImageDomain> articleDomain = imageDao.getImageList(articleId);
			return imageMapper.entityList(articleDomain);
		} catch (Exception ex) {
			logger.info("Exception getImageList:", ex);
		}
		return null;
	}


	

		@Override
		public Response updateImage(ImageModel  imageModel) throws Exception {
			try
			{
				ImageDomain imageDomain = new ImageDomain();
			BeanUtils.copyProperties(imageModel, imageDomain);
			Response response = imageDao.updateImage(imageDomain);
			return response;
		} catch (Exception ex) {
			logger.info("Exception Service:" + ex.getMessage());
			return null;
		}
			
		}
}
		
