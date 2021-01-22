package com.wtw.springcloud.alibaba.service.impl;

import com.wtw.springcloud.alibaba.dao.StorageDao;
import com.wtw.springcloud.alibaba.service.StorageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
@Slf4j
public class StorageServiceImpl implements StorageService {

    @Resource
    private StorageDao storageDao;

    @Override
    public void decrease(Long productId, Integer count) {
        log.info("----------------- 开始更新库存 -----------------");
        storageDao.decrease(productId, count);
        log.info("----------------- 结束更新库存 -----------------");
    }
}
