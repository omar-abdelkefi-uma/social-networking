package web_saucedemo.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage extends BasePage {

	private static final String ERR_LOCKED_OUT = "Epic sadface: Sorry, this user has been locked out.";

	By txtUsername = By.id("input-username");
	By txtPassword = By.id("input-password");
	By btnLogin = By.id("sign-in-button");
	By lblErrMsg = By.xpath("//div[contains(@class,'error-message-container')]/h3");

	By btnLoginGoogle = By.id("google-login-button");
	By btnGithubGoogle = By.id("github-login-button");

	public LoginPage(WebDriver driver) {
		super(driver);
	}

	public boolean isAt() {
		return driver.findElement(txtUsername).isDisplayed();
	}

	public boolean isUserLockedOut() {
		String error = driver.findElement(lblErrMsg).getText();
		if (error.equals(ERR_LOCKED_OUT)) {
			return true;
		}
		return false;
	}

	public void login(String username, String password) {
		driver.findElement(txtUsername).sendKeys(username);
		driver.findElement(txtPassword).sendKeys(password);
		driver.findElement(btnLogin).click();
	}

	// does not work
	public void loginWithGoogle(String username, String password) {

		driver.findElement(btnLoginGoogle).click();

		driver.findElement(By.id("identifierId")).sendKeys("my gmail");
		driver.findElement(By.id("identifierNext")).click();

		try {
			Thread.sleep(9000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys("mypassword");
//
//		// click on next
//		driver.findElement(By.id("passwordNext")).click();
//		// click on continue
//		driver.findElement(By.id("identifierContinue")).click();
//		try {
//			Thread.sleep(2000);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} // Adjust as needed
//		driver.findElement(By.name("password")).sendKeys("yourPassword");
//		driver.findElement(By.id("passwordNext")).click();

	}

	public void loginWithGithub(String username, String password) {
		driver.findElement(btnGithubGoogle).click();

		WebElement userName = driver.findElement(By.id("login_field"));

		//Fill user name

		userName.sendKeys("testtest130138@gmail.com");

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		//Find password'

		WebElement password1 = driver.findElement(By.id("password"));

		//Fill password

		password1.sendKeys("Test**123456");

		try {
			Thread.sleep(6000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		WebElement Signin= driver.findElement(By.className("js-sign-in-button"));

		//Hit search button

		Signin.click();
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		/*WebElement authorizeButton = driver.findElement(By.name("authorize"));
        authorizeButton.click();
		*/
	}
}
