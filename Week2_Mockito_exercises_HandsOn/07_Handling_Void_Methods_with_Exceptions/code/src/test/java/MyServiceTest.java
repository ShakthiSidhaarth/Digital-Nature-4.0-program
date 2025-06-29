import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;

public class MyServiceTest {

    @Test
    public void testVoidMethodThrowsException() {
        ExternalApi mockApi = Mockito.mock(ExternalApi.class);
        doThrow(new RuntimeException("Delete failed")).when(mockApi).deleteResource("123");

        MyService service = new MyService(mockApi);

        assertThrows(RuntimeException.class, () -> service.remove("123"));
        verify(mockApi).deleteResource("123");
    }
}