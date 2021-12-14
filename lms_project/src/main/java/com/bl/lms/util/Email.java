package com.bl.lms.util;


import java.io.Serializable;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class Email implements Serializable {
	
	private String mailFrom;
    private String mailTo;
    private String mailSubject;
    private String mailBody;
	

}
