package by.roman.ventskus.service;

import by.roman.ventskus.dao.FlatDao;
import by.roman.ventskus.entity.Flat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

/**
 * Created by Roman Ventskus on 30.12.2015.
 */
@Service
@Transactional(propagation = Propagation.REQUIRED)
public class Aggregator {

    @Autowired
    private OnlinerLoader onlinerLoader;

    @Autowired
    private FlatDao flatDao;

    public void aggregate() {
        List<Flat> originalList = flatDao.findAll();
        List<Flat> flats = onlinerLoader.load();
        flats.removeAll(originalList);
        for (Flat flat : flats) {
            flatDao.save(flat);
        }
    }
}
