package org.mgcx.nepm.util.minio;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UploadResponse {

    private String minIoUrl;

    private String nginxUrl;

    public UploadResponse(String minIoUrl) {
        this.minIoUrl = minIoUrl;
    }

    public String getMinIoUrl() {
        return minIoUrl;
    }

}

