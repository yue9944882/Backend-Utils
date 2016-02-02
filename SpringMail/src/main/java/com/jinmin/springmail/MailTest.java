package com.jinmin.springmail;

import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

import java.nio.MappedByteBuffer;

/**
 * Created by min.jin on 2016/2/2.
 */
public class MailTest {
    public static void main(String[] args){
        MailTest mt=new MailTest();
        mt.placeOrder(new Order("haha",1));
    }

    private MailSender mailSender;
    private SimpleMailMessage templateMessage;

    public void setMailSender(MailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void setTemplateMessage(SimpleMailMessage templateMessage) {
        this.templateMessage = templateMessage;
    }

    public void placeOrder(Order order) {

        // Do the business calculations...

        // Call the collaborators to persist the order...

        // Create a thread safe "copy" of the template message and customize it
        SimpleMailMessage msg = new SimpleMailMessage(this.templateMessage);
        msg.setTo(order.getAddress());
        msg.setText(
                "Dear " + order.getCustomer()
                        + ", thank you for placing order. Your order number is "
                        + order.getOrderNumber());
        try{
            this.mailSender.send(msg);
        }
        catch(MailException ex) {
            // simply log it and go on...
            System.err.println(ex.getMessage());
        }
    }
}

class Order{


    private int orderNumber;
    private String customer;

    public Order(String name,int num){
        this.orderNumber=num;
        this.customer=name;
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }
    public String getAddress(){
        return "min.jin@ctrip.com";
    }

}