package ncu.cc.moonshine.domain;

import java.io.Serializable;
import javax.persistence.*;

import java.util.List;


/**
 * The persistent class for the user database table.
 * 
 */
@Entity
@Table(name="TBL_USER")
@NamedQueries({
	@NamedQuery(name = "findAll", query = "SELECT a FROM User a"),
	@NamedQuery(name = "findByName", query = "SELECT a FROM User a WHERE name=:name")
})
public class User implements Serializable,SecureEntity {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id", nullable=false)
	private Integer userId;

	@Column(nullable=false, length=255)
	private String email;

	@Column(nullable=false, length=255)
	private String name;
	
	@Column(name="created_by", nullable=false, length=255)
	private String createdBy;

	//bi-directional many-to-many association to Role
    @ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(
		name="TBL_USER_ROLE"
		, joinColumns={
			@JoinColumn(name="user_id", nullable=false)
			}
		, inverseJoinColumns={
			@JoinColumn(name="role_id", nullable=false)
			}
		)
	private List<Role> roles;
    
    @Transient
    private transient boolean	deletable = false;

    public User() {
    }

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Role> getRoles() {
		return this.roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public boolean isDeletable() {
		return deletable;
	}

	public void setDeletable(boolean deletable) {
		this.deletable = deletable;
	}
}