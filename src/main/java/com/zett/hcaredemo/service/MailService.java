package com.zett.hcaredemo.service;

public interface MailService {
    void sendActivationFailedEmail(String to);

    void sendOtpEmail(String to, String otp);

    void sendReportEmail(String to);
}
