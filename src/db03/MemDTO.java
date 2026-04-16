package db03;

public class MemDTO {
	private int num;
	private String name;
	private String id;
	private String email;
	private String pwd;
	private String day;
	
	public MemDTO() {}
	public MemDTO(int num, String name, String id, String email, String pwd, String day) {
		super();
		this.num = num;
		this.name = name;
		this.id = id;
		this.email = email;
		this.pwd = pwd;
		this.day = day;
	}

	@Override
	public String toString() {
		return "memDTO [num=" + num + ", name=" + name + ", id=" + id + ", email=" + email + ", pwd=" + pwd + ", day="
				+ day + "]";
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}
	
	
}
