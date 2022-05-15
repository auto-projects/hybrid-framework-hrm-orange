package utilities;

import java.util.Date;

import com.github.javafaker.Faker;

public class DataUtil {
	private Faker faker;

	public static DataUtil getData() {
		return new DataUtil();
	}

	public DataUtil() {
		faker = new Faker();

	}

	public String getFirstName() {
		return faker.name().firstName();
	}

	public String getEditFirstName() {
		return faker.name().firstName();
	}

	public String getLastName() {
		return faker.name().lastName();
	}

	public String getEditLastName() {
		return faker.name().lastName();
	}

	public String getFullName() {
		return getFirstName() + " " + getLastName();
	}

	public String getEmailAddress() {
		return faker.internet().emailAddress();
	}

	public String getUsername() {
		return faker.name().username();
	}

	public String getPassword() {
		return faker.internet().password();
	}

	public String getStreetAddress() {
		return faker.address().streetAddress();
	}

	public String getCity() {
		return faker.address().city();
	}

	public String getState() {
		return faker.address().state();
	}

	public String getZipPostalCode() {
		return faker.address().zipCode();
	}

	public String getCountry() {
		return faker.address().country();
	}

	public String getName() {
		return faker.name().name();
	}

	public String getRelationship() {
		return faker.relationships().any();
	}

	public String getPhoneNumber() {
		return faker.phoneNumber().phoneNumber();
	}

	public String getCellPhone() {
		return faker.phoneNumber().cellPhone();
	}
	public String getNumber() {
		return faker.number().digits(9);
	}

	public Date getYYYYMMDD() {
		return faker.date().birthday();
	}

	public String getCompanyName() {
		return faker.company().name();
	}

	public String getJobTitle() {
		return faker.job().title();

	}
	public int getAmount(int min, int max) {
		return faker.number().numberBetween(10000, 20000);
	}
}
