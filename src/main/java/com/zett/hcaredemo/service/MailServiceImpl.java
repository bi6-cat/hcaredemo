package com.zett.hcaredemo.service;

import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.nio.file.Files;
import java.nio.file.Paths;

@Slf4j
@RequiredArgsConstructor
@Service
public class MailServiceImpl implements MailService {

    private final JavaMailSender emailSender;
    private final TemplateEngine templateEngine;

    @Async
    public void sendActivationFailedEmail(String to) {
        try {
            MimeMessage message = emailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setTo(to);
            helper.setSubject("Activation failed!");

            Context context = new Context();
            String htmlContent = templateEngine.process("activation-failed-email", context);
            helper.setText(htmlContent, true);

            emailSender.send(message);
        } catch (Exception ex) {
            log.error("Activation failed!", ex);
            throw new RuntimeException("Send activation failed!");
        }
    }

    @Async
    public void sendOtpEmail(String to, String otp) {
        try {
            MimeMessage message = emailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setTo(to);
            helper.setSubject("Your OTP Code");

            Context context = new Context();
            context.setVariable("otp", otp);
            String htmlContent = templateEngine.process("otp-email", context);
            helper.setText(htmlContent, true);

            emailSender.send(message);
        } catch (Exception ex) {
            log.error("Send to: {}, ex: {} ", to, ex.getMessage());
            throw new RuntimeException("");
        }
    }

    @Async
    public void sendReportEmail(String to) {
        try {
            MimeMessage message = emailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(to);
            helper.setSubject("Your Report");

            Resource resource = new ClassPathResource("templates/email/demo.html");
            String content = new String(Files.readAllBytes(Paths.get(resource.getURI())), "UTF-8");

            helper.setText(content, true);
            emailSender.send(message);
        } catch (Exception ex) {
            log.error("Send to: {}, ex: {} ", to, ex.getMessage());
            throw new RuntimeException("");
        }
    }
}
