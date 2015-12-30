package by.roman.ventskus.rest;

import by.roman.ventskus.dao.FlatDao;
import by.roman.ventskus.entity.Flat;
import by.roman.ventskus.service.Loader;
import by.roman.ventskus.service.OnlinerLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Roman Ventskus on 30.12.2015.
 */
@RestController
@RequestMapping(value = "/flat")
public class FlatResource {

    @Autowired
    private FlatDao flatDao;

    @Autowired
    private OnlinerLoader onlinerLoader;

    @RequestMapping(value = "/find", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Flat> find() {
        //return flatDao.findAll();
        return onlinerLoader.load();
    }

}
