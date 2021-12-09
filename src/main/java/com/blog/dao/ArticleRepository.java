package com.blog.dao;

import com.blog.models.Article;
import org.springframework.data.repository.CrudRepository;

/*для работы с таблицей создали интерфейс, который наследуется от интерфейса круд,
где реализованы процессы добавления, удаления, обновления и т.д.
Передали ему нашу модель и тип поля id*/
public interface ArticleRepository extends CrudRepository<Article, Long> {

}
