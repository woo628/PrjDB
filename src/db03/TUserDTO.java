package db03;

public class TUserDTO {
	// fields
	private String userid;
	private String username;
	private String email;
	
	// constructor
	public TUserDTO() {}
	public TUserDTO(String userid, String username, String email) {
	    this.userid = userid;
		this.username = username;
		this.email = email;
	}
	public TUserDTO(String username, String email) {
		this.username = username;
		this.email = email;
	}
	// get&set
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	// tostring
	@Override
	public String toString() {
		return "TUserDTO [userid=" + userid + ", username=" + username + ", email=" + email + "]";
	}
}
