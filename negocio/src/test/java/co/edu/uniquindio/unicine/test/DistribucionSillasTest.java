package co.edu.uniquindio.unicine.test;

import co.edu.uniquindio.unicine.repo.DistribucionSillasRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class DistribucionSillasTest {

    @Autowired
    private DistribucionSillasRepo distribucionSillasRepo;
}
