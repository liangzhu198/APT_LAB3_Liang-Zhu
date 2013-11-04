import java.util.StringTokenizer;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class SeleniumTemperatureTest {
    public static void main(String[] args) throws Exception {
        // The Firefox driver supports javascript 
        WebDriver driver = new FirefoxDriver();
        
        // Go to the login page
        driver.get("http://adnan.appspot.com/testing-lab-login.html");
        
        // check user andy
        WebElement name = driver.findElement(By.name("userId"));
        name.clear();
        name.sendKeys("andy");
        WebElement passwd = driver.findElement(By.name("userPassword"));
        passwd.clear();
        passwd.sendKeys("apple");
        passwd.submit();
        if(driver.findElement(By.tagName("body")).getText().contains("Convert"))
			System.out.println("Login with andy Passed!!!");
		else
			System.out.println("Login with andy Failed!!!");
		

        // check user bob
        driver.navigate().back();
        name = driver.findElement(By.name("userId"));
        name.clear();
        name.sendKeys("bob");
        passwd = driver.findElement(By.name("userPassword"));
        passwd.clear();
        passwd.sendKeys("bathtub");
        passwd.submit();
        if(driver.findElement(By.tagName("body")).getText().contains("Convert"))
			System.out.println("Login with bob Passed!!!");
		else
			System.out.println("Login with bob Failed!!!");
			
		// check user charley
        driver.navigate().back();
        name = driver.findElement(By.name("userId"));
        name.clear();
        name.sendKeys("charley");
        passwd = driver.findElement(By.name("userPassword"));
        passwd.clear();
        passwd.sendKeys("china");
        passwd.submit();
        if(driver.findElement(By.tagName("body")).getText().contains("Convert"))
			System.out.println("Login with charley Passed!!!");
		else
			System.out.println("Login with charley Failed!!!");
		
	    // check user charley userName case insensitive
        driver.navigate().back();
        name = driver.findElement(By.name("userId"));
        name.clear();
        name.sendKeys("Charley");
        passwd = driver.findElement(By.name("userPassword"));
        passwd.clear();
        passwd.sendKeys("china");
        passwd.submit();
        if(driver.findElement(By.tagName("body")).getText().contains("Convert"))
			System.out.println("Login with charley name-insensitive Passed!!!");
		else
			System.out.println("Login with charley name-insensitive Failed!!!");
			
		// check user charley password case sensitive
        driver.navigate().back();
        name = driver.findElement(By.name("userId"));
        name.clear();
        name.sendKeys("charley");
        passwd = driver.findElement(By.name("userPassword"));
        passwd.clear();
        passwd.sendKeys("China");
        passwd.submit();
        if(driver.findElement(By.tagName("body")).getText().contains("Convert"))
			System.out.println("Login with charley password-sensitive failed!!!");
		else
			System.out.println("Login with charley password-sensitive passed!!!");

		// check user charley userName case insensitive
        driver.navigate().back();
        name = driver.findElement(By.name("userId"));
        name.clear();
        name.sendKeys(" charley ");
        passwd = driver.findElement(By.name("userPassword"));
        passwd.clear();
        passwd.sendKeys(" china ");
        passwd.submit();
        if(driver.findElement(By.tagName("body")).getText().contains("Convert"))
			System.out.println("Login with charley whitespace-insensitive Passed!!!");
		else
			System.out.println("Login with charley whitespace-insensitive Failed!!!");
		
		
		// intentional fail 3 times
		for (int i=1;i<=3;i++){
        driver.navigate().back();
        name = driver.findElement(By.name("userId"));
        name.clear();
        name.sendKeys("charley");
        passwd = driver.findElement(By.name("userPassword"));
        passwd.clear();
        passwd.sendKeys("xxx");
        passwd.submit();
        if(driver.findElement(By.tagName("body")).getText().contains("Convert"))
			System.out.println("This should fail!!!");
		else
			System.out.println("failed time = " + i);
		}
		
		// check lockout
		boolean lockout_flag = false;
        driver.navigate().back();
        name = driver.findElement(By.name("userId"));
        name.clear();
        name.sendKeys("charley");
        passwd = driver.findElement(By.name("userPassword"));
        passwd.clear();
        passwd.sendKeys("china");
        passwd.submit();
        if(driver.findElement(By.tagName("body")).getText().contains("Convert"))
			System.out.println("lockout failed, 'sleep 60 second' is skipped!");
		else{
			System.out.println("lockout successful");
			lockout_flag = true;
		}
        
        // wait for lockout
        int i = 0;
        while (lockout_flag == true){
        	i = i+1;
            System.out.println("Sleeping for 5 seconds");
            Thread.sleep(5*1000);
	        driver.navigate().back();
	        name = driver.findElement(By.name("userId"));
	        name.clear();
	        name.sendKeys("charley");
	        passwd = driver.findElement(By.name("userPassword"));
	        passwd.clear();
	        passwd.sendKeys("china");
	        passwd.submit();
	        if(driver.findElement(By.tagName("body")).getText().contains("Convert")){
				System.out.println("lockout is no more than " + i*5 + " seconds");
				lockout_flag = false;
	        }
			else{
				System.out.println("lockout is longer than " + i*5 + " seconds");
			}
        }
        
		// check farenheitTemp = 212
        WebElement temp = driver.findElement(By.name("farenheitTemperature"));
        temp.clear();
        temp.sendKeys("212");
        temp.submit();
        String c = driver.findElement(By.tagName("h2")).getText();
        StringTokenizer st = new StringTokenizer(c);
        st.nextToken();
        st.nextToken();
        st.nextToken();
        float fcel = Float.parseFloat(st.nextToken());
        if(fcel < 101 && fcel >= 100)
			System.out.println("Persision to 2 places at 212 Passed!!!");
		else
			System.out.println("Persision to 2 places at 212 Failed!!!");
        
		// check farenheitTemp = 0
        driver.navigate().back();
        temp = driver.findElement(By.name("farenheitTemperature"));
        temp.clear();
        temp.sendKeys("0");
        temp.submit();
        c = driver.findElement(By.tagName("h2")).getText();
        st = new StringTokenizer(c);
        st.nextToken();
        st.nextToken();
        st.nextToken();
        fcel = Float.parseFloat(st.nextToken());
        if(fcel < -17 && fcel >= -18)
			System.out.println("Persision to 2 places at 0 Passed!!!");
		else
			System.out.println("Persision to 2 places at 0 Failed!!!");
        
		// check farenheitTemp = 500
        driver.navigate().back();
        temp = driver.findElement(By.name("farenheitTemperature"));
        temp.clear();
        temp.sendKeys("500");
        temp.submit();
        c = driver.findElement(By.tagName("h2")).getText();
        st = new StringTokenizer(c);
        st.nextToken();
        st.nextToken();
        st.nextToken();
        fcel = Float.parseFloat(st.nextToken());
        if(fcel < 300 && fcel >= 200)
			System.out.println("Persision to 1 place at 500 Passed!!!");
		else
			System.out.println("Persision to 1 place at 500 Failed!!!");
        
		// check farenheitTemp = -100
        driver.navigate().back();
        temp = driver.findElement(By.name("farenheitTemperature"));
        temp.clear();
        temp.sendKeys("-100");
        temp.submit();
        c = driver.findElement(By.tagName("h2")).getText();
        st = new StringTokenizer(c);
        st.nextToken();
        st.nextToken();
        st.nextToken();
        fcel = Float.parseFloat(st.nextToken());
        if(fcel < 0 && fcel >= -100)
			System.out.println("Persision to 1 place at -100 Passed!!!");
		else
			System.out.println("Persision to 1 place at -100 Failed!!!");
        
		// check non-decimal notation 
        driver.navigate().back();
        temp = driver.findElement(By.name("farenheitTemperature"));
        temp.clear();
        temp.sendKeys("5E2");
        temp.submit();
        c = driver.findElement(By.tagName("h2")).getText();
        st = new StringTokenizer(c);
        st.nextToken();
        st.nextToken();
        st.nextToken();
        fcel = Float.parseFloat(st.nextToken());
        if(driver.findElement(By.tagName("body")).getText().contains("Farenheit"))
			System.out.println("A non-decimal notation failed: Failed!!!");
		else
			System.out.println("non-decimal notation failed: Passed!!!");
        
		// check NumberFormatException  
        driver.navigate().back();
        temp = driver.findElement(By.name("farenheitTemperature"));
        temp.clear();
        try {
        temp.sendKeys(".a2");
        temp.submit();
        }finally{
        if(driver.findElement(By.tagName("body")).getText().contains("NumberFormatException"))
			System.out.println("Test NumberFormatException Passed!!!");
		else
			System.out.println("Test NumberFormatException Failed!!!");
        }
        
        //check correctness of parameter passing
        driver.navigate().back();
        driver.get("http://adnan.appspot.com/testing-lab-conversion?farenheittemperature=-40");
        if(driver.findElement(By.tagName("body")).getText().contains("Need to"))
			System.out.println("Test lowerCase farenheitTemperature parameter accepted: Passed!!!");
		else
			System.out.println("Test lowerCase farenheitTemperature parameter accepted: Failed!!!");
        
        //check correctness of parameter passing
        driver.navigate().back();
        driver.get("http://adnan.appspot.com/testing-lab-conversion?farenheitTemperature=-40");
        if(driver.findElement(By.tagName("body")).getText().contains("Need to"))
			System.out.println("Test upperCase farenheitTemperature parameter accepted: Passed!!!");
		else
			System.out.println("Test upperCase farenheitTemperature parameter accepted: Failed!!!");
        
        //the end
        driver.close();
        driver.quit();
					
     }
}