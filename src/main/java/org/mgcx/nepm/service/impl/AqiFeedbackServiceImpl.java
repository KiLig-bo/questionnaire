package org.mgcx.nepm.service.impl;

import org.mgcx.nepm.entity.AqiFeedback;

import org.mgcx.nepm.mapper.AqiFeedbackMapper;
import org.mgcx.nepm.service.IAqiFeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AqiFeedbackServiceImpl implements IAqiFeedbackService {
    @Autowired
    AqiFeedbackMapper aqiFeedBackMapper;
    @Override
    public List<AqiFeedback> sellectAllAqiFeedBack()  {
        return aqiFeedBackMapper.sellectAllAqiFeedBack();
    }

    @Override
    public AqiFeedback selectAqiFeedbackById(Integer afId) {
        return aqiFeedBackMapper. selectAqiFeedbackById(afId);
    }

    @Override
    public AqiFeedback getAqiFeedbackById(AqiFeedback aqiFeedback) {
        return aqiFeedBackMapper.getAqiFeedbackById(aqiFeedback);
    }


    @Override
    public int modifyAqiFeedback(AqiFeedback aqiFeedback){
        return aqiFeedBackMapper.modifyAqiFeedback(aqiFeedback);
    }

    @Override
    public List<AqiFeedback> searchAqiFeedBack(AqiFeedback aqiFeedback) {
        return aqiFeedBackMapper.searchAqiFeedBack(aqiFeedback);
    }

    @Override
    public  int saveAqiFeedbackId(AqiFeedback aqiFeedback){
        return aqiFeedBackMapper.saveAqiFeedbackId(aqiFeedback);
    }

    @Override
    public List<AqiFeedback> selectAqiFeedbackBySpId(String spId) {
        return aqiFeedBackMapper.getAqiFeedbackBySpId(spId);
    }


}
