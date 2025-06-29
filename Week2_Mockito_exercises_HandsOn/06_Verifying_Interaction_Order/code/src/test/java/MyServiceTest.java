import org.junit.Test;
import org.mockito.InOrder;
import org.mockito.Mockito;

import static org.mockito.Mockito.inOrder;

public class MyServiceTest {

    @Test
    public void testInteractionOrder() {
        ExternalApi mockApi = Mockito.mock(ExternalApi.class);
        MyService service = new MyService(mockApi);
        service.run();

        InOrder inOrder = inOrder(mockApi);
        inOrder.verify(mockApi).initialize();
        inOrder.verify(mockApi).execute();
        inOrder.verify(mockApi).cleanup();
    }
}