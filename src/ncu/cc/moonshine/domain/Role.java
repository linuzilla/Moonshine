package ncu.cc.moonshine.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the roles database table.
 * 
 */
@Entity
@Table(name="roles")
public class Role implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="role_id", nullable=false)
	private Integer roleId;

	@Column(name="role_name", nullable=false, length=255)
	private String roleName;

	//bi-directional many-to-many association to User
	@ManyToMany(mappedBy="roles")
	private List<User> users;

	//bi-directional many-to-one association to RoleRight
	@OneToMany(mappedBy="role")
	private List<RoleRight> roleRights;

    public Role() {
    }

	public Integer getRoleId() {
		return this.roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return this.roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public List<User> getUsers() {
		return this.users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}
	
	public List<RoleRight> getRoleRights() {
		return this.roleRights;
	}

	public void setRoleRights(List<RoleRight> roleRights) {
		this.roleRights = roleRights;
	}
	
}