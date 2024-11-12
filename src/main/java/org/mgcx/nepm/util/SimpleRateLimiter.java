package org.mgcx.nepm.util;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class SimpleRateLimiter {

    // 存储用户请求计数和最后请求时间的映射
    private ConcurrentHashMap<String, RequestWindow> counters = new ConcurrentHashMap<>();
    // 每个用户允许的最大请求次数
    private final int permitsPerUser;
    // 请求的时间窗口（毫秒）
    private final long windowTimeMillis;

    public SimpleRateLimiter(int permitsPerUser, long windowTime, TimeUnit unit) {
        this.permitsPerUser = permitsPerUser;
        this.windowTimeMillis = unit.toMillis(windowTime);
    }


    public boolean tryAcquire(String userKey) {


        RequestWindow window = counters.computeIfAbsent(userKey, k -> new RequestWindow(this));
        long currentTime = System.currentTimeMillis();

        // 检查是否需要重置窗口
        if (currentTime - window.getLastRequestTime() > windowTimeMillis) {
            window.reset(currentTime);
        }

        // 检查请求是否超出限制
        return window.incrementIfUnderLimit();
    }

    private static class RequestWindow {
        private long lastRequestTime;
        private AtomicInteger requestCount;
        private final SimpleRateLimiter rateLimiter; // 增加对外部类的引用

        public RequestWindow(SimpleRateLimiter rateLimiter) {
            this.lastRequestTime = System.currentTimeMillis();
            this.requestCount = new AtomicInteger(0);
            this.rateLimiter = rateLimiter; // 初始化外部类的引用
        }

        public long getLastRequestTime() {
            return lastRequestTime;
        }

        public void reset(long currentTime) {
            this.lastRequestTime = currentTime;
            this.requestCount.set(0);
        }

        public boolean incrementIfUnderLimit() {
            return requestCount.incrementAndGet() <= rateLimiter.permitsPerUser; // 使用外部类的引用
        }
    }
}