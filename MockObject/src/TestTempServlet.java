import junit.framework.*;
import com.mockobjects.servlet.*;

public class TestTempServlet extends TestCase {

  public void test_bad_parameter() throws Exception {
    TemperatureServlet s = new TemperatureServlet();
    MockHttpServletRequest request = 
      new MockHttpServletRequest();
    MockHttpServletResponse response = 
      new MockHttpServletResponse();
    
    request.setupAddParameter("Fahrenheit", "boo!");
    response.setExpectedContentType("text/html");
    s.doGet(request,response);
    response.verify();
    String a = response.getOutputStreamContents();
    assertEquals("Invalid temperature: boo!\r\n",
    			a);  //Under windows, "\n" in txt file is "\r\n" , or you can use .trim() to delete the "\r\n" at the end 
                     //of the file
  }
  
  public void test_boil() throws Exception {
    TemperatureServlet s = new TemperatureServlet();
    MockHttpServletRequest request = 
      new MockHttpServletRequest();
    MockHttpServletResponse response = 
      new MockHttpServletResponse();
    
    request.setupAddParameter("Fahrenheit", "212");
    response.setExpectedContentType("text/html");
    s.doGet(request,response);
    response.verify();
    String a = response.getOutputStreamContents().toString();
    System.out.print(a.substring(0,a.length()-1)+"end");
    assertEquals("Fahrenheit: 212, Celsius: 100.0\r\n",
                 a);
  }
  
}

