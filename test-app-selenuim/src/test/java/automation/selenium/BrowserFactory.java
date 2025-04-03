package automation.selenium;

import automation.enums.Browsers;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import java.io.File;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.remote.service.DriverFinder;

public class BrowserFactory {

	private static File getChromeLocation() {
		ChromeOptions options = new ChromeOptions();
		options.setBrowserVersion("stable");
		DriverFinder finder = new DriverFinder(ChromeDriverService.createDefaultService(), options);
		return new File(finder.getBrowserPath());
	}

	private static ChromeOptions getOptions() {
		ChromeOptions options = new ChromeOptions();

		options.setBinary(getChromeLocation());

		return options;
	}

	public static WebDriver launch(Browsers browser) {
		if (browser.equals(Browsers.CHROME)) {

			return new ChromeDriver();
		} else if (browser.equals(Browsers.FIREFOX)) {
			return new FirefoxDriver();
		} else if (browser.equals(Browsers.EDGE)) {
			return new EdgeDriver();
		}

		// default
		return new ChromeDriver();
	}
}
