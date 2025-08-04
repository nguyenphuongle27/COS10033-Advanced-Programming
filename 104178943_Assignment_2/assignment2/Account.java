package assignment2;

import java.io.Serializable;

public class Account  implements Serializable {
    private static final long serialVersionUID = 1L;
    private String name;
    private String username;
    private String password;
    
	//Get and set methods
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    
    // Constructor
    public Account(String name, String username, String password) {
        this.name = name;
        this.username = username;
        this.password = password;
    }
}
