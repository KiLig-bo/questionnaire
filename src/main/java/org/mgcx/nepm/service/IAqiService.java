package org.mgcx.nepm.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.mgcx.nepm.entity.Aqi;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IAqiService extends IService<Aqi> {
    Aqi getAqiById(Aqi aqi);
    List<Aqi> listAqi(Aqi aqi);
}
