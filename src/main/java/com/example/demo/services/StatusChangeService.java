package com.example.demo.services;

import com.example.demo.repositories.StatusChangeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StatusChangeService {
    @Autowired
    private StatusChangeRepository statusChangeRepository;
}
