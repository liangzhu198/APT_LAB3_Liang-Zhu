import junit.framework.TestCase;

public class RationalTest extends TestCase {

    protected Rational HALF;

    protected void setUp() {
      HALF = new Rational( 1, 2 );
    }

    // Create new test
    public RationalTest (String name) {
        super(name);
    }
    
    public void testSet(){
    	Rational a = new Rational(1,3);
    	Rational b = new Rational(a);
    	assertEquals(a, b);
    }
    
    public void testEquality() {
        assertEquals(new Rational(1,3), new Rational(1,3));
        assertEquals(new Rational(1,3), new Rational(2,6));
        assertEquals(new Rational(3,3), new Rational(1,1));
        assertEquals(new Rational(3,0), new Rational(1,0));
        Rational a = null;
        assertFalse(new Rational(1,2).equals(a));
        String b = "0";
        assertFalse(new Rational(1,2).equals(b));
    }

    // Test for nonequality
    public void testNonEquality() {
        assertFalse(new Rational(2,3).equals(
            new Rational(1,3)));    
        assertFalse(new Rational(2,-3).equals(
                new Rational(-1,3)));    
    }

    public void testAccessors() {
    	assertEquals(new Rational(2,3).numerator(), 2);
    	assertEquals(new Rational(2,3).denominator(), 3);
    	assertEquals(new Rational(-2,3).numerator(), -2);
    	assertEquals(new Rational(-2,3).denominator(), 3);
    	assertEquals(new Rational(0,-3).numerator(), 0);
    	assertEquals(new Rational(0,-3).denominator(), -1);
    }
    
    public void testPlus(){
    	Rational a = new Rational(1,-3);
    	Rational b = new Rational(1,-2);
    	assertEquals(a.plus(b).denominator()/2, 3);
    	Rational e = new Rational(1,2147483647);
    	Rational f = new Rational(1,2);
    	assertEquals(e.plus(f).denominator()/2, 2147483647);
    }
    
    public void testTimes(){
    	Rational a = new Rational(1,2);
    	Rational b = new Rational(1,2);
    	assertEquals(a.times(b).denominator()/2, 2);
    	Rational c = new Rational(1,2147483647);
    	Rational d = new Rational(1,2);
    	assertEquals(c.times(d).denominator()/2, 2147483647);
    }
    
    public void testMinus(){
    	Rational a = new Rational(-1,3);
    	Rational b = new Rational(1,2);
    	assertEquals(a.minus(b).denominator()/2, 3);
    	Rational c = new Rational(-1,2147483647);
    	Rational d = new Rational(1,2);
    	assertEquals(c.minus(d).denominator()/2, 2147483647);
    }
    
    public void testDivides(){
    	Rational a = new Rational(1,2);
    	Rational b = new Rational(2,1);
    	assertEquals(a.divides(b).denominator()/2, 2);
    	Rational c = new Rational(1,2147483647);
    	Rational d = new Rational(2,1);
    	assertEquals(c.divides(d).denominator()/2, 2147483647);
    }
    
    public void testToleranceSet(){
    	Rational a = new Rational(0,2);
    	Rational.setTolerance(a);
    	assertEquals(Rational.getTolerance(), a);
    }
    
    public void testIsLessThan(){
    	assertFalse(new Rational(1,-3).isLessThan(new Rational(-2,3))); 
    }
    
    public void testAbs(){
    	assertTrue(new Rational(1,2).abs().equals(new Rational(1,2)));  
    	assertTrue(new Rational(-1,-2).abs().equals(new Rational(1,2))); 
    	assertTrue(new Rational(-1,2).abs().equals(new Rational(1,2)));
    	assertTrue(new Rational(1,-2).abs().equals(new Rational(1,2)));
    }
    
    public void testRoot() {
        Rational s = new Rational( 1, 4 );
        Rational sRoot = null;
        try {
            sRoot = s.root();
        } catch (IllegalArgumentToSquareRootException e) {
            e.printStackTrace();
        }
        assertTrue( sRoot.isLessThan( HALF.plus( Rational.getTolerance() ) ) 
                        && HALF.minus( Rational.getTolerance() ).isLessThan( sRoot ) );
    }
    
    public void testCatchException1(){
        Rational s1 = new Rational( 2147483647, 1 );
        boolean flag = false;
        try {
            s1.root();
        } catch (IllegalArgumentToSquareRootException e) {
        	flag = true;
            e.printStackTrace();
        }
        assertTrue(flag);
    }
    
    public void testCatchException2(){
        Rational s1 = new Rational( -1, 1 );
        boolean flag = false;
        try {
            s1.root();
        } catch (IllegalArgumentToSquareRootException e) {
        	flag = true;
            e.printStackTrace();
        }
        assertTrue(flag);
    }
    
    public void testMain(){
    	String[] a = new String[2];
    	Rational.main(a);
    }

    public static void main(String args[]) {
        String[] testCaseName = 
            { RationalTest.class.getName() };
        // junit.swingui.TestRunner.main(testCaseName);
        junit.textui.TestRunner.main(testCaseName);
    }
}