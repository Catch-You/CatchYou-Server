package com.catchyou.api.signup.service;

import com.catchyou.api.signup.exception.CanNotSendMailException;
import com.catchyou.core.dto.BaseResponse;
import com.catchyou.domain.user.adaptor.UserAdaptor;
import com.catchyou.domain.user.entity.User;
import com.catchyou.domain.user.validator.UserValidator;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.Random;

@PropertySource("classpath:application-infrastructure.properties")
@Slf4j
@RequiredArgsConstructor
@Service
public class MailService {
    private final UserValidator userValidator;

    private final JavaMailSender javaMailSender;
    private final String ePw = createKey();

    @Value("${spring.mail.username}")
    private String id;

    public MimeMessage createMessage(String to) throws MessagingException, UnsupportedEncodingException {
        log.info("보내는 대상 : " + to);
        log.info("인증 번호 : " + ePw);

        MimeMessage message = javaMailSender.createMimeMessage();
        message.addRecipients(MimeMessage.RecipientType.TO, to);
        message.setSubject("그놈을 잡아라 인증 코드입니다. ");

        String msg = "<div style='margin: 20px; font-family: Arial, sans-serif; background-color: #f5f5f5; padding: 20px;'>"
                + "<h1 style='color: #3f51b5; margin-bottom: 20px;'>그놈을 잡아라</h1>"
                + "<div style='margin-top: 20px;'>"
                + "<p style='font-size: 16px; margin-bottom: 10px;'>아래 코드를 복사하여 입력해주세요:</p>"
                + "<p style='font-size: 24px; font-weight: bold; color: #f44336; margin-bottom: 20px;'>"
                +  ePw + "</p>"
                + "<p style='font-size: 16px;'>감사합니다.</p>"
                + "</div>"
                + "<div style='border: 1px solid #e0e0e0; padding: 10px; margin-top: 20px;'>"
                + "<h3 style='color: #3f51b5; font-size: 18px; margin-bottom: 10px;'>회원가입 인증 코드입니다.</h3>"
                + "<p style='font-size: 16px; margin-bottom: 10px;'>위의 코드를 사용하여 회원가입을 완료해주세요.</p>"
                + "</div>"
                + "</div>";

        message.setText(msg, "utf-8", "html");	//내용, charset타입, subtype
        message.setFrom(new InternetAddress(id, "catchyou_Admin"));	//발신자 메일주소, 발신자 이름

        return message;
    }

    public static String createKey() {
        StringBuffer key = new StringBuffer();
        Random random = new Random();

        for(int i = 0; i<8; i++){
            int idx = random.nextInt(3);	//0~2 경우를 랜덤

            switch (idx){
                case 0:
                    key.append((char) ((int) random.nextInt(26) + 97));	//a~z
                    break;
                case 1:
                    key.append((char) ((int) random.nextInt(26) + 65));	//A~Z
                    break;
                case 2:
                    key.append(random.nextInt(10));	//0~9
                    break;
            }
        }

        return key.toString();
    }

    private String sendSimpleMessage(String to) throws Exception{
        MimeMessage message = createMessage(to);

        try{
            javaMailSender.send(message);
        }catch(MailException e){
            throw CanNotSendMailException.EXCEPTION;
        }
        return ePw;
    }

    public BaseResponse<String> mailConfirm(String email) throws Exception {
        userValidator.validateDuplicatedUserEmail(email);
        String code = sendSimpleMessage(email);
        return BaseResponse.of("회원 인증 코드입니다.", code);
    }
}
