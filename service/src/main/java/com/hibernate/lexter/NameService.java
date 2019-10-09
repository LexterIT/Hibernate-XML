package com.hibernate.lexter;

import org.apache.commons.lang3.StringUtils;

public class NameService {
	
	private String lastName, firstName, midName, suffix;
    private ScannerUtil scannerUtil;

	public NameService() {
        scannerUtil = new ScannerUtil();
	}

    public void setScannerUtil(ScannerUtil scannerUtil) {
        this.scannerUtil = scannerUtil;
    }

    public Name createNameInput() {
        lastName = "";
        firstName = "";
        while(StringUtils.isEmpty(lastName)) {
            lastName = scannerUtil.getInputString("Please enter last name");
        }
        while(StringUtils.isEmpty(firstName)){
            firstName = scannerUtil.getInputString("Please enter first name");
        }

        midName = scannerUtil.getInputString("Please enter mid name");
        suffix = scannerUtil.getInputString("Please enter suffix");

        Name name = createName(lastName, firstName, midName, suffix);

        return name;
    }

    public Name createName(String lastName, String firstName, String midName, String suffix) {
        Name name = new Name();
        name.setLastName(lastName);
        name.setFirstName(firstName);
        name.setMidName(midName);
        name.setSuffix(suffix);
        return name;
    }

    public void updateNameInput(Name name) {
        lastName = scannerUtil.getInputString("Enter new Last Name (Leave blank if you don't want to edit)");
        firstName = scannerUtil.getInputString("Enter new First Name (Leave blank if you don't want to edit)");
        midName = scannerUtil.getInputString("Enter new Mid Name (Leave blank if you don't want to edit)");
        suffix = scannerUtil.getInputString("Enter new Suffix (Leave blank if you don't want to edit)");
        updateName(name, lastName, firstName, midName, suffix);
    }

     public void updateName(Name name, String lastName, String firstName, String midName, String suffix) {
        if(!StringUtils.isEmpty(lastName))
            name.setLastName(lastName);
        if(!StringUtils.isEmpty(firstName))
            name.setFirstName(firstName);
        if(!StringUtils.isEmpty(midName))
            name.setMidName(midName);
        if(!StringUtils.isEmpty(suffix))
            name.setSuffix(suffix);
    }

}