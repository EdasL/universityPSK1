package services;

import javax.enterprise.inject.Specializes;

@Specializes
public class DateService extends DateTimeService {

    public String date() {
        return java.time.LocalDate.now().toString();
    }
}
