package be.technobel.bll;

import be.technobel.bl.impl.ReceiptServiceImpl;
import be.technobel.dal.models.entities.Material;
import be.technobel.dal.models.entities.Provider;
import be.technobel.dal.models.entities.Receipt;
import be.technobel.dal.repositories.MaterialRepository;
import be.technobel.dal.repositories.ProviderRepository;
import be.technobel.dal.repositories.ReceiptRepository;
import be.technobel.pl.dtos.ReceiptDTO;
import be.technobel.pl.forms.ReceiptForm;
import jakarta.mail.MessagingException;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ReceiptServiceTest {

    @InjectMocks
    private ReceiptServiceImpl receiptService;
    @Mock
    ReceiptRepository receiptRepository;

    @Mock
    ProviderRepository providerRepository;

    @Mock
    MaterialRepository materialRepository;
    private Receipt receipt;

    private ReceiptDTO receiptDTO;

    private ReceiptForm receiptForm;

    private Provider provider;

    private Material material;

    @BeforeEach
    void setUp(){
        byte[] byteArray = new byte[]{1, 2, 3, 4, 5};

        provider = new Provider(1L, "Denis", null, true);

        material = new Material(1L, "Oeuf", false, false, true);

        receiptDTO = new ReceiptDTO(1L, LocalDate.now(), "calirra551@gmail.com", 50, "1258", LocalDate.now().plusDays(1), 5, false,  LocalDate.now().plusDays(10),5, LocalDate.now().plusDays(10) ,false, null, false, null, false, null, "RAS", provider, material, byteArray);
        receipt = new Receipt(receiptDTO.id(), receiptDTO.receiptDate(),  receiptDTO.quantity(), receiptDTO.providerNumber(), receiptDTO.expirationDate(), receiptDTO.temperature(), receiptDTO.frozen(), receiptDTO.frozenTemp(), receiptDTO.frozenDate() ,receiptDTO.FrozenExpirationDate() , receiptDTO.labelling(), receiptDTO.labelComment(), receiptDTO.packaging(), receiptDTO.packagingComment(), receiptDTO.hygiene(), receiptDTO.hygieneComment(), receiptDTO.comment(), receiptDTO.email() ,receiptDTO.imageData()," ",receiptDTO.provider(), receiptDTO.material());
        receiptForm = new ReceiptForm(LocalDate.now(), 50, "1235",  LocalDate.now().plusDays(1), 5, false, 5,  LocalDate.now().plusDays(10), LocalDate.now().plusDays(10), false, null, false, null, false, null, "calirra551@gmail.com", "calirra551@gmail.com", 1L, 1L, byteArray, null);

    }

    @Test
    void getById(){

        when(receiptRepository.findById(anyLong())).thenReturn(Optional.of(receipt));

        Optional<Receipt> search = receiptService.getOne(1L);

        assertTrue(search.isPresent());
        assertEquals(receipt, search.get());

    }

    @Test
    void getById_when_not_found(){

        when(receiptRepository.findById(anyLong())).thenReturn(Optional.empty());
        Optional<Receipt> search = receiptService.getOne(1L);

        assertFalse(search.isPresent());
    }

    @Test
    void when_create_ok() throws MessagingException {

        when(providerRepository.findById(any())).thenReturn(Optional.of(provider));

        when(materialRepository.findById(any())).thenReturn(Optional.of(material));

        when(receiptRepository.save(any(Receipt.class))).thenReturn(receipt);

        long actualId = receiptService.create(receiptForm);

        verify(receiptRepository, times(1)).save   (any(Receipt.class));
        assertNotNull(actualId);
    }

    @Test
    void when_create_ko(){
        Exception exception = assertThrows(IllegalArgumentException.class, () -> receiptService.create(null));

        String exceptedMessage = "Form can't be null";

        String actualMessage = exception.getMessage();

        assertEquals(exceptedMessage, actualMessage);
    }

    @Test
    void TestDataImage_WhenIdExists(){
        Long id = 1L;

        byte[] file = "test data".getBytes();

        receipt.setId(id);

        when(receiptRepository.findById(id)).thenReturn(Optional.of(receipt));

        receiptService.dataImage(file, id);

        verify(receiptRepository).save(receipt);

        assertArrayEquals(file, receipt.getImageData());

    }

    @Test
    void TestDataImage_WhenIdNotExist(){

        Long id = 1L;

        byte[] file = "test data".getBytes();

        when(receiptRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> receiptService.dataImage(file, id));

        verify(receiptRepository, never()).save(any());
    }

    @Test
    void TestGetImageData_WhenIdExist(){

        Long id = 1L;

        byte[] imageData = "test data".getBytes();
        Receipt receipt1  = new Receipt();

        receipt1.setId(id);

        receipt1.setImageData(imageData);
        when(receiptRepository.findById(id)).thenReturn(Optional.of(receipt1));

        byte[] result = receiptService.getImageData(id);

        assertArrayEquals(imageData, result);
    }

    @Test
    void testGetImageData_WhenIdNotExist(){

        Long id = 1L;

        when(receiptRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> receiptService.getImageData(id));
    }




}
