package njunit.annotation;

public class Example 
{

    @UnitTest(value="Test 1. This test will pass.")
    public void pass() {
        assert 10 > 5;
    }

    @UnitTest("Test 2. This test will fail.")
    public void fail() {
        assert 10 < 5;
    }

}

