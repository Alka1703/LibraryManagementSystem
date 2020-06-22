package SignInProject;

public class User {

	private String firstname="";
	private String lastname="";
	private String gender="";
        private String email="";
	private String username="";
	private String password="";
	
	public User(String firstname, String lastname,String gender,String email,String username,String password){
		this.firstname=firstname;
		this.lastname=lastname;
		this.gender=gender;
                this.email=email;
		this.username=username;
		this.password=password;
	}
	
	public User() {
		// TODO Auto-generated constructor stub
	}
	
	public String getFirstName() {
		return firstname;
	}
	public void setFirstName(String firstname) {
		this.firstname = firstname;
	}
	public String getLastName() {
		return lastname;
	}
	public String getGender()  {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
        public String getEmail()  {
		return email;
	}
	public void setEmail(String email) {
		this.email= email;
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password ;
	}
	public void setPassword(String password) {
		this.password=password;
	}
	
	public void setLastName(String lastname) {
		this.lastname = lastname;
	}

}

