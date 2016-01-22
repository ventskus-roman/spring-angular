package by.roman.ventskus.service;

import by.roman.ventskus.entity.Flat;

import java.util.List;

/**
 * Created by Roman Ventskus on 01.01.2016.
 */
public interface NotificationService {

    void notify(List<Flat> flats);
}
