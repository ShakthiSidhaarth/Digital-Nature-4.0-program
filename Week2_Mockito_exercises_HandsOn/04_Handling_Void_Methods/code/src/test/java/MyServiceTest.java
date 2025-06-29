import org.junit.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;


public class MyServiceTest {

    @Test
    public void testVoidMethod() {

        ExternalApi mockApi = Mockito.mock(ExternalApi.class);
        doNothing().when(mockApi).logEvent("Action performed");
        MyService service = new MyService(mockApi);
        service.performAction();
        verify(mockApi).logEvent("Action performed");
    }

}