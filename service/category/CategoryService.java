package com.atpl.kvk.service.category;

import java.util.List;

import com.atpl.kvk.model.category.CategoryModel;
import com.atpl.kvk.response.Response;

public interface CategoryService {
	
	public Response saveCategory(CategoryModel categoryModel) throws Exception;
	public List<CategoryModel> getCategoryList() throws Exception;
	public Response updateCategory(CategoryModel categoryModel)throws Exception;
	public CategoryModel getCategory(int id) throws Exception;
	

}

