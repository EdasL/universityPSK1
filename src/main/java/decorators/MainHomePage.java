package decorators;

import javax.enterprise.inject.Default;

@Default
public class MainHomePage implements HomePage {

    @Override
    public String welcome() {
        return "Today's date: :date Welcome to university management web application...";
    }
}
