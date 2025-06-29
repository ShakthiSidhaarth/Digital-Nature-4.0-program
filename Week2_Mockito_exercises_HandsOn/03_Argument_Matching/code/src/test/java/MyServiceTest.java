import org.junit.Test;
import org.mockito.Mockito;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

public class MyServiceTest {

    @Test
    public void testArgumentMatching() {
        ExternalApi mockApi = Mockito.mock(ExternalApi.class);
        MyService service = new MyService(mockApi);
        service.process();
        verify(mockApi).sendData(eq("Payload"), eq(200));
    }
}