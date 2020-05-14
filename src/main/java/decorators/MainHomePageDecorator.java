package decorators;

import services.DateTimeService;

import javax.decorator.Decorator;
import javax.decorator.Delegate;
import javax.enterprise.inject.Any;
import javax.inject.Inject;

@Decorator
public class MainHomePageDecorator implements HomePage{

    @Inject
    @Delegate
    @Any
    MainHomePage homePage;

    @Inject
    private DateTimeService dateTimeService;

    @Override
    public String welcome() {
        return homePage.welcome().replace(":date", dateTimeService.getDate());
    }
}
