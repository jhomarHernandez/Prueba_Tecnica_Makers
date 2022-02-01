package starter.tasks;

import net.serenitybdd.core.Serenity;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class TakesAScreenShotWithSelenium implements Task {

    public final String title;

    public TakesAScreenShotWithSelenium(String title) {
        this.title = title;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        File screenshot = ((TakesScreenshot) Serenity.getDriver()).getScreenshotAs(OutputType.FILE);
        try {
            Serenity.recordReportData().withTitle(title).downloadable().fromFile(Paths.get(screenshot.getPath()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Performable withTitle(String title){
        return instrumented(TakesAScreenShotWithSelenium.class, title);
    }
}
