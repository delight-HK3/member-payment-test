package com.example.point.service.payment.paytype;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Base64;
import java.net.URL;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.example.point.Enum.paymentValue;
import com.example.point.domain.Member;
import com.example.point.domain.Paymentlog;
import com.example.point.domain.Point;

import com.example.point.model.payment.PaymentRequestDTO;
import com.example.point.model.payment.PaymentResultDTO;
import com.example.point.repository.payment.PaymentRepository;
import com.example.point.repository.point.PointRepository;

@Service
public class TossTypeService implements PaymentTypeService{

    private final PaymentRepository paymentRepository; // 결제내역 저장 Repository
    private final PointRepository pointRepository;  // 포인트 Repository

    public TossTypeService(PaymentRepository paymentRepository, PointRepository pointRepository){
        this.paymentRepository = paymentRepository;
        this.pointRepository = pointRepository; 
    }

    @Value("${toss.api.key.test}")
    private String tossApiKey; // toss API key

    @Override
    public paymentValue getPaymentValue() {
        return paymentValue.TOSS;
    }

    // 토스페이먼츠 결제연동 및 요청
    @Override
    public PaymentResultDTO getPaymentRedirectURL(PaymentRequestDTO paymentRequestDTO) throws Exception {

        Base64.Encoder encoder = Base64.getEncoder();
        byte[] encodedBytes = encoder.encode((tossApiKey + ":").getBytes(StandardCharsets.UTF_8));
        String authorizations = "Basic " + new String(encodedBytes);
    
        JSONObject obj = new JSONObject();
        obj.put("orderId", paymentRequestDTO.getOrderId());
        obj.put("amount", paymentRequestDTO.getAmount());
        obj.put("paymentKey", tossApiKey);

        URL url = new URL("https://api.tosspayments.com/v1/payments/confirm");

        JSONParser parser = new JSONParser();

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestProperty("Authorization", authorizations);
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setRequestMethod("POST");
        connection.setDoOutput(true);
            
        OutputStream outputStream = connection.getOutputStream();
        outputStream.write(obj.toString().getBytes("UTF-8"));

        int code = connection.getResponseCode();
        boolean isSuccess = code == 200;

        InputStream responseStream = isSuccess ? connection.getInputStream() : connection.getErrorStream();
        
        Reader reader = new InputStreamReader(responseStream, StandardCharsets.UTF_8);
        JSONObject jsonObject = (JSONObject) parser.parse(reader);
        System.out.println(jsonObject);
        responseStream.close();

        return new PaymentResultDTO(code, jsonObject.get("message").toString());
    }

    // 결제 후 포인트 및 결제기록 저장 
    @Override
    public void savePayment(Member member, PaymentRequestDTO paymentRequestDTO, PaymentResultDTO resultDTO) {
        
        LocalDateTime nowdate = LocalDateTime.now();
        LocalDateTime nowOutNanos = nowdate.truncatedTo(ChronoUnit.SECONDS);

        Point point = pointRepository.findById(member.getId()).orElse(null);

        // 포인트 값이 없으면 새로운 row 추가
        Paymentlog paymentlog = Paymentlog.builder()
                                            .amount(paymentRequestDTO.getAmount())
                                            .member(member)
                                            .message(resultDTO.getMessage())
                                            .payment(paymentRequestDTO.getPayment())
                                            .code(resultDTO.getCode())
                                            .createdAt(nowOutNanos)
                                            .build();

        if(point == null){ // 신규 회원
            Point pointnew = Point.builder()
                                .member(member)
                                .amountpoint(paymentRequestDTO.getAmount())
                                .build();
        
            pointRepository.save(pointnew); // 포인트 저장
        } else { // 기존 존재 회원
            point.updtPoint(paymentRequestDTO.getAmount()); 
            pointRepository.save(point); // 포인트 업데이트
        }
        
        paymentRepository.save(paymentlog);
    }
    
}
