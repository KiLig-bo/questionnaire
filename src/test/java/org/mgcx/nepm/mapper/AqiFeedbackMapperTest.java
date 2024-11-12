package org.mgcx.nepm.mapper;

import org.junit.jupiter.api.Test;
import org.mgcx.nepm.entity.AqiFeedback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class AqiFeedbackMapperTest {
    @Autowired
    AqiFeedbackMapper aqiFeedbackMapper;
    @Test
    public void testGetAqiFeedbackById() {
        AqiFeedback aqiFeedback = new AqiFeedback();
        aqiFeedback.setAfId(1);
        AqiFeedback aqiFeedback1 = aqiFeedbackMapper.getAqiFeedbackById(aqiFeedback);
        System.out.println(aqiFeedback1);
    }


    @Test
    public void testGetAqiFeedbackByGmId() {
        AqiFeedback aqiFeedback = new AqiFeedback();
        aqiFeedback.setGmId("uuid-002");
        List<AqiFeedback> aqiFeedback1 = aqiFeedbackMapper.getAqiFeedbackByGmId(aqiFeedback);
        System.out.println(aqiFeedback1);
    }

    @Test
    public void testGetAqiFeedbackByState() {
        AqiFeedback aqiFeedback = new AqiFeedback();
        aqiFeedback.setState(1);
        List<AqiFeedback> aqiFeedback1 = aqiFeedbackMapper.getAqiFeedbackByState(aqiFeedback);
        System.out.println(aqiFeedback1);
    }
    @Test
    public void testModifyAqiFeedback() {
        AqiFeedback aqiFeedback = new AqiFeedback();
        aqiFeedback.setAfId(1);
        AqiFeedback aqiFeedback1 = aqiFeedbackMapper.getAqiFeedbackById(aqiFeedback);
        aqiFeedback.setRemarks("测试mapper");
        int re=aqiFeedbackMapper.modifyAqiFeedback(aqiFeedback);
        AqiFeedback aqiFeedback2 = aqiFeedbackMapper.getAqiFeedbackById(aqiFeedback);
        System.out.println(aqiFeedback2);
    }
    @Test
    public void testSaveAqiFeedbackId() {
        AqiFeedback aqiFeedback = new AqiFeedback();
        aqiFeedback.setAfId(1);
        AqiFeedback aqiFeedback1 = aqiFeedbackMapper.getAqiFeedbackById(aqiFeedback);
        System.out.println(aqiFeedback1);
        aqiFeedback1.setAfId(6);
        System.out.println(aqiFeedback1);
        aqiFeedbackMapper.saveAqiFeedbackId(aqiFeedback1);
        System.out.println(aqiFeedback1);
    }

    @Test
    public void testSearchAqiFeedBack() {
        AqiFeedback aqiFeedback = new AqiFeedback();
        List<AqiFeedback> aqiFeedback1 = aqiFeedbackMapper.searchAqiFeedBack(aqiFeedback);
        System.out.println(aqiFeedback1);

    }
}
