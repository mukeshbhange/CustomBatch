package com.bl.onboarding.model;

import com.bl.onboarding.dto.BankInfoDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class BankInfo {
	private String panNumber;
	private String aadharNumber;
	private String bankName;
	private String bankAccountNumber;
	private String ifscCode;
	private String passbookPath;
	private String panPath;
	private String aadharPath;
	
	
	public BankInfo(BankInfoDTO bankinfoDTO) {
		this.panNumber = bankinfoDTO.getPanNumber();
		this.aadharNumber = bankinfoDTO.getAadharNumber();
		this.bankName = bankinfoDTO.getBankName();
		this.bankAccountNumber = bankinfoDTO.getBankAccountNumber();
		this.ifscCode = bankinfoDTO.getIfscCode();
		this.passbookPath = bankinfoDTO.getPassbookPath();
		this.panPath = bankinfoDTO.getPanPath();
		this.aadharPath = bankinfoDTO.getAadharPath();
	}

}
