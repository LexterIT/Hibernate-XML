package com.hibernate.lexter;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;
import java.util.concurrent.Phaser;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doNothing;
/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp()
    {
        assertTrue( true );
    }


    HibernateUtil hibernateUtil = mock(HibernateUtil.class);
    //Address Service
    public void testCreateAddress() {
        Scanner scanner = new Scanner("street\nbarangay\nmunicipality\n1234");
        ScannerUtil scannerUtil = new ScannerUtil(scanner);
        AddressService addSer = new AddressService();  
        addSer.setScannerUtil(scannerUtil);
        Address address = addSer.createAddressInput();
        assertEquals(address.toString(), "Street Barangay Municipality 1234");
    }
    public void testUpdateAddress() {
        Scanner scanner = new Scanner("street\nbarangay\nmunicipality\n1234\n");
        ScannerUtil scannerUtil = new ScannerUtil(scanner);
        AddressService addSer = new AddressService();
        addSer.setScannerUtil(scannerUtil);
        Address address = new Address("str","bar","munic","4321");
        addSer.updateAddressInput(address);
        assertEquals(address.toString(),"Street Barangay Municipality 1234");
    }
    //Mainmenu Service
    public void testListPerson() {
        MainMenuService mainService = new MainMenuService(hibernateUtil);
        List<Person> returnList = new ArrayList<Person>();
        Person person = new Person();
        Name name = new Name("Sample", "Lang");
        person = new Person(name);
        returnList.add(person);
        when(hibernateUtil.getSorted(Person.class,"id","asc")).thenReturn(returnList);
        List sampleList = mainService.listPerson();
        Person pers = (Person) sampleList.get(0);
        assertEquals(pers, returnList.get(0));
    }
    public void testSortGwa() {
        MainMenuService mainService = new MainMenuService(hibernateUtil);
        List<Person> people = new ArrayList<Person>();
        Person person1 = new Person(new Name("Person", "1"));
        person1.setGwa(1.5);
        Person person2 = new Person(new Name("Person", "2"));
        person2.setGwa(2.0);
        Person person3 = new Person(new Name("Person", "3"));
        person3.setGwa(1.0);
        people.add(person1);
        people.add(person2);
        people.add(person3);
        when(hibernateUtil.getObject(Person.class)).thenReturn(people);
        List<Person> tempList = mainService.sortPersonGWA("ASC");
        assertEquals(tempList.get(0), person3);
        assertEquals(tempList.get(1), person1);
        assertEquals(tempList.get(2), person2);
    }
    //PersonService  
    public void testCreatePerson() {
        Name name = new Name("Sample", "Lang");
        Address address = new Address();
        Person person = new Person(name);
        List<ContactInfo> conts = new ArrayList<ContactInfo>();
        Set<Role> roles = new HashSet<Role>();
        PersonService perServ = new PersonService(hibernateUtil);
        Person pers = perServ.createPerson(name, address, 0.0, null, null, false, conts, roles);
        assertEquals(person.toString(), pers.toString());
    }
    public void testReadPerson() {
        PersonService perServ = new PersonService(hibernateUtil);
        Name name = new Name("Sample", "Lang");
        Person person = new Person(name);
        when(hibernateUtil.getSingleObject(Person.class, 1)).thenReturn(person);
        Person testPer = perServ.readPerson(1);
        assertEquals(person, testPer);
    }
    public void testDeletePerson() {
        PersonService perServ = new PersonService(hibernateUtil);
        Person person = new Person();
        when(hibernateUtil.getSingleObject(Person.class, 1)).thenReturn(person);
        doNothing().when(hibernateUtil).deleteObject(person);
        perServ.deletePerson(1);
    }
    public void testUpdatePersonInput() {
        PersonService perServ = new PersonService(hibernateUtil);
        Scanner scanner = new Scanner("1\ngwa\n2.0");
        ScannerUtil scannerUtil = new ScannerUtil(scanner);
        perServ.setScannerUtil(scannerUtil);
        Person person = new Person();
        when(hibernateUtil.getSingleObject(Person.class, 1)).thenReturn(person);
        perServ.updatePersonInput();
        assertEquals(person.getGwa().toString(), "2.0");
    }

    //ContactService
    public void testCreateContact() {
        ContactService conServ = new ContactService(hibernateUtil);
        Scanner scanner = new Scanner("Email\nemail@yahoo.com");
        ScannerUtil scannerUtil = new ScannerUtil(scanner);
        conServ.setScannerUtil(scannerUtil);
        List<ContactType> contactTypes = new ArrayList<ContactType>();
        contactTypes.add(new ContactType("Mobile"));
        contactTypes.add(new ContactType("Email"));
        contactTypes.add(new ContactType("Landline"));
        when(hibernateUtil.getObject(ContactType.class)).thenReturn(contactTypes);
        ContactInfo contact = new ContactInfo("Email", "Email@yahoo.com");
        ContactInfo testCon = conServ.createContactInput();
        assertEquals(contact.toString(), testCon.toString());
    }
    public void testEditContact() {
        Scanner scanner = new Scanner("0\nMobile\nEmail@gmail.com");
        ScannerUtil scannerUtil = new ScannerUtil(scanner);
        ContactService conServ = new ContactService(hibernateUtil);
        conServ.setScannerUtil(scannerUtil);
        List<ContactInfo> contactInfos = new ArrayList<ContactInfo>();
        contactInfos.add(new ContactInfo("Email","Email@yahoo.com"));
        contactInfos.add(new ContactInfo("Mobile","09123456789"));
        List<ContactType> contactTypes = new ArrayList<ContactType>();
        contactTypes.add(new ContactType("Email"));
        contactTypes.add(new ContactType("Mobile"));
        when(hibernateUtil.getObject(ContactType.class)).thenReturn(contactTypes);
        conServ.editContactInput(contactInfos);
        assertEquals(contactInfos.get(0).getContactInfo(), "Email@gmail.com");
    }

    //RoleService
    public void testCreateRole() {
        RoleService roleServ = new RoleService(hibernateUtil);
        String string = "testRole";
        Role testRole = new Role(string);
        Role role = roleServ.createRole(string);
        assertEquals(testRole.toString(), role.toString());  
    }
    public void testEditRole() {
        RoleService roleServ = new RoleService(hibernateUtil);
        String string = "testRole";
        String newRole = "newRole";
        Role testRole = new Role(string);
        when(hibernateUtil.getSingleObject(Role.class, 1)).thenReturn(testRole);
        doNothing().when(hibernateUtil).updateObject(testRole);
        roleServ.updateRole(1, newRole);
        assertEquals(testRole.toString(), newRole);
    }
    public void testReadRole() {
        RoleService roleServ = new RoleService(hibernateUtil);
        List<Role> roles = new ArrayList<Role>();
        roles.add(new Role("testRole"));
        roles.add(new Role("newRole"));
        when(hibernateUtil.getObject(Role.class)).thenReturn(roles);
        roles = roleServ.readRoles();
        assertEquals(roles.get(0).toString(), "testRole");
        assertEquals(roles.get(1).toString(), "newRole");
    }

    //NameService
    public void testCreateName() {
        Scanner scanner = new Scanner("lang\nsample\nyes\njr");
        ScannerUtil scannerUtil = new ScannerUtil(scanner);
        NameService nameServ = new NameService();
        nameServ.setScannerUtil(scannerUtil);
        Name testName = nameServ.createNameInput();
        assertEquals(testName.toString(), "Lang Sample Yes Jr");
    }
    public void testUpdateName() {
        Scanner scanner = new Scanner("\nsample\nyes\njr");
        ScannerUtil scannerUtil = new ScannerUtil(scanner);
        NameService nameServ = new NameService();
        nameServ.setScannerUtil(scannerUtil);
        Name name = new Name("SamPle","LaNg","To","Sr");
        nameServ.updateNameInput(name);
        assertEquals(name.toString(), "LaNg Sample Yes Jr");
    }

    //PersonRoleService
    public void testCreatePersonRole() {
        Scanner scanner = new Scanner("Admin\nYes\nSoftware Engineer\nNo");
        ScannerUtil scannerUtil = new ScannerUtil(scanner);
        PersonRoleService perolServ = new PersonRoleService(hibernateUtil);
        perolServ.setScannerUtil(scannerUtil);
        Set<Role> curRoles = new HashSet<Role>();
        List<Role> roles = new ArrayList<Role>();
        Role role = new Role("Admin");
        Role role1 = new Role("Software Engineer");
        Role role2 = new Role("Quality Assurance");
        roles.add(role);
        roles.add(role1);
        roles.add(role2);
        when(hibernateUtil.getObject(Role.class)).thenReturn(roles);
        curRoles = perolServ.createPersonRoleInput(curRoles);
        assertEquals(curRoles.size(), 2);
        assertTrue(curRoles.contains(role));
        assertTrue(curRoles.contains(role1));
        assertFalse(curRoles.contains(role2));
    }
    public void testUpdatePersonRole() {
        Scanner scanner = new Scanner("delete\n1");
        ScannerUtil scannerUtil = new ScannerUtil(scanner);
        PersonRoleService perolServ = new PersonRoleService(hibernateUtil);
        perolServ.setScannerUtil(scannerUtil);
        Person person = new Person();
        Set<Role> curRoles = new HashSet<Role>();
        Role role = new Role("Admin");
        Role role1 = new Role("Software Engineer");
        Role role2 = new Role("Quality Assurance");
        curRoles.add(role);
        curRoles.add(role1);
        person.setRoles(curRoles);
        perolServ.updateRoleMenu(person);
        assertEquals(curRoles.size(), 1);
    }
}
