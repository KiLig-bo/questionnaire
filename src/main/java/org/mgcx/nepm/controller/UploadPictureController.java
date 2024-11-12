package org.mgcx.nepm.controller;

import lombok.extern.slf4j.Slf4j;
import org.mgcx.nepm.util.CommonResult;
import org.mgcx.nepm.util.SimpleRateLimiter;
import org.mgcx.nepm.util.minio.MinioUtil;
import org.mgcx.nepm.util.minio.UploadResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.concurrent.TimeUnit;

@CrossOrigin
@RestController
@RequestMapping("/data")
@Slf4j
public class UploadPictureController {
    @Autowired
    private MinioUtil minioUtil;

    int permitsUser = 5;//每次允许的访问次数
    int windowTime = 20;//滑动窗口的时间长度/s
    SimpleRateLimiter rateLimiter = new SimpleRateLimiter(permitsUser, windowTime, TimeUnit.SECONDS); // 每个用户每10秒内最多3次请求

    @PostMapping("/uploadPicture")
    public CommonResult uploadPicture(@RequestParam(value = "file") MultipartFile file ,@RequestParam(value ="token", required = false) String authToken)  {
        String token = authToken;
        token = "";
        log.info("------------------uploadPicture-----------------------");
        try{
            if (file.isEmpty()) {
                return CommonResult.failed("File is empty");

            }
            if(!rateLimiter.tryAcquire(token)){
                return CommonResult.failed("upload picture too frequently");
            }
            UploadResponse uploadResponse = minioUtil.uploadFile(file, "my-bucket");
            String imgUrl = uploadResponse.getMinIoUrl();
            System.out.println(imgUrl);
            return CommonResult.success(imgUrl, "Successfully uploaded file");
        } catch (Exception e) {
            return CommonResult.failed("Failed to upload file");
        }
    }


}
