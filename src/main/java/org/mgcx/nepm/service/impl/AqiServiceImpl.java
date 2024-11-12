package org.mgcx.nepm.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.mgcx.nepm.entity.Aqi;
import org.mgcx.nepm.mapper.AqiMapper;
import org.mgcx.nepm.service.IAqiService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class AqiServiceImpl extends ServiceImpl<AqiMapper, Aqi> implements IAqiService {

    @Autowired
    AqiMapper aqiMapper;
    @Override
    public Aqi getAqiById(Aqi aqi) {
        return aqiMapper.getAqiById(aqi);
    }

    @Override
    public List<Aqi> listAqi(Aqi aqi) {
        return aqiMapper.listAqi(aqi);
    }
}
