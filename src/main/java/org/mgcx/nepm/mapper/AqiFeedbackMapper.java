package org.mgcx.nepm.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.mgcx.nepm.entity.AqiFeedback;

import java.util.List;
import java.util.Map;

/**
* @author 赵福强
* @description 针对表【aqi_feedback(空气质量公众监督反馈表)】的数据库操作Mapper
* @createDate 2024-05-21 11:18:24
* @Entity org.mgcx.nepm.entity.AqiFeedback
*/
@Mapper
public interface AqiFeedbackMapper {

    AqiFeedback getAqiFeedbackById(AqiFeedback aqiFeedback);

    List<AqiFeedback> getAqiFeedbackByGmId(AqiFeedback aqiFeedback);
    List<AqiFeedback> getAqiFeedbackByState(AqiFeedback aqiFeedback);

    int modifyAqiFeedback(AqiFeedback aqiFeedback);
    int saveAqiFeedbackId(AqiFeedback  aqiFeedback);


    List<AqiFeedback> sellectAllAqiFeedBack();

    AqiFeedback selectAqiFeedbackById(Integer afId);

    List<AqiFeedback> searchAqiFeedBack(AqiFeedback aqiFeedback);

    List<AqiFeedback> getAqiFeedbackBySpId(String spId);
}




