package com.yangkai.hotel.main.service;

import java.util.concurrent.TimeUnit;

public interface MailService {
    boolean sentMail(String mailAddress, String content, String verificationCode, long time, TimeUnit timeUnit);
}
