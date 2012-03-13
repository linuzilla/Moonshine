package ncu.cc.moonshine.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


@Entity
@Table(name = "TBL_USER")
//@Table(name="tbl_user", catalog="SA", schema="mydb")
@NamedQueries({
	@NamedQuery(name = "User.AllUsers", query = "SELECT a FROM User a"),
	@NamedQuery(name = "User.User4Name", query = "SELECT a FROM User a WHERE name=:name"),
	@NamedQuery(name = "User.MaxId", query = "SELECT MAX(a.id) FROM User a"),
	@NamedQuery(name = "User.User4Id", query = "SELECT a FROM User a WHERE userId=:id")
})
public class RemoteUser implements java.io.Serializable {
	private static final long serialVersionUID = -1952420768866503093L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Integer	userId;
	
	@Column(name = "name")
	private String	name;
	
	@Column(name = "email")
	private String	email;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
