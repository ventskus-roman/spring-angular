package by.roman.ventskus.dao;

import by.roman.ventskus.dto.Filter;
import by.roman.ventskus.entity.Flat;

import java.util.List;

/**
 * Created by Roman Ventskus on 30.12.2015.
 */
public interface FlatDao extends BaseDao<Flat> {

    List<Flat> find(Filter filter);
}
