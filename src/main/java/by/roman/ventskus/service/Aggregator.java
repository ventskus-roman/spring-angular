package by.roman.ventskus.service;

import by.roman.ventskus.dao.FlatDao;
import by.roman.ventskus.entity.Flat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Roman Ventskus on 30.12.2015.
 */
@Service
@Transactional(propagation = Propagation.REQUIRED)
public class Aggregator {

    @Autowired
    private OnlinerLoader onlinerLoader;

    @Autowired
    private IrrLoader irrLoader;

    @Autowired
    private NotificationService notificationService;

    @Autowired
    private FlatDao flatDao;

    public void aggregate() {
        List<Flat> originalList = flatDao.findAll();
        List<Flat> flats = onlinerLoader.load();
        List<Flat> irrFlats = irrLoader.load();
        flats.addAll(irrFlats);
        flats.removeAll(originalList);
        for (Flat flat : flats) {
            flatDao.save(flat);
            //notificationService.notify(flat);
        }

    }
}
