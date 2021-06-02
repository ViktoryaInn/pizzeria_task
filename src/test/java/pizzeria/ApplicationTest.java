package pizzeria;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pizzeria.controller.ShowController;

@SpringBootTest
public class ApplicationTest {

    @Autowired
    private ShowController controller;

    @Test
    public void test(){

    }
}
