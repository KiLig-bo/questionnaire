package org.mgcx.nepm.util;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * 封装回传体
 */
@Data
public class ResponseResult<T> {
    @JsonProperty("isPass")
    private  boolean isPass;
    @JsonProperty("user")
    private  T data;

    private String token;

    public ResponseResult(boolean isPass, T data, String token) {
        this.isPass = isPass;
        this.data = data;
        this.token = token;
    }

    @Override
    public String toString() {
        return "ResponseResult{" +
                "isPass=" + isPass +
                ", data=" + data +
                ", token='" + token + '\'' +
                '}';
    }
}
