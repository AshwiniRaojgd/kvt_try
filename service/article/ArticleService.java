package com.atpl.kvk.service.article;

import java.util.List;

import com.atpl.kvk.model.article.ArticleModel;
import com.atpl.kvk.response.Response;

public interface ArticleService {
	public Response saveArticle(ArticleModel articleModel) throws Exception;
	public List<ArticleModel> getArticleList() throws Exception;
	public Response updateArticle(ArticleModel articleModel)throws Exception;
	public ArticleModel getArticle(int id) throws Exception;
	
}
