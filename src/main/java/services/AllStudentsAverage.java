package services;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Alternative;
import java.util.Random;

@ApplicationScoped
@Alternative
public class AllStudentsAverage implements NumberGenerator {

    public Double generateAverage() {
        try {
            Thread.sleep(300); // Simulate intensive work
        } catch (InterruptedException e) {
        }
        Random r = new Random();

        int rangeMin = 1;
        int rangeMax = 10;

        return (double)rangeMin + (rangeMax - rangeMin) * r.nextDouble();
    }

}
