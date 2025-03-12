package cl.genesiscastillo.previred;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ApplicationTest {

    @Test
    void testMain() {
            Application.main(new String[]{});
    }
}
