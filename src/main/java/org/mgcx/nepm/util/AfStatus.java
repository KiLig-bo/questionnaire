package org.mgcx.nepm.util;

import lombok.Data;

@Data
public class AfStatus {
    private Integer status;
    private String showName;

    public AfStatus(Integer status) {
        this.status = status;
        if(status == 0) {
            this.showName = "未指派";
        } else if(status == 1) {
            this.showName = "已指派";
        } else {
            this.showName = "指派失败";
        }
    }


}
