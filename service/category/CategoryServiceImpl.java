package com.atpl.kvk.service.category;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atpl.kvk.constant.StatusCode;
import com.atpl.kvk.dao.category.CategoryDao;
import com.atpl.kvk.domain.category.CategoryDomain;
import com.atpl.kvk.mapper.category.CategoryMapper;
import com.atpl.kvk.model.category.CategoryModel;
import com.atpl.kvk.response.Response;
import com.atpl.kvk.utils.Validation;

@Service("categoryService")
public class CategoryServiceImpl implements CategoryService {
	
	private static final Logger logger = LoggerFactory.getLogger(CategoryServiceImpl.class);

	@Autowired
	CategoryDao categoryDao;

	@Autowired
	CategoryMapper categoryMapper;

	@Override
	public Response saveCategory(CategoryModel categoryModel) throws Exception {
		try {
			CategoryDomain categoryDomain = new CategoryDomain();
			Validation validation = new Validation();
			Response response = new Response();
			
			boolean categoryName = validation.isCategoryNameValidate(categoryModel.getCategoryName());
			boolean categoryDescription = validation.isCategoryDescriptionValidate(categoryModel.getCategoryDescription());
			if (categoryName == true && categoryDescription == true) {
				BeanUtils.copyProperties(categoryModel, categoryDomain);
				response = categoryDao.saveCategory(categoryDomain);
			} 
			else if (categoryName == false) {
				logger.info("Please Select Category");
				response.setStatus(StatusCode.ERROR.name());
				response.setMessage("please  Select Category");
			}
			else if (categoryDescription == false) {
				logger.info("Please enter proper source");
				response.setStatus(StatusCode.ERROR.name());
				response.setMessage("please enter  category Description");
			}
			
			return response;
			

		} catch (Exception e) {
			logger.info("Exception Service" + e.getMessage());
			return null;
		}
	}


	@Override
	public List<CategoryModel> getCategoryList() throws Exception {
		try {
			List<CategoryDomain> categoryDomain= categoryDao.getCategoryList();
			return categoryMapper.entityList(categoryDomain);
		} catch (Exception ex) {
			logger.info("Exception get category info:", ex);
			return null;
		}
		
	}


	@Override
	public Response updateCategory(CategoryModel categoryModel) throws Exception {
		try
		{
			CategoryDomain categoryDomain = new CategoryDomain();
		BeanUtils.copyProperties(categoryModel, categoryDomain);
		Response response = categoryDao.updateCategory(categoryDomain);
		return response;
	} catch (Exception ex) {
		logger.info("Exception Service:" + ex.getMessage());
		return null;
	}
		
	}


	@Override
	public CategoryModel getCategory(int id) throws Exception {
		try {
			CategoryDomain categoryDomain = categoryDao.getCategory(id);
			CategoryModel categoryModel = new CategoryModel();
			if (categoryDomain == null)
				return null;
			BeanUtils.copyProperties(categoryDomain, categoryModel);
			return categoryModel;
		} catch (Exception e) {
			logger.info("Exception getCategory:", e);
			return null;
		}
	}


}

