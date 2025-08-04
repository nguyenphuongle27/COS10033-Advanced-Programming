
public class Trainer {
	 private String name;
	    private String address;
	    private String teachingLevel;
	    
	    public Trainer(String name, String address, String Level) {
	        this.name = name;
	        this.address = address;
	        this.teachingLevel = Level;
	    }
	    
	    public String getName() { return name; }
	    public String getAddress() { return address; }
	    public String getTeachingLevel() { return teachingLevel; }
	    
	    public void setTeachingLevel(String teachingLevel) { this.teachingLevel = teachingLevel; }

	    public String toString () {
	    	return  "Name: " + this.name + 
	    			" | Address: " + this.address + 
	    			" | Teaching level: " + this.teachingLevel;
	    }
}

