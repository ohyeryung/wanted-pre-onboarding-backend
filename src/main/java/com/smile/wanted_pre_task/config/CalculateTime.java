package com.smile.wanted_pre_task.config;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CalculateTime {

    public static String dateformatForPost(LocalDateTime dateTime){
        return dateTime.format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm"));
    }
}
