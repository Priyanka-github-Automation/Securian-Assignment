package classes.page.assignment.securian;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;


public class Base {
	
	public static WebDriver driver;
	
	
	public WebDriver browserInitialization(String browsername, String url, String chromepath, String geckopath) {
		
		
		switch (browsername) {
		
		case "firefox":
			System.setProperty("webdriver.gecko.driver", geckopath);
			driver = new FirefoxDriver();
			break;
			
		case "chrome":
			System.setProperty("webdriver.chrome.driver", chromepath);
			driver = new ChromeDriver();
			break;
		}
		
		
		driver.get("https://www.securian.com/insights-tools/retirement-calculator.html");
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		return driver;
		
	}

}
