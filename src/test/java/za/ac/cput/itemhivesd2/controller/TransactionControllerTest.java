package za.ac.cput.transactionentity.controllerTest;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import za.ac.cput.transactionentity.controller.TransactionController;
import za.ac.cput.transactionentity.service.TransactionService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)
public class TransactionControllerTest {
    @Mock
    private TransactionService service;

    @InjectMocks
    private TransactionController controller;

    @Test
    void testGetAll(){
        when (service.getAll()).thenReturn(List.of());
        List<?>result = controller.getAll();
        assertNotNull(result);
        verify(service).getAll();
    }
}
