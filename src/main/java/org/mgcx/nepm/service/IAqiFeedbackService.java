package org.mgcx.nepm.service;

import org.mgcx.nepm.entity.AqiFeedback;

import java.util.List;

public interface IAqiFeedbackService {
    /**
     * getAfData获得空气质量反馈数据
     */
    List<AqiFeedback> sellectAllAqiFeedBack();

    AqiFeedback selectAqiFeedbackById(Integer afId);

    AqiFeedback getAqiFeedbackById(AqiFeedback aqiFeedback);
    int modifyAqiFeedback(AqiFeedback aqiFeedback);

    List<AqiFeedback> searchAqiFeedBack(AqiFeedback aqiFeedback);

    int saveAqiFeedbackId(AqiFeedback aqiFeedback);

    List<AqiFeedback> selectAqiFeedbackBySpId(String spId);
}
