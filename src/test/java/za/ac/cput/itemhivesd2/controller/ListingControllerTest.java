package za.ac.cput.itemhivesd2.controller;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import za.ac.cput.itemhivesd2.service.ListingService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ListingControllerTest {

    @Mock
    private ListingService service;

    @InjectMocks
    private ListingController controller;

    @Test
    void testGetAll(){
        when(service.getAll()).thenReturn(List.of());
        List<?> result = controller.getAll();
        assertNotNull(result);
        verify(service).getAll();
    }
}
