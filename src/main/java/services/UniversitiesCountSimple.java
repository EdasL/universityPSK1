package services;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Alternative;
import java.util.concurrent.ThreadLocalRandom;

@ApplicationScoped
@Alternative
public class UniversitiesCountSimple implements NumberGenerator {
    @Override
    public int generateUniversitiesCount() {
        return ThreadLocalRandom.current().nextInt(30);
    }
}
