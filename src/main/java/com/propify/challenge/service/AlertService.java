package com.propify.challenge.service;

import org.springframework.stereotype.Service;

@Service
public class AlertService {

    public void sendPropertyDeletedAlert(int id) {
        System.out.println("Triggered Alert Service for record id :" + id);
        // What this method actually does is not important
    }
}
