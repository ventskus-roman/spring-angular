package by.roman.ventskus.dao;

import by.roman.ventskus.entity.Flat;
import org.springframework.stereotype.Repository;

/**
 * Created by Roman Ventskus on 30.12.2015.
 */
@Repository
public class FlatDaoImpl extends BaseDaoImpl<Flat> implements FlatDao {

    public FlatDaoImpl() {
        super(Flat.class);
    }
}
