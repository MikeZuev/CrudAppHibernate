package tests.testmockito;

import com.zuev.entities.Writer;
import com.zuev.repositories.WriterRepository;
import com.zuev.services.ServiceForWriter;
import com.zuev.services.impl.ServiceForWritersImpl;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;

public class TestServiceForWriter {

    @Mock
    WriterRepository writerRepository;
    ServiceForWriter serviceForWriter;

    public TestServiceForWriter(){
        MockitoAnnotations.openMocks(this);
        this.serviceForWriter = new ServiceForWritersImpl(writerRepository);
    }

    @Test
    void TestGetWriterById(){

        Writer newWriter = new Writer(1, "Bob", "Bobovich");
        when(writerRepository.getByld(1)).thenReturn(newWriter);

        Writer writer = serviceForWriter.getByld(1);
        Assertions.assertEquals(newWriter, writer);
    }

    @Test
    void TestGetAllWriters(){
        Writer firstWriter = new Writer(1, "Bob", "Bobkov");
        Writer secondWriter = new Writer(2, "Mike", "Zuev");
        Writer thirdWriter = new Writer(3, "Boris", "Pert");

        List<Writer> newWriters = new ArrayList<>(Arrays.asList(
                firstWriter, secondWriter, thirdWriter
        ));

        when(writerRepository.getAll()).thenReturn(newWriters);
        List<Writer> writers = serviceForWriter.getAll();
        Assertions.assertEquals(newWriters, writers);
    }

    @Test
    void TestSaveWriter(){
        Writer newWriter = new Writer(1, "Bob", "Bobkov");
        when(writerRepository.save(newWriter)).thenReturn(newWriter);
        Writer writer = serviceForWriter.save(newWriter);
        Assertions.assertEquals(newWriter, writer);
    }

    @Test
    void TestUpdateWriter(){
        Writer newWriter = new Writer(1, "Bob", "Bobkov");
        when(writerRepository.update(newWriter)).thenReturn(newWriter);
        Writer writer = serviceForWriter.update(newWriter);
        Assertions.assertEquals(newWriter, writer);
    }

}
