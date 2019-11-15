package ie.mohammed.services.implementation;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ie.mohammed.dao.CategoryDao;
import ie.mohammed.model.Category;
import ie.mohammed.services.CategoryService;

/**
 * Implementing the CategoryService interface
 */

@Service("categoryService")
@Transactional
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryDao categoryDao;

	@Override
	public List<Category> findAll() {

		return categoryDao.findAll();
	}

	@Override
	public Optional<Category> findByName(String name) {

		return categoryDao.findByName(name);
	}

	@Override
	public Category save(Category category) {

		return categoryDao.save(category);
	}

	@Override
	public void deleteByName(String name) {
		categoryDao.deleteById(name);
	}

}
