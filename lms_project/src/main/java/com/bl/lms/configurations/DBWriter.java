package com.bl.lms.configurations;

import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bl.lms.entity.LMS_User;
import com.bl.lms.repository.LMSRepository;

@Component
public class DBWriter implements ItemWriter<LMS_User> {
	private LMSRepository userRepository;

    @Autowired
    public DBWriter (LMSRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void write(List<? extends LMS_User> users) throws Exception{
        System.out.println("Data Saved for Users: " + users);
        for (LMS_User user : users) {
			System.out.println(user);
			userRepository.save(user);
		}
    }

}
