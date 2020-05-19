package services;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Alternative;
import java.util.concurrent.ThreadLocalRandom;

@ApplicationScoped
@Alternative
public class AllUniversitiesCount implements NumberGenerator {

    public int generateUniversitiesCount() {
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
        }
        int min = 1000000;

        return ThreadLocalRandom.current().nextInt(min);
    }

}
