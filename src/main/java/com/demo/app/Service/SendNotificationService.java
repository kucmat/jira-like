package com.demo.app.Service;

import com.demo.app.Entity.User;
import com.demo.app.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@Service
public class SendNotificationService {

    @Autowired
    UserRepository userRepository;

    @Async("threadPoolTaskExecutor")
    public void SendSMS(String userName){
        System.out.println("Sending SMS to " + userName);
        try {
            User user = userRepository.findByUserName(userName);
                System.out.println("Users phone number: " + user.getMob());

                Random rand = new Random();
                int pause = rand.nextInt(5);
                System.out.println("pause: " + pause);
                if (pause == 0) {
                    throw new TimeoutException();
                }
                TimeUnit.SECONDS.sleep(pause);
        }catch(TimeoutException e){
            System.out.println("Sending message timed out:" + e.getMessage());
;
        }
        catch(Exception e){
            System.out.println("Error sending message:" + e.getMessage());
        }
    }

    @Async("threadPoolTaskExecutor")
    public void SendEmail(String userName){
        System.out.println("Sending email to " + userName);
        try {
            User user = userRepository.findByUserName(userName);


                System.out.println("Users email: " + user.getEmail());

                Random rand = new Random();
                int pause = rand.nextInt(5);
            System.out.println("pause: " + pause);
                if (pause == 0) {
                    throw new TimeoutException();
                }
                TimeUnit.SECONDS.sleep(pause);

        }catch(TimeoutException e){
            System.out.println("Sending email timed out:" + e.getMessage());


        }
        catch(Exception e){
            System.out.println("Error sending email:" + e.getMessage());
        }
    }
}
