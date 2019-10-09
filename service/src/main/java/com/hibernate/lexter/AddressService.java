package com.hibernate.lexter;

import org.apache.commons.lang3.StringUtils;

public class AddressService {
	
	private String street, barangay, municipality, zipcode;
    private ScannerUtil scannerUtil;

	public AddressService() {
        scannerUtil = new ScannerUtil();
	}

    public void setScannerUtil(ScannerUtil scannerUtil) {
        this.scannerUtil = scannerUtil;
    }

    public Address createAddressInput() {
        street = scannerUtil.getInputString("Please Enter Street");
        barangay = scannerUtil.getInputString("Please Enter Barangay");
        municipality = scannerUtil.getInputString("Please Enter Municipality");
        zipcode = scannerUtil.getInputString("Please Enter Zipcode");
        Address address = createAddress(street, barangay, municipality, zipcode);
        return address;
    }

    public Address createAddress(String street, String barangay, String municipality, String zipcode) {
        Address address = new Address();
        address.setStreet(street);
        address.setBarangay(barangay);
        address.setMunicipality(municipality);
        address.setZipCode(zipcode);
        return address;
    }

	public void updateAddressInput(Address address) {
        street = scannerUtil.getInputString("Enter new street (Leave blank if you don't want to edit)");
        barangay = scannerUtil.getInputString("Enter new Barangay (Leave blank if you don't want to edit)");
        municipality = scannerUtil.getInputString("Enter new Municipality (Leave blank if you don't want to edit)");
        zipcode = scannerUtil.getInputString("Enter new ZipCode (Leave blank if you don't want to edit)");
        updateAddress(address, street, barangay, municipality, zipcode);
    }

    public void updateAddress(Address address, String street, String barangay, String municipality, String zipcode) {
    	if(!StringUtils.isEmpty(street))
    		address.setStreet(street);
    	if(!StringUtils.isEmpty(barangay))
    		address.setBarangay(barangay);
    	if(!StringUtils.isEmpty(municipality))
    		address.setMunicipality(municipality);
    	if(!StringUtils.isEmpty(zipcode))
    		address.setZipCode(zipcode);
    }

}