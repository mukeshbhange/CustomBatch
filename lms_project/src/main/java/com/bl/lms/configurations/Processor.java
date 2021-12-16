package com.bl.lms.configurations;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import com.bl.lms.entity.LMS_User;

@Component
public class Processor implements ItemProcessor<LMS_User, LMS_User> {

	@Override
	public LMS_User process(LMS_User item) throws Exception {
		// TODO Auto-generated method stub
		return item;
	}

    

}