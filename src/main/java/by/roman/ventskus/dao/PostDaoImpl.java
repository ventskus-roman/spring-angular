package by.roman.ventskus.dao;

import by.roman.ventskus.entity.Post;
import org.springframework.stereotype.Repository;

/**
 * Created by Roman Ventskus on 20.12.2015.
 */
@Repository
public class PostDaoImpl extends BaseDaoImpl<Post> implements PostDao {

    public PostDaoImpl() {
        super(Post.class);
    }
}
