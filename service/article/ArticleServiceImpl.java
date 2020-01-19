package com.atpl.kvk.service.article;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atpl.kvk.constant.StatusCode;
import com.atpl.kvk.dao.article.ArticleDao;
import com.atpl.kvk.domain.article.ArticleDomain;
import com.atpl.kvk.mapper.article.ArticleMapper;
import com.atpl.kvk.model.article.ArticleModel;
import com.atpl.kvk.response.Response;
import com.atpl.kvk.utils.Validation;

@Service("articleService")
public class ArticleServiceImpl implements ArticleService {

	private static final Logger logger = LoggerFactory.getLogger(ArticleServiceImpl.class);

	@Autowired
	ArticleDao articleDao;

	@Autowired
	ArticleMapper articleMapper;

	@Override
	public Response saveArticle(ArticleModel articleModel) throws Exception {
		try {
			ArticleDomain articleDomain = new ArticleDomain();
			Validation validation = new Validation();
			Response response = new Response();

			boolean articleName = validation.isArticleNameValidate(articleModel.getArticleName());
			boolean source = validation.isSourceValidate(articleModel.getSource());
			boolean article = validation.isArticleValidate(articleModel.getArticle());
			
			if (articleName == true && source == true && article == true) {
				BeanUtils.copyProperties(articleModel, articleDomain);
				response = articleDao.saveArticle(articleDomain);
			} 
			else if (articleName == false) {
				logger.info("Please enter proper Article Name");
				response.setStatus(StatusCode.ERROR.name());
				response.setMessage("please enter proper article name");
			}
			else if (source == false) {
				logger.info("Please enter proper source");
				response.setStatus(StatusCode.ERROR.name());
				response.setMessage("please enter proper source");
			}
			else if (article == false) {
				logger.info("Please enter proper article");
				response.setStatus(StatusCode.ERROR.name());
				response.setMessage("please enter proper article");
			}
			return response;

		} catch (Exception e) {
			logger.info("Exception Service" + e.getMessage());
			return null;
		}
	}

	@Override
	public List<ArticleModel> getArticleList() throws Exception {
		try {
			List<ArticleDomain> articleDomain = articleDao.getArticleList();
			return articleMapper.entityList(articleDomain);
		} catch (Exception ex) {
			logger.info("Exception get article info:", ex);
			return null;
		}

	}

	@Override
	public Response updateArticle(ArticleModel articleModel) throws Exception {
		try {
			ArticleDomain articleDomain = new ArticleDomain();
			BeanUtils.copyProperties(articleModel, articleDomain);
			Response response = articleDao.updateArticle(articleDomain);
			return response;
		} catch (Exception ex) {
			logger.info("Exception Service:" + ex.getMessage());
			return null;
		}

	}

	@Override
	public ArticleModel getArticle(int id) throws Exception {
		try {
			ArticleDomain articleDomain = articleDao.getArticle(id);
			ArticleModel articleModel = new ArticleModel();
			if (articleDomain == null)
				return null;
			BeanUtils.copyProperties(articleDomain, articleModel);
			return articleModel;
		} catch (Exception e) {
			logger.info("Exception getArticle:", e);
			return null;
		}
	}

}
