import by.roman.ventskus.entity.Flat;
import by.roman.ventskus.service.OnlinerLoader;
import org.junit.Test;
import org.springframework.util.Assert;

import java.util.List;

import static org.springframework.util.Assert.*;

/**
 * Created by Roman Ventskus on 30.12.2015.
 */
public class OnlinerLoaderTest {

    @Test
    public void get() {
        OnlinerLoader loader = new OnlinerLoader();
        List<Flat> flats = loader.load();
        notEmpty(flats);
    }
}
