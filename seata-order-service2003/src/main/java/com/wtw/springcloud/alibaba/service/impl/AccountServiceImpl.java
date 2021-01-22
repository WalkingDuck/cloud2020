package com.wtw.springcloud.alibaba.service.impl;

import com.wtw.springcloud.alibaba.dao.AccountDao;
import com.wtw.springcloud.alibaba.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;

@Service
@Slf4j
public class AccountServiceImpl implements AccountService {

    @Resource
    private AccountDao accountDao;

    @Override
    public void decrease(Long userId, BigDecimal money) {
        log.info("----------------- 开始更新余额 -----------------");
        accountDao.decrease(userId, money);
        log.info("----------------- 结束更新余额 -----------------");
    }
}
