package tests.testmockito;

import com.zuev.entities.Label;
import com.zuev.repositories.LabelRepository;
import com.zuev.services.ServiceForLabel;
import com.zuev.services.impl.ServiceForLabelsImpl;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;


public class TestServiceForLabel {

    @Mock
    LabelRepository labelRepository;
    ServiceForLabel serviceForlabel;

    public TestServiceForLabel(){
       MockitoAnnotations.openMocks(this);
        this.serviceForlabel = new ServiceForLabelsImpl(labelRepository);
    }

    @Test
    void TestGetLabelById(){
        Label newLabel = new Label(1L, "super");
        when(labelRepository.getByld(1L)).thenReturn(newLabel);

        Label label = serviceForlabel.getByld(1L);
        Assertions.assertEquals(newLabel, label);
    }

    @Test
    void TestGetAllLabels(){
        Label firstLabel = new Label(1L, "super");
        Label secondLabel = new Label(2L, "msg");
        Label thirdLabel = new Label(3L, "something");

        List<Label> newLabels = new ArrayList<>(Arrays.asList(
                firstLabel, secondLabel, thirdLabel
        ));

        when(labelRepository.getAll()).thenReturn(newLabels);

        List<Label> labels = serviceForlabel.getAll();
        Assertions.assertEquals(newLabels, labels);
    }

    @Test
    void TestSaveLabel(){
        Label newLabel = new Label(1L, "super");

        when(labelRepository.save(newLabel)).thenReturn(newLabel);
        Label label = serviceForlabel.save(newLabel);
        Assertions.assertEquals(newLabel, label);
    }

    @Test
    void TestUpdateLabel(){
        Label newLabel = new Label(1L, "super");

        when(labelRepository.update(newLabel)).thenReturn(newLabel);
        Label label = serviceForlabel.update(newLabel);
        Assertions.assertEquals(newLabel, label);
    }

}
