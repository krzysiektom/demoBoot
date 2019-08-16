package pl.tomala.demo.device;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class DeviceControllerTest {
    @InjectMocks
    private DeviceController tested;

    @Test
    public void shouldHome() {
        assertEquals("devices",tested.home());
    }

    @Test
    public void add() {
    }
}