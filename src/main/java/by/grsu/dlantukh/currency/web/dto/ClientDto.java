package by.grsu.dlantukh.currency.web.dto;

public class ClientDto {

	private Integer id;

	private String firstName;

	private String lastName;

	private String patronymic;

	private String passport;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPatronymic() {
		return patronymic;
	}

	public void setPatronymic(String patronymic) {
		this.patronymic = patronymic;
	}

	public String getPassport() {
		return passport;
	}

	@Override
	public String toString() {
		return "Client [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", patronymic=" + patronymic
				+ ", passport=" + passport + "]";
	}

	public void setPassport(String passport) {
		this.passport = passport;
	}

}