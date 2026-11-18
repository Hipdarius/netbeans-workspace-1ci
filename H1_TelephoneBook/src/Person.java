/** A phone number is text: preserve spaces, + signs and leading zeroes. */
public class Person implements Comparable<Person> {
    private String fullName;
    private String phone;
    private String email;

    public Person(String fullName, String phone, String email) {
        if (fullName == null || fullName.trim().isEmpty() || phone == null || email == null) {
            throw new IllegalArgumentException("A name, phone and email value are required.");
        }
        this.fullName = fullName.trim();
        this.phone = phone;
        this.email = email;
    }

    public String getFullName() { return fullName; }
    public String getPhone() { return phone; }
    public String getEmail() { return email; }

    @Override
    public int compareTo(Person other) {
        return fullName.compareToIgnoreCase(other.fullName);
    }
}
