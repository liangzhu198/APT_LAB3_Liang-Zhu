import junit.framework.*;
import com.mockobjects.servlet.*;

public class TestTestingServlet extends TestCase {

  public void testNoParameter() throws Exception {
    TestingLabConverterServlet s = new TestingLabConverterServlet();
    MockHttpServletRequest request = 
      new MockHttpServletRequest();
    MockHttpServletResponse response = 
      new MockHttpServletResponse();
    
    response.setExpectedContentType("text/html");
    s.doGet(request,response);
    response.verify();     //this do the test?
    String a = response.getOutputStreamContents().trim();
    assertEquals("<html><head><title>No Temperature</title>"
			+ "</head><body><h2>Need to enter a temperature!"
			+ "</h2></body></html>",
			a);  
  }
  
  public void testBadParameter() throws Exception {
	    TestingLabConverterServlet s = new TestingLabConverterServlet();
	    MockHttpServletRequest request = 
	      new MockHttpServletRequest();
	    MockHttpServletResponse response = 
	      new MockHttpServletResponse();
	    
	    request.setupAddParameter("farenheitTemperature", "boo!");
	    response.setExpectedContentType("text/html");
	    s.doGet(request,response);
	    response.verify();     //this do the test?
	    String a = response.getOutputStreamContents().trim();
	    assertEquals("<html><head><title>Bad Temperature</title>"
				+ "</head><body><h2>Need to enter a valid temperature!"
			    + "Got a NumberFormatException on " 
				+ "boo!" 
				+ "</h2></body></html>",
				a);  
	  }
  
  public void testGoodParameter() throws Exception {
	    TestingLabConverterServlet s = new TestingLabConverterServlet();
	    MockHttpServletRequest request = 
	      new MockHttpServletRequest();
	    MockHttpServletResponse response = 
	      new MockHttpServletResponse();
	    
	    request.setupAddParameter("farenheitTemperature", "212");
	    response.setExpectedContentType("text/html");
	    s.doGet(request,response);
	    response.verify();     //To verify the specific behavior has been used.
	    String a = response.getOutputStreamContents().trim();
	    String austinTemperature = CityTemperatureServiceProvider.lookup("Austin");
	    assertEquals("<html><head><title>Temperature Converter Result</title>"
					+ "</head><body><h2>" + "212" + " Farenheit = " + "100" + " Celsius "
					+ "</h2>" + "\r\n"   //Under windows, "\n" in txt file is "\r\n"  
					+ "<p><h3>The temperature in Austin is " + austinTemperature + " degrees Farenheit</h3>" + "\r\n"
					+"</body></html>",
				a);  
	  }
  
}

