import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

public class MyServiceTest {

    @Test
    public void testMultipleReturns() {
        ExternalApi mockApi = Mockito.mock(ExternalApi.class);
        when(mockApi.getStatus()).thenReturn("Pending", "Processing", "Completed");

        MyService service = new MyService(mockApi);

        assertEquals("Pending", service.checkStatus());
        assertEquals("Processing", service.checkStatus());
        assertEquals("Completed", service.checkStatus());
    }
}