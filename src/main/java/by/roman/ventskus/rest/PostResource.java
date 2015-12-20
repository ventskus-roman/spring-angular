package by.roman.ventskus.rest;

import by.roman.ventskus.dao.PostDao;
import by.roman.ventskus.entity.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Roman Ventskus on 20.12.2015.
 */
@RestController
@RequestMapping(value = "post")
public class PostResource {

    @Autowired
    private PostDao postDao;

    @RequestMapping(value = "list", method = RequestMethod.GET)
    public List<Post> getList() {
        List<Post> posts = postDao.findAll();
        return posts;
    }

}
