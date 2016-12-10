package fcu.android.backend.data;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Comment implements Serializable {

	private int ID;
	
	private int userId;

	private String type;

	private String content;

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
